package com.xiazhenyu;

/**
 * Date: 2021/8/19
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String param) {
        System.out.println("param" + param);
        return "hello:" + param;
    }
}