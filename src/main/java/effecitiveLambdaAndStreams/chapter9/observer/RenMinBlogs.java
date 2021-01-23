package effecitiveLambdaAndStreams.chapter9.observer;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-11 12:42
 **/
public class RenMinBlogs implements MyObserver {
    @Override
    public void acceptedMessage(String message) {
        if("zhonghua".equals(message))
            System.out.println("中华人民共和国万岁||" +message);
    }
}
