package streams.chpater5;

import java.util.Arrays;
import java.util.List;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-09-30 16:35
 **/
public class Filtering {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 3, 2);
        numbers.stream().filter(i->i%2==0).distinct().forEach(System.out::println);
    }
}















