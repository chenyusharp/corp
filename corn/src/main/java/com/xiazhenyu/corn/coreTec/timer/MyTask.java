package com.xiazhenyu.corn.coreTec.timer;

import java.util.TimerTask;

/**
 * Date: 2022/1/12
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("任务执行了，时间为:" + System.currentTimeMillis());
    }
}