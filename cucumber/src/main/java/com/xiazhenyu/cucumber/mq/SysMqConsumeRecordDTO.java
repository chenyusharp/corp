package com.xiazhenyu.cucumber.mq;

import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 消息队列消费记录(解决重复消费的问题)
 * </p>
 *
 * @author mybatisplus-generator
 * @since 2019-10-28
 */
@Data
public class SysMqConsumeRecordDTO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 消息主题
     */
    private String topic;

    /**
     * 消息接收时间
     */
    private Date consumeTime;

    /**
     * 备注
     */
    private String remark;
}
