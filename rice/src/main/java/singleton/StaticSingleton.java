package singleton;

/**
 * Date: 2022/6/19
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class StaticSingleton {


    private StaticSingleton() {
    }


    public static StaticSingleton getInstance() {
        return StaticSingletonHolder.INSTANCE;
    }


    private static class StaticSingletonHolder {

        private static final StaticSingleton INSTANCE = new StaticSingleton();
    }


}