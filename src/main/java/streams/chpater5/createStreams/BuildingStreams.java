package streams.chpater5.createStreams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-09-30 21:09
 **/
public class BuildingStreams {
    public static void main(String[] args) throws IOException {
       // fromValues();
       // fromFile();
        fromFunction();
    }

    public static void fromValues(){
        Stream<String> stream = Stream.of("java", "make", "world", "happy");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    public static void fromFile() throws IOException {
        try(        Stream<String> testWord = Files.lines(Paths.get("./testWord"), Charset.defaultCharset())){
            long count = testWord.flatMap(x -> Arrays.stream(x.split("")))
                    .distinct()
                    .count();
            System.out.println(count);
        }

    }

    public static void fromFunction(){
        Stream.iterate(10,n->n*2)
                .limit(10)
                .forEach(System.out::println);
    }

}


























