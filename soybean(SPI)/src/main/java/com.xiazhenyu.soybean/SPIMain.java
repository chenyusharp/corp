package com.xiazhenyu.soybean;

import java.util.ServiceLoader;

/**
 * Date: 2021/7/26
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@SuppressWarnings(value = "all")
public class SPIMain {

    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout shout : shouts) {
            shout.shout();
        }
    }

}