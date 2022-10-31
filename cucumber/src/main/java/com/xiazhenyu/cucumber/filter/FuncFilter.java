package com.xiazhenyu.cucumber.filter;

import java.util.function.Function;

/**
 * Date: 2022/10/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class FuncFilter extends AbstractFilter {


    private static final long serialVersionUID = 2644641448485121361L;


    private final Function<String, Number> hashFunc;


    public FuncFilter(long maxValue, Function<String, Number> hashFunc) {
        this(maxValue, DEFAULT_MACHINE_NUM, hashFunc);
    }


    public FuncFilter(long maxValue, int machineNum, Function<String, Number> hashFunc) {
        super(maxValue, machineNum);
        this.hashFunc = hashFunc;
    }

    @Override
    public long hash(String str) {
        return hashFunc.apply(str).longValue() % size;
    }
}