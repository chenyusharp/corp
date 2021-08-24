package com.xiazhenyu.serialization;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SerializationFactory {

    public static Serialization get(byte type) {
        switch (type & 0x7) {
            case 0x0:
                return new HessianSerialization();
            default:
                return new HessianSerialization();
        }

    }


}