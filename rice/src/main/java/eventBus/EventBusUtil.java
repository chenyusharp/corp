package eventBus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.Executor;
import jdk.nashorn.internal.ir.Block;
import lombok.Synchronized;

/**
 * Date: 2021/12/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class EventBusUtil {

    private static EventBus eventBus;

    private static AsyncEventBus asyncEventBus;


    private static Object asyncEventBusLock = new Object();

    private static Object eventBusLock = new Object();

    private static Executor executor = new Executor() {
        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    };


    public static AsyncEventBus getAsyncEventBus() {
        if (asyncEventBus == null) {
            synchronized (asyncEventBusLock) {
                if (asyncEventBus == null) {
                    asyncEventBus = new AsyncEventBus(executor);
                }
            }
        }
        return asyncEventBus;
    }

    public static EventBus getEventBus() {
        if (eventBus == null) {
            synchronized (eventBusLock) {
                if (eventBus == null) {
                    eventBus = new EventBus();
                }
            }
        }
        return eventBus;
    }

    public static void post(Object object) {
        getEventBus().post(object);
    }

    public static void asyncPost(Object object) {
        getAsyncEventBus().post(object);
    }


    public static void register(Object object) {
        getEventBus().register(object);
        getAsyncEventBus().register(object);
    }


}