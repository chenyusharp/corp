package com.xiazhenyu.corn.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Date: 2021/8/25
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ParallelStream {


    public static void main(String[] args) {

        Stream<String> stream = Stream.of("How", "do", "you", "do");
        List<String> var0=stream.parallel().collect(ArrayList::new,ArrayList::add,(t,u)->{
            System.out.println("t:" + t + " u:" + u);
            t.addAll(u);
        });
        System.out.println(var0);
        List<String> list = stream.collect(ArrayList::new, ArrayList::add, (t, u) -> {
            System.out.println("t:" + t + " u:" + u);
            t.addAll(u);
        });
        System.out.println(list);


    }


}