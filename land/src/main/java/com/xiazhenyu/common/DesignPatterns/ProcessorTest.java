package com.xiazhenyu.common.DesignPatterns;

/**
 * Date: 2025/5/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ProcessorTest {

    public static void main(String[] args) {

        Processor processor = new ProcessorChainBuilder()
                .addProcessor(new ProcessorA())  // 添加 ProcessorA
                .addProcessor(new ProcessorB())  // 添加 ProcessorB
                .addProcessor(new ProcessorC())  // 添加 ProcessorC
                .build();
        Context context = new Context();
        processor.process(context);
    }


}