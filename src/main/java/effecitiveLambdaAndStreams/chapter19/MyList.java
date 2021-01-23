package effecitiveLambdaAndStreams.chapter19;

/**
 * @author fendoukaoziji
 * @create 2019-10-21 18:46
 * @description 功能描述
 **/
public interface MyList<T> {
    T head();
    default  boolean isEmpty(){
        return true;
    }


}
