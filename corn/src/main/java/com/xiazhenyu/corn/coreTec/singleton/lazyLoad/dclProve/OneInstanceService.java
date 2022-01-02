package com.xiazhenyu.corn.coreTec.singleton.lazyLoad.dclProve;

import java.util.Random;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class OneInstanceService {

    public int status;

    public static OneInstanceService service;


    private OneInstanceService() {
        status = new Random().nextInt(200) + 1;
    }

    public static OneInstanceService getInstance() {
        if (service == null) {
            synchronized (OneInstanceService.class) {
                if (service == null) {
                    service = new OneInstanceService();
                }
            }
        }
        return service;
    }


    public static void reSet() {
        service = null;
    }


}