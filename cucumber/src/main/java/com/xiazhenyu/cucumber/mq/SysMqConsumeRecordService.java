package com.xiazhenyu.cucumber.mq;

import java.util.Date;

/**
 * <p>
 * 消息队列消费记录 服务类
 * </p>
 *
 * @author mybatisplus-generator
 * @since 2019-10-28
 */
public interface SysMqConsumeRecordService {

    /**
     * 保存新
     */
    boolean insertNew(SysMqConsumeRecordDTO newDto);

    /**
     * 删除
     */
    boolean deleteDateBefore(Date targetDate);

    /**
     * 保存新错误
     */
    boolean insertNewError(SysMqConsumeErrorRecordDTO newDto);
}
