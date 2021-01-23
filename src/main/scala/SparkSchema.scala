import org.apache.spark.sql.SparkSession

object SparkSchema {
    def main(args: Array[String]): Unit = {
        val spark = SparkSession
            .builder()
            .appName("Spark SQL basic example")
            .config("spark.some.config.option", "some-value")
            .getOrCreate()

        val jsonDf=spark.range(1).selectExpr("""
            '{"myJSONKey" : {"myJSONValue" : [1, 2, 3]}}' as jsonString""")
        //scala
        jsonDf.selectExpr(
            "json_tuple(jsonString, '$.myJSONKey.myJSONValue[1]') as column").show(2)
    }


}
