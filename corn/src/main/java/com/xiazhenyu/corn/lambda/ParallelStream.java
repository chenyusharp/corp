package com.xiazhenyu.corn.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
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

        Supplier<Stream<String>> supplier = () -> Stream.of("How", "do", "you", "do");
        List<String> var0 = supplier.get().parallel().collect(ArrayList::new, ArrayList::add, (t, u) -> {
            System.out.println("t:" + t + " u:" + u);
            t.addAll(u);
        });
        System.out.println(var0);
        List<String> list = supplier.get().collect(ArrayList::new, ArrayList::add, (t, u) -> {
            System.out.println("t:" + t + " u:" + u);
            t.addAll(u);
        });

        List<String> createStream = new ArrayList<>();
        createStream.add("xia");
        createStream.add("zhen");
        createStream.add("yu");
        Stream<String> stringStream = Stream.of(createStream.toArray(new String[createStream.size()]));

        List<String> result = stringStream.collect(ArrayList::new, ArrayList::add, (t, u) -> {
            t.addAll(u);
        });
        System.out.println(result);
    }


    public static void handler(Stream<String> supplier) {
        List<String> list = supplier.collect(ArrayList::new, ArrayList::add, (t, u) -> {
            System.out.println("t:" + t + " u:" + u);
            t.addAll(u);
        });
        System.out.println(list);

    }


}