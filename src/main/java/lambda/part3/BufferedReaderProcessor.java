package lambda.part3;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-09-25 08:53
 **/
@FunctionalInterface
public interface BufferedReaderProcessor {
    abstract String process(BufferedReader b) throws IOException;
}
