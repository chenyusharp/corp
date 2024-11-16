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
public @interface BojunAPIResponse {


    /**
     * 别名
     * @return
     */
    String alias();
}
