package eventBus;

/**
 * Date: 2021/12/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class CustomEvent {

    private int age;


    public CustomEvent(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}