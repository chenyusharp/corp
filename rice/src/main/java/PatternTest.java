import static java.util.regex.Pattern.*;

import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Date: 2021/9/20
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class PatternTest {


    public static void main(String[] args) {
//        final Pattern POSITIVE_INTEGER = compile("^[1-9][0-9]*$");
//        Preconditions.checkArgument(
//                POSITIVE_INTEGER.matcher("3.33").find(),
//                "不是正整数");

        int result = BigDecimal.ONE
                .compareTo(new BigDecimal("1.010").stripTrailingZeros());
        System.out.println(result);

    }

}