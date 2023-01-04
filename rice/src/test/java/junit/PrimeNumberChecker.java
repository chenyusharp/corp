package junit;

/**
 * Date: 2023/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class PrimeNumberChecker {

    public Boolean validate(final Integer primeNumber) {
        for (int i = 2; i < (primeNumber / 2); i++) {
            if (primeNumber % i == 0) {
                return false;
            }
        }
        return true;
    }















}