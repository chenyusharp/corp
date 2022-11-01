package com.xiazhenyu.cucumber.filter;

import com.xiazhenyu.common.util.HashUtil;
import java.util.function.Function;

/**
 * Date: 2022/11/1
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class JSFilter extends FuncFilter {

    public JSFilter(long maxValue) {
        this(maxValue, DEFAULT_MACHINE_NUM);
    }

    public JSFilter(long maxValue, int machineNum) {
        super(maxValue, machineNum, HashUtil::jsHash);
    }
}