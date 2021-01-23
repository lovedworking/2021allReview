package sparkSource.dataBaseSource;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

/**
 * @author fendoukaoziji
 * @time 2019-10-20 15:58
 *   @action 功能描述
 **/
public class SparkSqlJDBC {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("testJDBC").setMaster("local[*]");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "username");
        connectionProperties.put("password", "password");
        Dataset<Row> jdbcDF = spark.read()
                .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties);

    }
}
