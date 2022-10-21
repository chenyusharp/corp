package com.xiazhenyu.corn.util;

import com.google.common.base.Objects;
import com.google.common.base.Stopwatch;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2022/10/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Slf4j
@Builder
public class CollectionUtil {


    public static void main(String[] args) {

        final SecureRandom random = new SecureRandom();
        List<Integer> copySourceList1 = new ArrayList<>(40000);
        List<Integer> copySourceList2 = new ArrayList<>(40000);
        List<Integer> copySourceList3 = new ArrayList<>(40000);
        List<Integer> targetList = new ArrayList<>(70000);
        for (int i = 0; i < 40000; i++) {
            int nextInt = random.nextInt(40000);
            copySourceList1.add(nextInt);
            copySourceList2.add(nextInt);
            copySourceList3.add(nextInt);
        }
        for (int i = 0; i < 70000; i++) {
            targetList.add(random.nextInt(70000));
        }
//        sourceList.stream().forEach(System.out::println);

        Stopwatch sp = Stopwatch.createStarted();
        copySourceList1.removeAll(targetList);
        System.out.println(
                "list removeAll api need time:" + sp.elapsed(TimeUnit.MILLISECONDS) + "result size:" + copySourceList1.size());

        sp.reset();
        sp.start();
        copySourceList2 = removeAllWithMap(copySourceList2, targetList);
        System.out.println(
                "removeAllWithMap need time:" + sp.elapsed(TimeUnit.MILLISECONDS) + "result size:" + copySourceList2.size());

        sp.reset();
        sp.start();
        copySourceList3 = removeAllWithSet(copySourceList3, targetList);
        System.out.println(
                "removeAllWithSet need time:" + sp.elapsed(TimeUnit.MILLISECONDS) + "result size:" + copySourceList3.size());

//        List<Student> sourceStudentList = new ArrayList<Student>() {{
//            add(Student.builder()
//                    .no("1")
//                    .age(12)
//                    .name("xiaoming")
//                    .nation("china")
//                    .build());
//
//            add(Student.builder()
//                    .no("2")
//                    .age(12)
//                    .name("xiaoming")
//                    .nation("china")
//                    .build());
//
//            add(Student.builder()
//                    .no("3")
//                    .age(12)
//                    .name("xiaoming")
//                    .nation("china")
//                    .build());
//        }};

//        List<Student> targetStudentList = new ArrayList<Student>() {{
//            add(Student.builder()
//                    .no("1")
//                    .age(12)
//                    .name("xiaoming")
//                    .nation("china")
//                    .build());
//        }};
//        sourceStudentList =removeAllWithSet(sourceStudentList, targetStudentList);
//        System.out.println("result size:" + sourceStudentList.size());

    }

    @Data
    @Builder
    public static class Student {

        private String no;
        private String name;
        private int age;
        private String nation;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Student student = (Student) o;
            return Objects.equal(getNo(), student.getNo());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getNo());
        }
    }


    public static <T> List<T> removeAllWithMap(List<T> sourceList, List<T> targetList) {
        List<T> result = new LinkedList<>();
        Map<T, Integer> sourceMap = new HashMap<>();
        sourceList.forEach(k -> {
            if (sourceMap.containsKey(k)) {
                sourceMap.put(k, sourceMap.get(k) + 1);
            } else {
                sourceMap.put(k, 1);
            }
        });
        Set<T> targetSet = new HashSet<>(targetList);
        sourceMap.forEach((k, v) -> {
            if (targetSet.add(k)) {
                for (int i = 0; i < v; i++) {
                    result.add(k);
                }
            }
        });
        return result;
    }


    public static <T> List<T> removeAllWithSet(List<T> sourceList, List<T> targetList) {
        List<T> result = new LinkedList<>();
        Set<T> targetSet = new HashSet<>(targetList);
        sourceList.stream().filter(x -> !targetSet.contains(x)).forEach(result::add);
        return result;
    }


}