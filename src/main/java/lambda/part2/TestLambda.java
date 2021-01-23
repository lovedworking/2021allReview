package lambda.part2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-09-24 10:54
 **/
public class TestLambda {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
       /* es.submit(() -> System.out.println("july"));
        es.submit(()-> {
            System.out.println("Jyly");
        });*/

       Runnable r1=()-> System.out.println("nihao");
       Runnable r2=new Runnable() {
           @Override
           public void run() {
               System.out.println("匿名内部类");
           }
       };
       proces(r1);
       proces(r2);

    }

    public static void proces(Runnable r){
        r.run();
    }
    public static void test1WithOutBlock(){
        Thread t1 = new Thread(() -> System.out.println("july"));

    }
    public static void testWithBlock(){
        Thread t2=new Thread(()-> {
            System.out.println("Jyly");
        });
    }
}
