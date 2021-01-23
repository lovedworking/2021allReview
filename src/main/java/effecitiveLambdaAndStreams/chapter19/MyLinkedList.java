package effecitiveLambdaAndStreams.chapter19;

/**
 * @author fendoukaoziji
 * @create 2019-10-21 18:48
 * @description 功能描述
 **/
public class MyLinkedList<T> implements MyList<T> {
    private final T head;
    private final MyList<T> tail;

    public MyLinkedList(T head, MyList<T> tail) {
        this.head = head;
        this.tail = tail;
    }
    @Override
    public T head() {
        return head;
    }
    public MyList<T> tail(){
        return  tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
