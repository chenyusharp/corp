package com.xiazhenyu.common.DesignPatterns;

/**
 * Date: 2025/5/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface Processor {
    void process(Context context);
    void setNext(Processor next);
    Processor getNext();
}
