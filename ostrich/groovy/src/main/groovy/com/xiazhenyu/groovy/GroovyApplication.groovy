package com.xiazhenyu.groovy

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class GroovyApplication {

    static void main(String[] args) {
//        SpringApplication.run(GroovyApplication, args)
        // this is  a statement
        println("Hello World!");
//        def x = 5;
        println("Hello World!");
        int x=5;
        long y=100L;
        float a=10.56f;
        double b=10.5e40;
        BigInteger bi=30g;
        BigDecimal bd=3.5g;
        println(x);
        println(y);
        println(a);
        println(b);
        println(bi);
        println(bd);



        def range=0..5
        println(range)
        println(range.get(5))


        int count=0;

        while (count<5){
            println(count);
            count++;
        }


















    }

}
