package com.eptison.bojun;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Date: 2024/10/24
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BojunAPIRequest {

    /**
     * 查询的列名
     * @return
     */
    String column() default "";

    /**
     * 查询的条件名
     * @return
     */
    String condition() default "";


    /**
     * 操作符：> 、>= 、< 、<= 、= 、in（如果属性值是集合的话，不用设置)、like(伯俊默认的)
     * @return
     */
    String operator() default "=";


    /**
     * 查询表达式，优先级最高，如果表达式有值，会当作condition的值
     * @return
     */
    String expression() default "";


    /**
     * 关联表id
     * @return
     */
    int reftableId() default -1;

    /**
     * 关联表的操作类型,有add、modify、delete三种操作类型
     * @return
     */
    String reftableType() default "add";



}
