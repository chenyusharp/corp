package junit;

import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * Date: 2023/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class TimeOutTest {


    @Test(timeout = 1000)
    public void testCase1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5000);
        System.out.println("in timeout exception");
    }

}