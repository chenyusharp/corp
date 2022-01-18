package com.xiazhenyu.sesame;


import com.xiazhenyu.sesame.dispatch.StaticDispatch;
import com.xiazhenyu.sesame.dispatch.StaticDispatch.Human;
import com.xiazhenyu.sesame.dispatch.StaticDispatch.Man;
import com.xiazhenyu.sesame.dispatch.StaticDispatch.Woman;
import org.junit.Test;

/**
 * Date: 2022/1/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */

public class DispatchTest {


    @Test
    public void dispatch() {

        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);

    }

}