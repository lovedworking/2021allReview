package effecitiveLambdaAndStreams.chapter19;

import java.util.function.Supplier;

/**
 * @author fendoukaoziji
 * @create 2019-10-21 18:59
 * @description 功能描述
 **/
public class LazyList<T>  implements MyList<T>{
    final T head;
    final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    public MyList<T> tail() {
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
