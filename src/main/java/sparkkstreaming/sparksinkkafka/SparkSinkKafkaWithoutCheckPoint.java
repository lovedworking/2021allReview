package sparkkstreaming.sparksinkkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Level;
import org.apache.spark.SparkConf;
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
 * @create: 2019-10-16 14:45
 **/
public class SparkSinkKafkaWithoutCheckPoint {
    public static void main(String[] args) throws InterruptedException {
        CommonUtil.setLoggerLevel(Level.WARN);
        SparkConf conf = new SparkConf().setAppName("JavaSparkStreamingKafkaTest");
        //指定以哪种方式启动
        CommonUtil.setMaster(conf,ConfigurationManager.StringtoBoolean(ConfigurationManager.getProperty("sparkLocal")));
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));

        String brokers=ConfigurationManager.getProperty("kafka.BootServers");
        String groupId=ConfigurationManager.getProperty("kafka.GroupId");
        String sourceTopic=ConfigurationManager.getProperty("kafka.SourceTopic");
        String kafkaUserName=ConfigurationManager.getProperty("kafka.SourceUserName");
        String kafkaPassword=ConfigurationManager.getProperty("kafka.SourcePassword");
        String sinkTopic=ConfigurationManager.getProperty("kafka.SinkTopic");
        Set<String> topicSet = new HashSet<>(Arrays.asList(sourceTopic.split(",")));

        Map<String, Object> kafkaParams = CommonUtil.getKafkaParams(brokers, groupId, kafkaUserName, kafkaPassword);
        JavaInputDStream<ConsumerRecord<String, String>> message = KafkaUtils.createDirectStream(jssc, LocationStrategies.PreferConsistent()
                , ConsumerStrategies.Subscribe(topicSet, kafkaParams));

        JavaDStream<String> lines = message.map(ConsumerRecord::value);
        jssc.start();
        jssc.awaitTermination();

    }
}
