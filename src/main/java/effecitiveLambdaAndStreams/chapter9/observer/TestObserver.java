package effecitiveLambdaAndStreams.chapter9.observer;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-11 12:49
 **/
public class TestObserver {
    public static void main(String[] args) {
        LoveChinaSubject loveChinaSubject = new LoveChinaSubject();
        loveChinaSubject.registerObserver(new RenMinBlogs());
        loveChinaSubject.registerObserver(new TaiWan());
        loveChinaSubject.registerObserver((String message)->{
            if("wohenhao".equals(message))
                System.out.println("haode");
        });
        loveChinaSubject.notifyObserver("zhonghua");
        loveChinaSubject.notifyObserver("wohenhao");
    }
}
