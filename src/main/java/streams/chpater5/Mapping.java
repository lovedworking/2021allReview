package streams.chpater5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-09-30 16:56
 **/
public class Mapping {
    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(1, 3, 5);
        List<Integer> numbers2 = Arrays.asList(2,4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j})
                ).collect(Collectors.toList());
        System.out.println(pairs);
    }
}
