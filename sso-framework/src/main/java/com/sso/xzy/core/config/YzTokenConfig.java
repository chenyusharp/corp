package com.sso.xzy.core.config;

import com.sun.org.apache.xpath.internal.operations.Bool;
import java.io.Serializable;

/**
 * Date: 2023/8/9
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class YzTokenConfig implements Serializable {

    private static final long serialVersionUID = -7412694179769618633L;

    private String tokenName = "yztoken";

    private long timeout = 60 * 60 * 24 * 30;

    private long activeTimeout = -1;

    private Boolean dynamicActiveTimeout = false;

    private Boolean isConcurrent = true;

    private Boolean isShare;

    private int maxLoginCount = 12;


    private Boolean isPrint = true;


    private Boolean isLog = false;

    public Boolean getPrint() {
        return isPrint;
    }

    public Boolean getLog() {
        return isLog;
    }
}