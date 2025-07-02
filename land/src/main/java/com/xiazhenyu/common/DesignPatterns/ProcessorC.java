package com.xiazhenyu.common.DesignPatterns;

/**
 * Date: 2025/5/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ProcessorC extends AbstractProcessor {

    @Override
    protected void startProcess(Context context) {
        System.out.println("ProcessorC开始执行");
    }

    @Override
    protected void afterProcess(Context context) {
        System.out.println("ProcessorC执行完毕");
    }
}