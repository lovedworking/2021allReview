package everyDayJava.chapter11Optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-13 14:22
 **/
public class TestOptional {
    public static void main(String[] args) {
        test1();
    }
    public static void test1(){
        Optional<Object> empty = Optional.empty();
        Optional<Object> object = Optional.of(new Object());
       // Optional<Object> nullRe = Optional.of(null);
        System.out.println(empty+"///"+object);

        Car ni = new Car("ni");
        Optional<Car> car = Optional.ofNullable(ni);
        Optional<Car> car2 = Optional.of(ni);

        Optional<String> name = car.map(Car::getName);
        Optional<String> we1 = car.map(Car::getWeight);
        Optional<String> weight = car2.map(Car::getWeight);

        System.out.println(car);
        System.out.println(name);
        System.out.println(weight);
        System.out.println(we1);

        // null vs Optional
        Optional.of(ni).ifPresent(System.out::println);
        Map<String,String>  testmap=new HashMap<>();
        testmap.put("null","whi");
        Optional.ofNullable(testmap.getOrDefault("nill","gou")).ifPresent(System.out::println);
    }
}
