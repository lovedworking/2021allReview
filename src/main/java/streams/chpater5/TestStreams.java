package streams.chpater5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-09-30 20:35
 **/
public class TestStreams {
    public static void main(String[] args) {
        Trader raou1 = new Trader("Raol", "Cambridge");
        Trader Lilyy = new Trader("Lily", "beijing");
        Trader Lihua = new Trader("Lihua", "shanghai");
        Trader brain = new Trader("Sunjian", "hangzhou");
        List<Transations> transations = Arrays.asList(new Transations(raou1, 2011, 300),
                new Transations(Lihua, 2012, 1000),
                new Transations(brain, 2011, 400),
                new Transations(Lilyy, 2012, 710),
                new Transations(Lilyy, 2013, 720),
                new Transations(Lilyy, 2014, 750)
        );
        List<Transations> tr2011 = transations.stream().filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transations::getValue))
                .collect(Collectors.toList());
        Optional<Transations> smallestTransation = transations.stream().min(Comparator.comparing(Transations::getValue));



    }
}
























