package junit;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Date: 2023/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@RunWith(Parameterized.class)
public class PrimeNumberCheckerTest {

    private Integer inputNumber;
    private Boolean exceptedResult;
    private PrimeNumberChecker primeNumberChecker;


    public PrimeNumberCheckerTest(Integer inputNumber, Boolean exceptedResult) {
        this.inputNumber = inputNumber;
        this.exceptedResult = exceptedResult;
    }


    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, true},
                {6, false},
                {19, true},
                {22, false},
                {23, true},
        });
    }

    @Before
    public void initialize() {
        primeNumberChecker = new PrimeNumberChecker();
    }


    @Test
    public void testPrimeNumberChecker() {
        System.out.println("Parameterized Number is:" + inputNumber);
        Assert.assertEquals(exceptedResult, primeNumberChecker.validate(inputNumber));
    }


}