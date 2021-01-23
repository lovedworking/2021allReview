package effecitiveLambdaAndStreams.chapter9.observer;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-11 12:43
 **/
public class TaiWan implements MyObserver {
    @Override
    public void acceptedMessage(String message) {
        if("taiwan".equals(message))
            System.out.println("台湾早晚要回归"+message);
    }
}
