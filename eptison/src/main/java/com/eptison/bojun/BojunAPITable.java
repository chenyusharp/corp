package com.eptison.bojun;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Date: 2024/10/25
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BojunAPITable {

    /**
     * 对应表的ID或名称，或快捷码。我们建议使用表名
     * @return
     */
    String table();

    /**
     * 伯俊结构返回对象映射到fms的DTO类
     * @return
     */
    Class resultMapDTO() default Void.class;


    /**
     *  是否提交，默认false
     * @return
     */
    boolean submit() default false;

}