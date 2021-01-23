package effecitiveLambdaAndStreams.chapter19;

/**
 * @author fendoukaoziji
 * @create 2019-10-21 18:52
 * @description 功能描述
 **/
public class Test {
    public static void main(String[] args) {
        MyList<Integer> list = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
        System.out.println(list.head());
    }
}
