package eventBus;

import com.google.common.eventbus.Subscribe;
import java.time.Instant;

/**
 * Date: 2021/12/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class EventListener2 {

    @Subscribe
    public void test(CustomEvent event) {

        System.out.println(Instant.now() + ",监听者2,收到事件：" + event.getAge() + "，线程号为：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}