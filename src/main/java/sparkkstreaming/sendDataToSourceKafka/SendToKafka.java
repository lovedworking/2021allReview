package sparkkstreaming.sendDataToSourceKafka;

import sparkkstreaming.utils.ConfigurationManager;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-16 11:26
 **/
public class SendToKafka {
    public static void main(String[] args) {
        sendToKafka(args);
    }


    /**
     * 根据传入文件路径的个数 启动相应个数线程写入kafka
     * @param args  路径path的数组
     */
    private static void sendToKafka(String[] args) {
        String topic = ConfigurationManager.getProperty("kafka.SourceTopic"); //kafkaTopic
        String kafkaUserName=ConfigurationManager.getProperty("kafka.SourceUserName");
        String kafkaPassword=ConfigurationManager.getProperty("kafka.SourcePassword");
        MyKafkaUtils myKafkaUtils = new MyKafkaUtils(topic, kafkaUserName, kafkaPassword);
        ExecutorService es = Executors.newFixedThreadPool(args.length);
        Arrays.stream(args).forEach(path->{
            File file = new File(path);
            WriteToKafkaTask writeToKafkaTask = new WriteToKafkaTask(file, myKafkaUtils);
            Future submit = es.submit(writeToKafkaTask);
            try {
                AtomicInteger lines =(AtomicInteger) submit.get();
                System.out.println(lines.get());
            }  catch (Exception e) {
                e.printStackTrace();
            }
        });
        es.shutdown();
    }


}
