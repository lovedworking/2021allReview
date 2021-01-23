package sparkkstreaming.algorithm.union;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fendoukaoziji
 * @time 2019-11-21 15:13
 * @action 功能描述
 **/
public class SparkLeftOutJoin {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("TestLeftJoin").setMaster("local[*]");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        //useSparkRDD(jsc);
        useSparkStructure(spark);
    }

    /**
     * use java RDD
     * @param jsc  javaSparkContext
     */
    public static void useSparkRDD(JavaSparkContext jsc){
        //step1   读取 users并且创建对应rdd
        JavaRDD<String> users = jsc.textFile("union1");
        JavaPairRDD<String, Tuple2<String, String>> usersRDD = users.mapToPair(s -> {
            String[] userRecord = s.split(",");
            Tuple2<String, String> location = new Tuple2<String, String>("L", userRecord[1]);
            return new Tuple2<String, Tuple2<String, String>>(userRecord[0], location);
        });
        // step2  读取交易并且创建rdd
        JavaRDD<String> transactions = jsc.textFile("union2");
        JavaPairRDD<String, Tuple2<String, String>> transactionsRDD = transactions.mapToPair(s -> {
            String[] transactionRecord = s.split(",");
            Tuple2<String, String> product = new Tuple2<>("P", transactionRecord[1]);
            return new Tuple2<String, Tuple2<String, String>>(transactionRecord[2], product);
        });
        //step3 union
        JavaPairRDD<String, Tuple2<String, String>> unionRDD = transactionsRDD.union(usersRDD);

        //step4 groupbykey  相同用户 进行聚合操作 javaPairRDD(userId,List<Tuple>)
        JavaPairRDD<String, Iterable<Tuple2<String, String>>> groupedRDD = unionRDD.groupByKey();
        List<Tuple2<String, Iterable<Tuple2<String, String>>>> groupedCollect = groupedRDD.collect();
        groupedCollect.forEach(System.out::println);
        //step5 拼接为product-location 对应的键值对
        JavaPairRDD<String, String> productLocationsRDD = groupedRDD.flatMapToPair(iter -> {
            Iterable<Tuple2<String, String>> pairs = iter._2;
            String location = "UNKNOW";
            List<String> products = new ArrayList<>();
            for (Tuple2<String, String> t2 : pairs) {
                if (t2._1.equals("L")) {
                    location = t2._2;
                } else {
                    // t2._1.equals("P")
                    products.add(t2._2);
                }
            }
            List<Tuple2<String, String>> kvList = new ArrayList<Tuple2<String, String>>();
            for (String product : products) {
                kvList.add(new Tuple2<String, String>(product, location));
            }
            return kvList.iterator();
        });

        //step6 聚合相同key的键值对
        JavaPairRDD<String, Iterable<String>> productLocations = productLocationsRDD.groupByKey();
        List<Tuple2<String, Iterable<String>>> debug3 = productLocations.collect();
        System.out.println("—————————————debug3 开始—————————————————————");
        for(Tuple2<String,Iterable<String>> t2:debug3){
            System.out.println("debug3  "+t2._1+""+t2._2);
        }
        System.out.println("—————————————debug3 结束—————————————————————");
        JavaPairRDD<String, Tuple2<Set<String>, Integer>> productByUniqueLocations = productLocations.mapValues((Iterable<String> s) -> {
            Set<String> uniqueLocations = new HashSet<>();
            for (String location : s) {
                uniqueLocations.add(location);
            }
            return new Tuple2<Set<String>, Integer>(uniqueLocations, uniqueLocations.size());
        });
        List<Tuple2<String, Tuple2<Set<String>, Integer>>> debug4 = productByUniqueLocations.collect();
        debug4.forEach(System.out::println);
        System.exit(0);
    }

    /**
     * use SparkSession
     * @param sparkSession sparkSession
     *
     */
    public static void useSparkStructure(SparkSession sparkSession){
        Dataset<String> users = sparkSession.read().textFile("union1");
        Dataset<String> transactions = sparkSession.read().textFile("union2");
        users.createOrReplaceTempView("users");
        transactions.createOrReplaceTempView("transactions");
        users.show();
        transactions.show();
        //需要指定对应列进行join
        Dataset<Row> userTable= sparkSession.sql("select * from users");
        userTable.show();
        Dataset<Row> transTable = sparkSession.sql("select * from transactions");
        transTable.show();
    }
}



















