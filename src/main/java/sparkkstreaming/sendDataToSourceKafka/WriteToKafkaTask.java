package sparkkstreaming.sendDataToSourceKafka;

import sparkkstreaming.utils.ConfigurationManager;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-16 10:31
 **/
public class WriteToKafkaTask  implements Callable {
    private File file;
    private MyKafkaUtils kafkaUtils;

    public WriteToKafkaTask(File file, MyKafkaUtils kafkaUtils) {
        this.file = file;
        this.kafkaUtils = kafkaUtils;
    }

    public AtomicInteger call() throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
            String line="";
            atomicInteger.getAndAdd(1);
            Properties prop = this.kafkaUtils.getProducerConfig(ConfigurationManager.getProperty(""), ConfigurationManager.getProperty(""));
            KafkaProducer<String, String> producer = new KafkaProducer<>(prop);
            ProducerRecord producerRecord = this.kafkaUtils.getProducerRecord(this.kafkaUtils.getTopic(), line);
            Future send = producer.send(producerRecord);
            System.out.println(send.get().toString());
        }

        return atomicInteger;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public MyKafkaUtils getKafkaUtils() {
        return kafkaUtils;
    }

    public void setKafkaUtils(MyKafkaUtils kafkaUtils) {
        this.kafkaUtils = kafkaUtils;
    }
}
