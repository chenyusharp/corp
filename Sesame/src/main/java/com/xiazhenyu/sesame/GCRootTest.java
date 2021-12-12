package com.xiazhenyu.sesame;

/**
 * Date: 2021/12/12
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class GCRootTest {


    //栈帧中引用的对象
    public void foo(Student student) {

    }

    //静态变量
    private static final Student student = new Student();

    //
    private String ERROR_CODE = "error_code";



    public static void foo1() {

    }


    public static class Student {

        String name;
        String age;
    }

}