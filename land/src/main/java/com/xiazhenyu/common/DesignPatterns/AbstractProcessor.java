package com.xiazhenyu.common.DesignPatterns;

/**
 * Date: 2025/5/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public abstract class AbstractProcessor implements Processor {

    private Processor next;

    @Override
    public void process(Context context) {
        this.startProcess(context);
        if (next != null) {
            next.process(context);
        }
    }

    @Override
    public void setNext(Processor next) {
        this.next = next;
    }

    @Override
    public Processor getNext() {
        return next;
    }

    protected abstract void startProcess(Context context);

    protected abstract void afterProcess(Context context);


}