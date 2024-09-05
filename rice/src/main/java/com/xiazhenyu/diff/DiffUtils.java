package com.xiazhenyu.diff;

import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 2024/9/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DiffUtils {


    private static final Logger log = LoggerFactory.getLogger(DiffUtils.class);

    public static void main(String[] args) {
        Student  studentA=new Student();
        studentA.setAge(11);
        studentA.setName("Jack");
        studentA.setGrade("中二班");



        Student  studentB=new Student();
        studentB.setAge(21);
        studentB.setName("Mike");
        studentB.setGrade("小二班");

        DiffNode root = ObjectDifferBuilder.buildDefault().compare(studentB, studentA);
        log.info(root.toString());
    }


    public static class Student {

        private String name;
        private int age;

        private String grade;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }


}


