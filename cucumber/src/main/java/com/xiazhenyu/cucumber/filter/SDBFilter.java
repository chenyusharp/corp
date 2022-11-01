package com.xiazhenyu.cucumber.filter;

import com.xiazhenyu.common.util.HashUtil;

/**
 * Date: 2022/11/1
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SDBFilter extends FuncFilter {

    public SDBFilter(long maxValue) {
        this(maxValue, DEFAULT_MACHINE_NUM);
    }

    public SDBFilter(long maxValue, int machineNum) {
        super(maxValue, machineNum, HashUtil::sdbmHash);
    }
}