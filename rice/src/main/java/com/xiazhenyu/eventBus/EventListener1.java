package com.xiazhenyu.eventBus;

import com.google.common.eventbus.Subscribe;
import java.time.Instant;

/**
 * Date: 2021/12/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class EventListener1 {


    @Subscribe
    public void test1(CustomEvent customEvent) {


        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(
                Instant.now() + "监听者1-->订阅者1,收到事件：" + customEvent.getAge() + "，线程号为：" + Thread.currentThread().getName());
    }


    @Subscribe
    public void test2(CustomEvent customEvent) {
        System.out.println(
                Instant.now() + "监听者1-->订阅者2,收到事件：" + customEvent.getAge() + "，线程号为：" + Thread.currentThread().getName());
    }

}