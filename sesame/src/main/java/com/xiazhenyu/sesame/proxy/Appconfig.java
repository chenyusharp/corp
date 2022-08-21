package com.xiazhenyu.sesame.proxy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Date: 2022/7/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Configuration
@ComponentScan("com.xiazhenyu.sesame.proxy")
@EnableAspectJAutoProxy
public class Appconfig {

}