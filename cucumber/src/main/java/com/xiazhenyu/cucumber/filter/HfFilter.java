package com.xiazhenyu.cucumber.filter;

import com.xiazhenyu.common.util.HashUtil;

/**
 * Date: 2022/11/1
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class HfFilter extends FuncFilter {


    public HfFilter(long maxValue) {
        this(maxValue, DEFAULT_MACHINE_NUM);
    }

    public HfFilter(long maxValue, int machineNum) {
        super(maxValue, machineNum, HashUtil::hfHash);
    }
}