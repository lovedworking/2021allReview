package sparksql;

import org.apache.spark.sql.SparkSession;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author fendoukaoziji
 * @time 2019-11-01 18:23
 * @action 功能描述
 **/
public class SqlParse {
    public static void main(String[] args) {
        Logger.getLogger("org").setLevel(Level.INFO);
        SparkSession spark = SparkSession.builder().appName("sqlParse").master("local[*]").getOrCreate();
        spark.read().json("student")
                .createOrReplaceTempView("student");
        spark.sql("select name from student where age>18").show();
    }
}
