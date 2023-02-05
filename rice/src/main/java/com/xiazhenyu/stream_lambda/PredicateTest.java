package com.xiazhenyu.stream_lambda;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Date: 2022/1/27
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class PredicateTest {

    public static void main(String[] args) {

        Predicate<String> nonEmpty = s -> !s.isEmpty();
        List<String> ans = filter(Lists.newArrayList("", "name"), nonEmpty);
        System.out.println(ans);
        Quote quote=new Quote();



    }


    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        list.forEach(var -> {
            if (predicate.test(var)) {
                result.add(var);
            }
        });
        return result;
    }


}