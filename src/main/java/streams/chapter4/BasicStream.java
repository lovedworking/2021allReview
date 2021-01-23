package streams.chapter4;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-09-28 10:36
 **/
public class BasicStream {
    public static void main(String[] args) {
        List<String> list = getLowCaloricDishesNamesInJava8(Arrays.asList(new Dish("ni", false, 1, Dish.Type.FISH)));
        list.forEach(System.out::println);
    }
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                .filter((d) -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }
}
