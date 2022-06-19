package com.xiazhenyu.broccoli;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Date: 2022/6/19
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */

@Import({ConfigB.class})
public class ConfigA {


    @Bean
    public ServiceInterface  getServiceA(){
        return new ServiceA();
    }

}