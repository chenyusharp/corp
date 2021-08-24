package com.xiazhenyu.serialization;

import java.io.IOException;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface Serialization {


    <T> byte[] serialize(T obj) throws IOException;


    <T> T deserialize(byte[] data, Class<T> clz) throws IOException;

}
