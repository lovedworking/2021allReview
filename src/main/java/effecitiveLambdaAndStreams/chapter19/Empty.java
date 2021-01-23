package effecitiveLambdaAndStreams.chapter19;

/**
 * @author fendoukaoziji
 * @create 2019-10-21 18:50
 * @description 功能描述
 **/
public class Empty<T>  implements MyList<T>{
    @Override
    public T head() {
        throw new UnsupportedOperationException();
    }
    public MyList<T> tail(){
        throw new UnsupportedOperationException();
    }


}
