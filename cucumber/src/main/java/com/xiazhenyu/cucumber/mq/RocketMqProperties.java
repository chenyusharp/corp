package com.xiazhenyu.cucumber.mq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Date: 2022/10/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.rocketmq")
public class RocketMqProperties {

    private String nameServe;

    private String producerGroup;

    private int sendMessageTimeout = 3000;

    private int compressMessageBodyThreshold = 1024 * 4;

}