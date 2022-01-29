package stream_lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.Builder;
import lombok.Data;
import org.junit.Test;

/**
 * Date: 2022/1/28
 * <p>
 * 方法引用的分类：
 * <li>
 * 向静态方法的方法引用（例如Integer::parseInt）;
 * </li>
 * <li>
 * 指向任意类型实例方法的方法引用（例如String的length方法，写作String::length。 注意需要通过实例对象进行调用。
 * </li>
 * <li>
 * 指向持有的对象的实例方法的方法引用。这个意思就是，如果实例中声明了另外一个对象的实例变量instance，instance中持有instanceMethod方法，那么就可以
 * 采用instance::instanceMethod来引用这个方法。
 * </li>
 * </p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Quote {


    public static void main(String[] args) {
        Supplier<List<?>> list = ArrayList::new;
        List<?> x = list.get();

        Function<Integer, Student> c2 = Student::new;

        Student student_0 = c2.apply(10);
        System.out.println(student_0.getAge());


    }


    @Test
    public void quoteTest() {
        Student student = new Student(10);
        Function<Integer, Integer> doubleAge = student::doubleAge;
        System.out.println(doubleAge.apply(student.getAge()));

        //实例方法引用
        List<Student> students = new ArrayList<Student>() {
            private static final long serialVersionUID = 3263085725159076409L;

            {
                add(new Student(10, "jack"));
                add(new Student(12, "nik"));
            }
        };
        students.sort(Comparator.comparingInt(Student::getAge));

        //

        Student student_1 = new Student(10, "herry", new MyTransaction("5", "4", "3"));
//        ((Student x) -> x.myTransaction.getValue());

//        PrintCustomer printCustomer = (Student x) -> x.myTransaction.getValue();
        PrintCustomer printCustomer =Student::getPositionX;
        System.out.println(printCustomer.println(student_1));

        //
//        Customer customer = (String original) -> student_1.myTransaction.getValue();
        Customer customer = student_1.myTransaction::decorate;
        System.out.println(customer.println("haha"));




    }


    public interface PrintCustomer {

        String println(Student origin);

    }


    public interface Customer {

        String println(String origin);

    }


    @Data
    @Builder
    public static class Student {

        private int age;
        private String name;
        private MyTransaction myTransaction;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public Student(int age) {
            this.age = age;
        }

        public Student(int age, String name, MyTransaction myTransaction) {
            this.age = age;
            this.name = name;
            this.myTransaction = myTransaction;
        }

        public Integer doubleAge(int age) {
            return age << 1;
        }

        public String getPositionX() {
             return myTransaction.getPositionX();
        }


    }

    @Data
    @Builder
    public static class MyTransaction {

        private String positionX;
        private String positionY;
        private String positionH;

        private String getValue() {
            return this.positionX;
        }


        private String decorate(String original) {
            return original + " " + positionX;
        }
    }

}