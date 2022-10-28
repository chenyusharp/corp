package com.xiazhenyu.cucumber.mq;

import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 消息队列消费出错记录
 * </p>
 *
 * @author mybatisplus-generator
 * @since 2019-10-28
 */
@Data
public class SysMqConsumeErrorRecordDTO {

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

    /**
     * 错误堆栈信息
     */
    private String stackTrace;
}
