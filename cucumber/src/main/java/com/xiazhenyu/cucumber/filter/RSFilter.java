package com.xiazhenyu.cucumber.filter;

import com.xiazhenyu.common.util.HashUtil;

/**
 * Date: 2022/11/1
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class RSFilter extends FuncFilter {

    public RSFilter(long maxValue) {
        this(maxValue, DEFAULT_MACHINE_NUM);
    }

    public RSFilter(long maxValue, int machineNum) {
        super(maxValue, machineNum, HashUtil::rsHash);
    }
}