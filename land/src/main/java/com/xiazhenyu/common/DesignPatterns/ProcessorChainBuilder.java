package com.xiazhenyu.common.DesignPatterns;

/**
 * Date: 2025/5/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ProcessorChainBuilder {

    public Processor head;
    public Processor tail;

    // 添加处理器到链中
    public ProcessorChainBuilder addProcessor(Processor processor) {
        if (head == null) {
            head = processor;
            tail = processor;
        } else {
            tail.setNext(processor);
            tail = processor;
        }
        return this;
    }


    // 构建最终链
    public Processor build() {
        return head;
    }


}