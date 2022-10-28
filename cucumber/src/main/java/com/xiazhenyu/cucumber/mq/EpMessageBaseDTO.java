package com.xiazhenyu.cucumber.mq;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * your Description
 *
 * @author 朱智贤
 * @since 2019-10-23
 */
@EqualsAndHashCode(callSuper = false)
public class EpMessageBaseDTO extends EpBasePayableDTO implements Serializable {

    /**
     * 消息ID, 用于后续消息幂等性处理
     */
    @Getter
    @Setter
    private String messageId;

    /**
     * 消息主题, 用于后续消息幂等性处理
     */
    @Getter
    @Setter
    private String topic;

    /**
     * 服务器还剩下多少消息没有消费
     */
    @Getter
    @Setter
    private Long consumeOffsetDiff;

    /**
     * 消息内容
     */
    @Getter
    @Setter
    private String msg;


    public EpMessageBaseDTO() {
    }

    public EpMessageBaseDTO(String messageId, String topic) {
        this.messageId = messageId;
        this.topic = topic;
    }
}
