package effecitiveLambdaAndStreams.chapter9.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-11 12:47
 **/
public class LoveChinaSubject implements MySubject {
    private final List<MyObserver> observers=new ArrayList<>();
    @Override
    public void registerObserver(MyObserver myObserver) {
            this.observers.add(myObserver);
    }

    @Override
    public void notifyObserver(String message) {
        observers.forEach(o->o.acceptedMessage(message));

    }
}
