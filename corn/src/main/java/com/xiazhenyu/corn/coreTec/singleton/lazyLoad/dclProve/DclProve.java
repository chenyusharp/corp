package com.xiazhenyu.corn.coreTec.singleton.lazyLoad.dclProve;

import java.util.concurrent.CountDownLatch;
import org.checkerframework.checker.units.qual.C;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DclProve {

    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            CountDownLatch latch = new CountDownLatch(1);
            CountDownLatch end = new CountDownLatch(100);
            for (int i = 0; i < 100; i++) {
                new Thread(() -> {
                    try {
                        latch.await();
                        OneInstanceService instanceService = OneInstanceService.getInstance();
                        if (instanceService.status == 0) {
                            System.out.println("process end");
                            System.exit(0);
                        }
                        end.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            latch.countDown();
            end.await();
            OneInstanceService.reSet();
        }


    }

}