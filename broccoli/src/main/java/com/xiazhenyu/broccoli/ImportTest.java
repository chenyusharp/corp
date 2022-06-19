package com.xiazhenyu.broccoli;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Date: 2022/6/19
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ImportTest {


    public static void main(String[] args) {
        ApplicationContext  context=new AnnotationConfigApplicationContext(ConfigA.class);
        ServiceInterface bean=context.getBean(ServiceB.class);
        bean.test();
    }

}