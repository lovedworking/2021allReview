package effecitiveLambdaAndStreams.chapter9;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-10 22:12
 **/
public class EffectiveLambdaAndStreams {
    public static void main(String[] args) {
        testStream();

    }

    public static void testStream(){
        List<String> strings = Arrays.asList("nihao", "woshi");
        List<String> nihao = strings.stream().filter(x -> x.equals("nihao"))
                .map(x -> x + "filter后")
                .collect(Collectors.toList());
        System.out.println(nihao);
    }
}
