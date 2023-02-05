package com.xiazhenyu.json;

import com.alibaba.fastjson.JSON;
import java.lang.reflect.Type;

/**
 * Date: 2022/1/27
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class FastJsonTest {


    public static void main(String[] args) {

//        Object parsedValue = valueParser == null ? fieldValue : valueParser.apply(fieldValue);

        Object parsedValue = new Student(12, "xiaoming");
        System.out.println(">>>>>>>>>>>>"+parsedValue);

        // 利用JSON深拷贝
       Student  parsedValue1 = JSON.parseObject(JSON.toJSONString(parsedValue), (Type) parsedValue.getClass());
        System.out.println(">>>>>>>>>>>>"+parsedValue1);

        System.out.println(Math.sqrt(9));

    }


    public static class Student {

        private int age;
        private String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }


        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}

