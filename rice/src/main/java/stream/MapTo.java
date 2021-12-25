package stream;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Date: 2021/12/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MapTo {


    public static void main(String[] args) {
        List<String> itemCodeList = new ArrayList<>();
        itemCodeList.add("123");
        itemCodeList.add("345");
        itemCodeList.add("789");
//        List<Long> longItemCodeList = itemCodeList.stream().mapToLong(Long::new).collect(LinkedList::new);

        //匿名内部类
        List<String> names = new ArrayList<String>() {
            {
                add("xiazhenyu");
                add("zhenyu");
                add("sharp");

            }

        };
    }

}