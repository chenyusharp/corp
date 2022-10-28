package com.xiazhenyu.cucumber.mq;

import lombok.Getter;

/**
 * Date: 2022/10/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Getter
public enum EpMqTopic {
    ;

    private String nameHelper;
    private String desc;


    EpMqTopic(String nameHelper, String desc) {
        this.nameHelper = nameHelper;
        this.desc = desc;
    }
}