package eventBus;

import java.time.Instant;

/**
 * Date: 2021/12/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class TestMain {


    public static void main(String[] args) {
        EventListener1 eventListener1 = new EventListener1();
        EventListener2 eventListener2 = new EventListener2();

        CustomEvent customEvent = new CustomEvent(23);

        EventBusUtil.register(eventListener1);
        EventBusUtil.register(eventListener2);

//        EventBusUtil.post(customEvent);
        EventBusUtil.asyncPost(customEvent);
        try {
            Thread.sleep(2* 1000);
        } catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println(Instant.now() + ",主线程执行完毕：" + Thread.currentThread().getName());

    }

}