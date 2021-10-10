package com.xiazhenyu.corn.redisson;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

/**
 * Date: 2021/10/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ListExamples {


    public static void main(String[] args) {
        RedissonClient client = Redisson.create();
        RList<String> nameList = client.getList("nameList");
        nameList.clear();
        nameList.add("bingo");
        nameList.add("yangmei");
        nameList.forEach(System.out::println);
        client.shutdown();
    }

}