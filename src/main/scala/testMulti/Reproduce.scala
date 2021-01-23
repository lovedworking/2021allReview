package testMulti

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

/**https://stackoverflow.com/questions/41674069/running-threads-in-spark-dataframe-foreachpartition
 * @author fendoukaoziji
 * create 2019-10-22 15:43
 * description 功能描述
 **/
object Reproduce extends  App {
  val sc = new SparkContext("local", "reproduce")
  val sqlContext = new SQLContext(sc)

  import sqlContext.implicits._

  val df = sc.parallelize(Seq(1)).toDF("number").groupBy("number").count()

  df.foreachPartition { iterator =>
    val f = Future(iterator.toVector)
    Await.result(f, Duration.Inf)
  }


}
