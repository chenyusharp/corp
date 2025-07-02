package com.eptison.quartz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.quartz.SchedulerConfigException;
import org.quartz.spi.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 2025/4/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SimpleThreadPool implements ThreadPool {

    private int count = -1;

    private int prio = Thread.NORM_PRIORITY;

    private boolean isShutdown = false;

    private boolean handoffPending = false;

    private boolean inheritLoader = false;

    private boolean inheritGroup = false;

    private boolean makeThreadsDaemons = false;

    private ThreadGroup threadGroup;

    private final Object nextRunnableLock = new Object();


    private List<WorkerThread> workers;
    private LinkedList<WorkerThread> availableWorkers = new LinkedList<WorkerThread>();
    private LinkedList<WorkerThread> busyWorkers = new LinkedList<WorkerThread>();


    private String threadNamePrefix;

    private String schedulerInstanceName;

    private final Logger log = LoggerFactory.getLogger(getClass());

    public int getThreadPriority() {
        return prio;
    }

    public SimpleThreadPool() {
    }

    public SimpleThreadPool(int threadCount, int threadPriority) {
        this.count = threadCount;
        this.prio = threadPriority;
    }


    public void setThreadCount(int count) {
        this.count = count;
    }

    public int getThreadCount() {
        return count;
    }


    protected void clearFromBusyWorkersList(WorkerThread wt) {
        synchronized (nextRunnableLock) {
            busyWorkers.remove(wt);
            nextRunnableLock.notifyAll();
        }
    }


    protected void makeAvailable(WorkerThread wt) {
        synchronized (nextRunnableLock) {
            if (!isShutdown) {
                availableWorkers.add(wt);
            }
            busyWorkers.remove(wt);
            nextRunnableLock.notifyAll();
        }
    }


    @Override
    public int blockForAvailableThreads() {
        synchronized (nextRunnableLock) {

            while ((availableWorkers.size() < 1 || handoffPending) && !isShutdown) {
                try {
                    nextRunnableLock.wait(500);
                } catch (InterruptedException ignore) {

                }
            }
            return availableWorkers.size();
        }
    }

    @Override
    public void initialize() throws SchedulerConfigException {
        if (workers != null && !workers.isEmpty()) // already initialized...
        {
            return;
        }

        if (count <= 0) {
            throw new SchedulerConfigException(
                    "Thread count must be > 0");
        }
        if (prio <= 0 || prio > 9) {
            throw new SchedulerConfigException(
                    "Thread priority must be > 0 and <= 9");
        }
        if (isThreadsInheritGroupOfInitializingThread()) {
            threadGroup = Thread.currentThread().getThreadGroup();
        } else {
            threadGroup = Thread.currentThread().getThreadGroup();
            ThreadGroup parent = threadGroup;
            while (!parent.getName().equals("main")) {
                threadGroup = parent;
                parent = threadGroup.getParent();
            }
            threadGroup = new ThreadGroup(parent, schedulerInstanceName + "-SimpleThreadPool");
            if (isMakeThreadsDaemons()) {
                threadGroup.setDaemon(true);
            }
        }

        if (isThreadsInheritContextClassLoaderOfInitializingThread()) {
            log.info(
                    "Job execution threads will use class loader of thread: "
                            + Thread.currentThread().getName());
        }
        final Iterator<WorkerThread> threadIterator = createWorkerThreads(count).iterator();
        while (threadIterator.hasNext()) {
            WorkerThread thread = threadIterator.next();
            thread.start();
            workers.add(thread);
        }
    }

    public boolean runInThread(Runnable runnable) {
        if (runnable == null) {
            return false;
        }
        synchronized (nextRunnableLock) {
            handoffPending = true;
            while (availableWorkers.size() < 1 && !isShutdown) {
                try {
                    nextRunnableLock.wait(500);
                } catch (InterruptedException ignore) {
                }
            }
            if (!isShutdown) {
                final WorkerThread wt = availableWorkers.removeFirst();
                busyWorkers.add(wt);
                wt.run(runnable);
            } else {
                WorkerThread wt = new WorkerThread(this, threadGroup,
                        "WorkerThread-LastJob", prio, isMakeThreadsDaemons(), runnable);
                busyWorkers.add(wt);
                workers.add(wt);
                wt.start();
            }
            nextRunnableLock.notifyAll();
            handoffPending = false;
        }
        return true;
    }


    protected List<WorkerThread> createWorkerThreads(int createCount) {
        workers = new ArrayList<WorkerThread>();
        for (int i = 0; i < createCount; i++) {
            String threadPrefix = getThreadNamePrefix();
            if (threadPrefix != null) {
                threadPrefix = schedulerInstanceName + "_Worker";
            }
            WorkerThread wt = new WorkerThread(this, threadGroup,
                    threadPrefix + "-" + i,
                    getThreadPriority(),
                    isMakeThreadsDaemons());
            if (isThreadsInheritContextClassLoaderOfInitializingThread()) {
                wt.setContextClassLoader(Thread.currentThread().getContextClassLoader());
            }
            workers.add(wt);
        }
        return workers;
    }


    public void shutdown() {
        shutdown(true);
    }

    @Override
    public void shutdown(boolean waitForJobsToComplete) {
        synchronized (nextRunnableLock) {
            isShutdown = true;

            if (workers == null) {
                return;
            }
            Iterator<WorkerThread> threadIterator = workers.iterator();
            while (threadIterator.hasNext()) {
                WorkerThread thread = threadIterator.next();
                thread.shutdown();
                availableWorkers.remove(thread);
            }
            nextRunnableLock.notifyAll();
            boolean interrupted = false;
            if (waitForJobsToComplete) {
                try {
                    while (handoffPending) {
                        try {
                            nextRunnableLock.wait(100);
                        } catch (InterruptedException _) {
                            interrupted = true;
                        }
                    }
                    if (busyWorkers.size() > 0) {
                        final WorkerThread workerThread = busyWorkers.getFirst();
                        try {
                            nextRunnableLock.wait(2000);
                        } catch (InterruptedException _) {
                            interrupted = true;
                        }
                    }
                    threadIterator = workers.iterator();
                    while (threadIterator.hasNext()) {
                        WorkerThread thread = threadIterator.next();
                        try {
                            thread.join();
                            threadIterator.remove();
                        } catch (InterruptedException _) {
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
    }

    @Override
    public int getPoolSize() {
        return getThreadCount();
    }

    @Override
    public void setInstanceId(String schedInstId) {

    }

    public void setThreadNamePrefix(String prfx) {
        this.threadNamePrefix = prfx;
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public boolean isThreadsInheritContextClassLoaderOfInitializingThread() {
        return inheritLoader;
    }

    /**
     * @param inheritLoader
     *          The threadsInheritContextClassLoaderOfInitializingThread to
     *          set.
     */
    public void setThreadsInheritContextClassLoaderOfInitializingThread(
            boolean inheritLoader) {
        this.inheritLoader = inheritLoader;
    }

    public boolean isThreadsInheritGroupOfInitializingThread() {
        return inheritGroup;
    }

    public void setThreadsInheritGroupOfInitializingThread(
            boolean inheritGroup) {
        this.inheritGroup = inheritGroup;
    }

    public boolean isMakeThreadsDaemons() {
        return makeThreadsDaemons;
    }

    /**
     * @param makeThreadsDaemons
     *          The value of makeThreadsDaemons to set.
     */
    public void setMakeThreadsDaemons(boolean makeThreadsDaemons) {
        this.makeThreadsDaemons = makeThreadsDaemons;
    }

    @Override
    public void setInstanceName(String schedName) {
        schedulerInstanceName = schedName;
    }

    class WorkerThread extends Thread {

        private final Object lock = new Object();
        private AtomicBoolean running = new AtomicBoolean(true);
        private SimpleThreadPool tp;
        private Runnable runnable = null;
        private boolean runOnce = false;

        WorkerThread(SimpleThreadPool tp, ThreadGroup threadGroup, String name, int prio, boolean isDaemon) {
            this(tp, threadGroup, name, prio, isDaemon, null);
        }


        public WorkerThread(SimpleThreadPool tp, ThreadGroup threadGroup, String name, int prio, boolean isDaemon, Runnable runnable) {
            super(threadGroup, name);
            this.tp = tp;
            this.runnable = runnable;
            if (runnable != null) {
                runOnce = true;
            }
            setPriority(prio);
            setDaemon(isDaemon);
        }

        void shutdown() {
            running.set(false);
        }

        public void run(Runnable newRunnable) {
            synchronized (lock) {
                if (runnable != null) {
                    throw new IllegalStateException("Already running a  Runnable");
                }
                runnable = newRunnable;
                lock.notifyAll();
            }
        }

        @Override
        public void run() {
            boolean ran = false;
            while (running.get()) {
                try {
                    synchronized (lock) {
                        while (runnable == null && running.get()) {
                            lock.wait(500);
                        }
                        if (runnable != null) {
                            ran = true;
                            runnable.run();
                        }
                    }
                } catch (InterruptedException unblock) {

                } catch (Throwable exceptionInRunnable) {
                } finally {
                    synchronized (lock) {
                        runnable = null;
                    }
                    if (getPriority() != tp.getThreadPriority()) {
                        setPriority(tp.getThreadPriority());
                    }
                    if (runOnce) {
                        running.set(false);
                        clearFromBusyWorkersList(this);
                    } else if (ran) {
                        ran = false;
                        makeAvailable(this);
                    }
                }
            }
            try {
                log.debug("WorkThread is shut down.");
            } catch (Exception e) {

            }
        }
    }
}