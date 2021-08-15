import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;

/**
 * Date: 2021/8/12
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Singleton {


    //此处使用volatile修饰，为了防止jv对singleton对象的重排序，
    // 因为singleton=new Singleton()  这个操作并不是原子的，
    //step1 给singleton分配内存空间
    //step2 调用singleton的构造函数等来初始化singleton对象
    //step3 将singleton对象指向分配的内存空间（执行完这一步singleton对象就不是null了）
    //重排序之后就会导致step3会先于step2的发生
    //https://kaiwu.lagou.com/course/courseInfo.htm?courseId=16#/detail/pc?id=301
    private static volatile Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        //防止出现初始化之后，后续的线程依然走下面的synchronized部分，都会穿行化执行
        if (singleton == null) {
            //加锁，实例化代码同一时刻只会有一个线程在执行
            synchronized (Singleton.class) {
                //由于并发问题，同时跳过第一重check检查的多个线程，在其中一个线程先拿到锁并执行完初始化且
                // 释放了锁之后，另外的处于自旋的线程拿到锁之后，判断singleton不是为null，跳过创建实例的语句。
                //如果不加，重新拿到锁的线程就也会创建一个实例，破坏了单例。
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}