package lambda.part3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-09-25 08:35
 **/
public class PutLambdaIntoPratice {
    public static void main(String[] args) {

    }
    public  static String testProcessFile() throws IOException {
        try (BufferedReader bf=new BufferedReader(new FileReader("data.txt"))){//Java 7  try souorce 不需要显示释放资源
            return bf.readLine();  //which does the useful work
        }
    }
}
