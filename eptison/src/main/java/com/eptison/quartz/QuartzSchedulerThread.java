package com.eptison.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import org.quartz.JobPersistenceException;
import org.quartz.SchedulerException;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.core.JobRunShell;
import org.quartz.core.QuartzScheduler;
import org.quartz.core.QuartzSchedulerResources;
import org.quartz.spi.JobStore;
import org.quartz.spi.OperableTrigger;
import org.quartz.spi.TriggerFiredBundle;
import org.quartz.spi.TriggerFiredResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 2025/4/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class QuartzSchedulerThread extends Thread {


    private QuartzScheduler qs;

    private QuartzSchedulerResources qsRsrcs;

    private final Object sigLock = new Object();

    private boolean signaled;

    private long signaledNextFireTime;

    private boolean paused;

    private AtomicBoolean halted;

    private Random random = new Random(System.currentTimeMillis());

    private static long DEFAULT_IDLE_WAIT_TIME = 30L * 1000L;

    private long idleWaitTime = DEFAULT_IDLE_WAIT_TIME;

    private int idleWaitVariableness = 7 * 1000;

    private final Logger log = LoggerFactory.getLogger(getClass());


    public QuartzSchedulerThread(QuartzScheduler qs, QuartzSchedulerResources qsRsrcs) {
        this.qs = qs;
        this.qsRsrcs = qsRsrcs;
    }

    public QuartzSchedulerThread(QuartzScheduler qs, QuartzSchedulerResources qsRsrcs, boolean setDaemon, int threadPrio) {
        super(qs.getSchedulerThreadGroup(), qsRsrcs.getThreadName());
        this.qs = qs;
        this.qsRsrcs = qsRsrcs;
        setDaemon(setDaemon);
        if (qsRsrcs.isThreadsInheritInitializersClassLoadContext()) {
            this.setContextClassLoader(Thread.currentThread().getContextClassLoader());
        }
        this.setPriority(threadPrio);
        paused = true;
        halted = new AtomicBoolean(false);
    }

    void setIdleWaitTime(long waitTime) {
        idleWaitTime = waitTime;
        idleWaitVariableness = (int) (waitTime * 0.2);
    }


    private long getRandomizedIdleWaitTime() {
        return idleWaitTime - random.nextInt(idleWaitVariableness);
    }


    void togglePaused(boolean pause) {
        synchronized (sigLock) {
            paused = pause;
            if (paused) {
                signalSchedulingChange(0);
            } else {
                sigLock.notifyAll();
            }
        }
    }


    void halt(boolean wait) {
        synchronized (sigLock) {
            halted.set(true);
            if (paused) {
                sigLock.notifyAll();
            } else {
                signalSchedulingChange(0);
            }
        }
        if (wait) {
            boolean interrupted = false;
            try {
                while (true) {
                    try {
                        join();
                        break;
                    } catch (InterruptedException e) {
                        interrupted = true;
                    }
                }
            } finally {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    boolean isPaused() {
        return paused;
    }


    public void signalSchedulingChange(int candidateNewNextFireTime) {
        synchronized (sigLock) {
            signaled = true;
            signaledNextFireTime = candidateNewNextFireTime;
            sigLock.notifyAll();
        }
    }

    public void clearSignaledSchedulingChanges() {
        synchronized (sigLock) {
            signaled = false;
            signaledNextFireTime = 0;
        }
    }

    public boolean isSchedulingChanged() {
        synchronized (sigLock) {
            return signaled;
        }
    }

    public long getSignaledNextFireTime() {
        synchronized (sigLock) {
            return signaledNextFireTime;
        }
    }

    @Override
    public void run() {
        int acquiresFailed = 0;
        while (!halted.get()) {
            try {
                synchronized (sigLock) {
                    while (paused && !halted.get()) {
                        try {
                            sigLock.wait(1000L);
                        } catch (InterruptedException ignore) {

                        }
                        acquiresFailed = 0;
                    }
                    if (halted.get()) {
                        break;
                    }
                }
                if (acquiresFailed > 1) {
                    try {
                        long delay = computeDelayForRepeatedErrors(qsRsrcs.getJobStore(), acquiresFailed);
                    } catch (Exception ignore) {

                    }
                }
                final int availThreadCount = qsRsrcs.getThreadPool().blockForAvailableThreads();

                if (availThreadCount > 0) {
                    List<OperableTrigger> triggers;
                    long now = System.currentTimeMillis();
                    clearSignaledSchedulingChanges();
                    try {
                        triggers = qsRsrcs.getJobStore().acquireNextTriggers(now + idleWaitTime,
                                Math.min(availThreadCount, qsRsrcs.getMaxBatchSize()), qsRsrcs.getBatchTimeWindow());
                        acquiresFailed = 0;
                    } catch (JobPersistenceException jpe) {
                        if (acquiresFailed == 0) {
                            qs.notifySchedulerListenersError("An error occurred while scanning for the next triggers to fire", jpe);
                        }
                        if (acquiresFailed < Integer.MAX_VALUE) {
                            acquiresFailed++;
                        }
                        continue;
                    } catch (RuntimeException e) {
                        if (acquiresFailed < Integer.MAX_VALUE) {
                            acquiresFailed++;
                        }
                        continue;
                    }
                    if (triggers != null && !triggers.isEmpty()) {
                        now = System.currentTimeMillis();
                        long triggerTime = triggers.get(0).getNextFireTime().getTime();
                        long timeUntilTrigger = triggerTime - now;
                        while (timeUntilTrigger > 2) {
                            synchronized (sigLock) {
                                if (halted.get()) {
                                    break;
                                }
                                if (!isCandidateNewTimeEarlierWithinReason(triggerTime, false)) {
                                    try {
                                        now = System.currentTimeMillis();
                                        timeUntilTrigger = triggerTime - now;
                                        if (timeUntilTrigger >= 1) {
                                            sigLock.wait(timeUntilTrigger);
                                        }
                                    } catch (InterruptedException jgnore) {
                                    }
                                }
                            }
                            if (releaseIfScheduleChangedSignificantly(triggers, triggerTime)) {
                                break;
                            }
                            now = System.currentTimeMillis();
                            timeUntilTrigger = triggerTime - now;
                        }
                        if (triggers.isEmpty()) {
                            continue;
                        }
                        List<TriggerFiredResult> bndles = new ArrayList<>();
                        boolean goAhead = true;
                        synchronized (sigLock) {
                            goAhead = !halted.get();
                        }
                        if (goAhead) {
                            try {
                                final List<TriggerFiredResult> res = qsRsrcs.getJobStore().triggersFired(triggers);
                                if (res != null) {
                                    bndles = res;
                                }
                            } catch (SchedulerException se) {
                                qs.notifySchedulerListenersError("An error occurred while firing triggers", se);
                                for (OperableTrigger trigger : triggers) {
                                    qsRsrcs.getJobStore().releaseAcquiredTrigger(trigger);
                                }
                                continue;
                            }
                        }

                        for (int i = 0; i < bndles.size(); i++) {
                            final TriggerFiredResult result = bndles.get(i);
                            final TriggerFiredBundle bundle = result.getTriggerFiredBundle();
                            final Exception exception = result.getException();
                            if (exception instanceof RuntimeException) {
                                qsRsrcs.getJobStore().releaseAcquiredTrigger(triggers.get(i));
                                continue;
                            }
                            if (bundle == null) {
                                qsRsrcs.getJobStore().releaseAcquiredTrigger(triggers.get(i));
                                continue;
                            }
                            JobRunShell shell = null;
                            try {
                                shell = qsRsrcs.getJobRunShellFactory().createJobRunShell(bundle);
                                shell.initialize(qs);
                            } catch (SchedulerException se) {
                                qsRsrcs.getJobStore().triggeredJobComplete(triggers.get(i),
                                        bundle.getJobDetail(), CompletedExecutionInstruction.SET_ALL_JOB_TRIGGERS_ERROR);
                                continue;
                            }
                            if (!qsRsrcs.getThreadPool().runInThread(shell)) {
                                qsRsrcs.getJobStore().triggeredJobComplete(triggers.get(i), bundle.getJobDetail(),
                                        CompletedExecutionInstruction.SET_ALL_JOB_TRIGGERS_ERROR);
                            }
                        }
                        continue;
                    }
                } else {
                    continue;
                }
                final long now = System.currentTimeMillis();
                long waitTime = now + getRandomizedIdleWaitTime();
                long waitUntilContinue = waitTime - now;
                synchronized (sigLock) {
                    try {
                        if (!halted.get()) {
                            if (!isSchedulingChanged()) {
                                sigLock.wait(waitUntilContinue);
                            }
                        }

                    } catch (InterruptedException ignore) {

                    }
                }


            } catch (Exception e) {

            }

        }
        qs = null;
        qsRsrcs = null;

    }

    private boolean releaseIfScheduleChangedSignificantly(List<OperableTrigger> triggers, long triggerTime) {
        if (isCandidateNewTimeEarlierWithinReason(triggerTime, true)) {
            for (OperableTrigger trigger : triggers) {
                qsRsrcs.getJobStore().releaseAcquiredTrigger(trigger);
            }
            triggers.clear();
            return true;
        }
        return false;
    }

    private boolean isCandidateNewTimeEarlierWithinReason(long oldTime, boolean clearSignal) {
        synchronized (sigLock) {
            if (!isSchedulingChanged()) {
                return false;
            }
            boolean earlier = false;
            if (getSignaledNextFireTime() == 0) {
                earlier = true;
            } else if (getSignaledNextFireTime() < oldTime) {
                earlier = true;
            }
            if (earlier) {
                final long diff = oldTime - System.currentTimeMillis();
                if (diff < (qsRsrcs.getJobStore().supportsPersistence() ? 70L : 7L)) {
                    earlier = false;
                }
            }
            if (clearSignal) {
                clearSignaledSchedulingChanges();
            }
            return earlier;
        }
    }


    private static final long MIN_DELAY = 20;

    private static final long MAX_DELAY = 600000;


    private long computeDelayForRepeatedErrors(JobStore jobStore, int acquiresFailed) {
        long delay;
        try {
            delay = jobStore.getAcquireRetryDelay(acquiresFailed);
        } catch (Exception ignore) {
            delay = 100;
        }
        if (delay < MIN_DELAY) {
            delay = MIN_DELAY;
        }
        if (delay > MAX_DELAY) {
            delay = MAX_DELAY;
        }
        return delay;
    }
}