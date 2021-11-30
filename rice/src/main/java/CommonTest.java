import com.google.common.collect.Lists;
import java.util.List;

/**
 * Date: 2021/9/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class CommonTest {

    public static void main(String[] args) {
        /*List<Integer> businessTypeList = Lists.newArrayList(12, 13);
        if (businessTypeList.contains(13)) {
            System.out.println("success");
        }*/
//static 语句块，只能访问到定义在static语句块之前的变量，a就是这个一个样子；而定义在static语句块之后的，就访问不到static语句块之内的。
        System.out.println(A.a);
        System.out.println(A.b);

        final int MAXIMUM_CAPACITY = 1 << 30;
        System.out.println(MAXIMUM_CAPACITY);

        final String name="xiazhenyu";
        System.out.println(name);


    }


    public static class A {

        static int a = 0;

        static {
            a = 1;
            b = 1;
        }

        static int b = 0;
    }


}