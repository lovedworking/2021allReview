package sparkSource.json;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;

/**
 * @author fendoukaoziji
 * @time 2019-10-20 15:15
 * @action 功能描述
 **/
public class TestJsonSource {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("testJson").setMaster("local[*]");
        testJson(conf);
    }
    public static void testJson(SparkConf conf){
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        // Alternatively, a DataFrame can be created for a JSON dataset represented by
        // a Dataset<String> storing one JSON object per string.
        List<String> jsonData = Arrays.asList(
                "{\"name\":\"Yin\",\"address\":{\"city\":\"Columbus\",\"state\":\"Ohio\"}}");
        Dataset<String> anotherPeopleDataset = spark.createDataset(jsonData, Encoders.STRING());
        //通过另外一个jsonDataSet创建
        Dataset<Row> json = spark.read().json(anotherPeopleDataset);

        json.show();
    }
}
