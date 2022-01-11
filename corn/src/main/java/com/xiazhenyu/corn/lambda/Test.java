package com.xiazhenyu.corn.lambda;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Date: 2022/1/10
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test {


    public static void main(String[] args) {

        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("name", "xiazhenyu");
        map.put("age", 16);
        map.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });


    }

}