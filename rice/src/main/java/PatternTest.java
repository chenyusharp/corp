import static java.util.regex.Pattern.*;

import com.google.common.base.Preconditions;
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
        final Pattern POSITIVE_INTEGER = compile("^[1-9][0-9]*$");
        Preconditions.checkArgument(
                POSITIVE_INTEGER.matcher("3").find(),
                "不是正整数");

    }

}