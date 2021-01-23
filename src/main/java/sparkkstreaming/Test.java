package sparkkstreaming;

import sparkkstreaming.utils.ConfigurationManager;

import java.util.HashMap;
import java.util.function.DoubleUnaryOperator;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-15 14:30
 **/
public class Test {
    public static void main(String[] args) {
        String language = ConfigurationManager.getProperty("redis.expire");
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        System.out.println(objectObjectHashMap.size());
        System.out.println(language);
    }

    static double converter(double x, double f, double b) {
        return x * f + b;
    };

    static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }
}
