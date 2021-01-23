package effecitiveLambdaAndStreams.chapter10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-12 15:10
 **/
public class FilterErrorLines {
    public static void main(String[] args) throws IOException {
        filterErrorLine();
    }

    /**
     * Files.lines 静态封装io流
     * @throws IOException
     */
    public static  void  filterErrorLine() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("./testWord"));
       // lines.forEach(System.out::println);
        List<String> what = lines.filter(line -> line.startsWith("what"))
                .collect(Collectors.toList());
        System.out.println(what);


    }
}











