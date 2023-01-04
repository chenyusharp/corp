package junit.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Date: 2023/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class HelloWorldTest {


    @Test
    public void firstTest() {
        assertEquals(2, 1 + 1);
    }




    @Test
    @Disabled("for demonstration purposes")
    public void skippedTest(){
        System.out.println("not skip test");
    }

}