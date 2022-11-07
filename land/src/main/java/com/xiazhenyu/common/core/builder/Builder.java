package com.xiazhenyu.common.core.builder;

import java.io.Serializable;
import sun.plugin2.message.Serializer;

/**
 * Date: 2022/11/6
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface Builder<T> extends Serializable {


    T build();


}
