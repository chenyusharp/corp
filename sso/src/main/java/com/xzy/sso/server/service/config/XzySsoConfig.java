package com.xzy.sso.server.service.config;

import com.xzy.sso.core.store.SsoLoginStore;
import com.xzy.sso.core.util.JedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * Date: 2023/7/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Configuration
public class XzySsoConfig implements InitializingBean, DisposableBean {

    private String redisAddress;

    private int redisExpireMinute;


    @Override
    public void destroy() throws Exception {
        JedisUtil.close();

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SsoLoginStore.setRedisExpireMinute(redisExpireMinute);
        JedisUtil.init(redisAddress);
    }
}