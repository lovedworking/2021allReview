package effecitiveLambdaAndStreams.chapter9.observer;

import java.util.List;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-11 12:44
 **/
public interface MySubject {
    void  registerObserver(MyObserver myObserver);
    void  notifyObserver(String message);
}
