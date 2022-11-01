package com.xiazhenyu.cucumber.filter;

import com.xiazhenyu.common.util.HashUtil;

/**
 * Date: 2022/11/1
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ELFFilter extends FuncFilter {

    private static final long serialVersionUID = -7601716763246909029L;

    public ELFFilter(long maxValue) {
        this(maxValue, DEFAULT_MACHINE_NUM);
    }

    public ELFFilter(long maxValue, int machineNum) {
        super(maxValue, machineNum, HashUtil::elfHash);
    }
}