package junit;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Date: 2023/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class HasGlobalTimeoutTest {


    public static String log;

    private final CountDownLatch latch = new CountDownLatch(1);


    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);

    @Test
    public void testSleepForTooLong() throws InterruptedException {
        log += "ran1";
        TimeUnit.SECONDS.sleep(100);
    }

    @Test
    public void testBlockForver() throws InterruptedException {
        log += "rang2";
        latch.await();
    }


}