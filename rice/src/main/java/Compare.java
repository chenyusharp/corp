import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.checkerframework.checker.units.qual.C;

/**
 * Date: 2021/9/5
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Compare {

    public static void main(String[] args) throws ParseException {
        List<Integer> compareList = new ArrayList<>();
        compareList.add(10);
        compareList.add(3);
        compareList.add(100);
        compareList.add(14);
        compareList.sort(Comparator.comparingInt(integer -> (int) integer).reversed());
        System.out.println(compareList);

        DateFormat dateFormat = DateFormat.getDateInstance();

        Person person1 = new Person("xiaoming", dateFormat.parse("2021-09-11"));
        Person person2 = new Person("xiaoqiang", dateFormat.parse("2021-09-10"));
        Person person3 = new Person("xiaohua", dateFormat.parse("2020-05-12"));
        List<Person> var0 = new ArrayList<>();
        var0.add(person1);
        var0.add(person2);
        var0.add(person3);
        List<Person> var1 = var0.stream().sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList());
        System.out.println(var1);

    }

    public static class Person {

        private String name;
        private Date age;

        public Person(String name, Date age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getAge() {
            return age;
        }

        public void setAge(Date age) {
            this.age = age;
        }


        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}