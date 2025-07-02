package com.xiazhenyu.common.DesignPatterns;

/**
 * Date: 2025/5/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ProcessorA extends AbstractProcessor {

    @Override
    protected void startProcess(Context context) {
        System.out.println("ProcessorA开始执行");
    }

    @Override
    protected void afterProcess(Context context) {
        System.out.println("ProcessorA已执行完毕");
    }
}