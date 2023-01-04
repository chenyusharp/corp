package junit;

import org.junit.Test;

/**
 * Date: 2023/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */

public class ExceptionTest {


    @Test(expected = ArithmeticException.class)
    public void exceptionTest() {
        System.out.println("in exception success test");
        int a = 0;
        int b = 1 / a;
    }

    @Test(expected = NullPointerException.class)
    public void exceptionFailTest() {
        System.out.println("in exception fail test");
        int a = 0;
        int b = 1 / a;
    }



}