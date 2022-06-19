package com.xiazhenyu.broccoli;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Date: 2022/6/19
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Configuration
public class ConfigB {


    @Bean
    public ServiceInterface  getServiceB(){
        System.out.println("get ServiceB");
        return  new ServiceB();
    }

}