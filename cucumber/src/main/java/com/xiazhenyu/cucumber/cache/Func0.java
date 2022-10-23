package com.xiazhenyu.cucumber.cache;

import java.io.Serializable;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface Func0<R> extends Serializable {


    R call() throws Exception;



    default R callWithRuntimeException(){
        try {
            return call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
