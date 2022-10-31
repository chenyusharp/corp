package com.xiazhenyu.cucumber.filter;

import java.util.function.Function;

/**
 * Date: 2022/10/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DefaultFilter extends FuncFilter {


    private static final long serialVersionUID = 1763438950487937157L;


    public DefaultFilter(long maxValue) {
        super(maxValue, DEFAULT_MACHINE_NUM);
    }

    public DefaultFilter(long maxValue, int machineNum) {
        super(maxValue, machineNum,);
    }
}