package sparkkstreaming.algorithm;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import scala.Tuple2;
import scala.collection.generic.Sorted;

import javax.validation.constraints.Size;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;

/**
 * Input:
 *  *
 *  *    name, time, value
 *  *    x,2,9
 *  *    y,2,5
 *  *    x,1,3
 *  *    y,1,7
 *  *    y,3,1
 *  *    x,3,6
 *  *    z,1,4
 *  *    z,2,8
 *  *    z,3,7
 *  *    z,4,0
 *  *    p,1,10
 *  *    p,3,60
 *  *    p,4,40
 *  *    p,6,20
 *  *
 *  * Output: generate a time-series looking like this:
 *  *
 *  *       t1   t2   t3   t4  t5     t6
 *  *  x => [3,  9,   6]
 *  *  y => [7,  5,   1]
 *  *  z => [4,  8,   7,   0]
 *  *  p => [10, null, 60, 40, null , 20]
 *  *
 *  *  x => [(1,3), (2,9), (3,6)]            where 1 < 2 < 3
 *  *  y => [(1,7), (2,5), (3,1)]            where 1 < 2 < 3
 *  *  z => [(1,4), (2,8), (3,7), (4,0)]     where 1 < 2 < 3 < 4
 *  *  p => [(1,10), (3,60), (4,40), (6,20)] where 1 < 3 < 4 < 6
 * @author fendoukaoziji
 * @time 2019-10-24 18:03
 * @action 功能描述
 **/
public class SecondSortUsingCombineKey {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("TestSort").setMaster("local[*]");
        final JavaSparkContext jsc = new JavaSparkContext(conf);
        // 1 read data
        JavaRDD<String> lines = jsc.textFile("data/nameTime");
        //2  create (key,value)  pairs  key  name  value  <time,value>
        JavaPairRDD<String, Tuple2<Integer, Integer>> stringTimePairs = lines.mapToPair(s -> {
            String[] split = s.split(",");//x,2,5
            System.out.println(split[0] + "," + split[1] + "," + split[2]);
            Tuple2<Integer, Integer> timeValue = new Tuple2<>(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            return new Tuple2<String, Tuple2<Integer, Integer>>(split[0], timeValue);
        });
        //how to use combineByKey() to use combineByKey
        //three funtions  f1,f2,f3
        //combine(f1,f2,f3)
        //f1 create a combiner data structure   f2  merge a value into a combined data structure
        //f3  merge two combiner data structures

        //f1  craete a combiner data structure
        Function<Tuple2<Integer,Integer>, SortedMap<Integer,Integer>> createCombiner
                =(Tuple2<Integer,Integer> x)->{
            Integer time = x._1;
            Integer value = x._2;
            SortedMap<Integer, Integer> map = new TreeMap<>();
            map.put(time,value);
            return map;

        };
        //f2   merge a value into a combiner data structure
        Function2<SortedMap<Integer,Integer>,Tuple2<Integer,Integer>,SortedMap<Integer,Integer>> mergeValue
            =(SortedMap<Integer,Integer> map,Tuple2<Integer,Integer> x)->{
            Integer time = x._1;
            Integer value = x._2;
            map.put(time,value);
            return map;
        };



    }
}
























