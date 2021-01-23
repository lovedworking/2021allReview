package sparkkstreaming.sendDataToSourceKafka;

import sparkkstreaming.utils.ConfigurationManager;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-16 10:26
 **/
public class MyKafkaUtils {
    private String topic;
    private String kafkaValue;
    private String kafkaUser;
    private String kafkaPassword;

    public MyKafkaUtils(String topic, String kafkaUser, String kafkaPassword) {
        this.topic = topic;
        this.kafkaUser = kafkaUser;
        this.kafkaPassword = kafkaPassword;
    }


    /**
     * 创建kafka record topic 和消息value
     * @param topic  主题
     * @param msg  消息值
     * @return
     */
    public ProducerRecord getProducerRecord(String topic,String msg){
        return new ProducerRecord(topic,msg);
    }


    public Properties getProducerConfig(String userName,String password){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,ConfigurationManager.getProperty("kafka.BootServers"));
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put("security.protocol","SASL_PLAINTEXT");
        properties.put("sasl.mechanism","PLAIN");
        properties.put("sasl.jaas.config","");
        return properties;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getKafkaValue() {
        return kafkaValue;
    }

    public void setKafkaValue(String kafkaValue) {
        this.kafkaValue = kafkaValue;
    }

    public String getKafkaUser() {
        return kafkaUser;
    }

    public void setKafkaUser(String kafkaUser) {
        this.kafkaUser = kafkaUser;
    }

    public String getKafkaPassword() {
        return kafkaPassword;
    }

    public void setKafkaPassword(String kafkaPassword) {
        this.kafkaPassword = kafkaPassword;
    }
}
