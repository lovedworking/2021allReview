package sparkkstreaming.sparksinkkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Level;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function0;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import sparkkstreaming.utils.CommonUtil;
import sparkkstreaming.utils.ConfigurationManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-16 14:46
 **/
public class SparkSinkKafkaRecoverable {
    public static void main(String[] args) throws InterruptedException {
        CommonUtil.setLoggerLevel(Level.WARN);
        sinkToRedis();
    }

    /**
     * 消费kafak 将数据推送到redis
     * @throws InterruptedException
     */
    private static void sinkToRedis() throws InterruptedException {
        //create context with a second batch interval
        SparkConf conf = new SparkConf().setAppName("javaSparkSinkRedisRecoverable");
        //set  spark local
        CommonUtil.setMaster(conf,ConfigurationManager.StringtoBoolean(ConfigurationManager.getProperty("sparkLocal")));
        //创建一个可恢复的streamingcontext
        Function0<JavaStreamingContext>  createContextFunc= () -> createContext(conf);
        JavaStreamingContext jssc = CommonUtil.getJavaStreamingContext(
                ConfigurationManager.getProperty("spark.CheckPointPath"), createContextFunc);
        jssc.start();
        jssc.awaitTermination();

        

    }

    /**
     * 创建jssc的函数
     * @param sparkConf sparkconf配置
     * @return jssc
     */
    private static JavaStreamingContext createContext(SparkConf sparkConf){
        Long second = ConfigurationManager.StringtoLong(ConfigurationManager.getProperty("spark.Durations"));
        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(second));
        //设置检查点
        jssc.checkpoint(ConfigurationManager.getProperty("spark.CheckPointPath"));
        String brokers=ConfigurationManager.getProperty("kafka.BootServers");
        String groupId=ConfigurationManager.getProperty("kafka.GroupId");
        String sourceTopic=ConfigurationManager.getProperty("kafka.SourceTopic");
        String kafkaUserName=ConfigurationManager.getProperty("kafka.SourceUserName");
        String kafkaPassword=ConfigurationManager.getProperty("kafka.SourcePassword");
        Set<String> topicSet = new HashSet<>(Arrays.asList(sourceTopic.split(",")));
        Map<String, Object> kafkaParams = CommonUtil.getKafkaParams(brokers, groupId, kafkaUserName, kafkaPassword);
        JavaInputDStream<ConsumerRecord<String, String>> message = KafkaUtils.createDirectStream(jssc, LocationStrategies.PreferConsistent()
                , ConsumerStrategies.Subscribe(topicSet, kafkaParams));
        JavaDStream<String> lines = message.map(ConsumerRecord::value);
        generateRedisData(lines);
        return jssc;
    }

    private static void generateRedisData(JavaDStream<String> lines) {
        lines.print();
        //转换操作都在这里面做


    }

}
