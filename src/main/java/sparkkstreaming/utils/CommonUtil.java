package sparkkstreaming.utils;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.security.auth.SecurityProtocol;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function0;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-15 14:56
 **/
public class CommonUtil {
    private static  Logger logger=null;
    public static final  Logger getLogger(Class clazz){
        logger=Logger.getLogger(clazz);
        return logger;
    }

    /**
     * 设置日志级别
     * @param level  设置日志级别  WARN  INFO ERROR
     */
    public static void setLoggerLevel(Level level){
        Logger.getLogger("org").setLevel(level);
    }

    /**
     * 根据配置中 sparkLocal 属性是否为true如果为true默认 以本地方式运行spark 用于测试
     * @param conf spark配置
     * @param local  true or false
     */
    public static void setMaster(SparkConf conf,Boolean local){
        if(local){
            conf.setMaster("local[*]");
        }
    }


    /**
     * 创建高可用的sparkstreammingcontext   如果重新启动 可以从checkPoint恢复元数据
     * @param checkPointPath  检查点路径
     * @param createContextFunc   创建ssc的函数
     * @return  ssc
     */
    public static JavaStreamingContext getJavaStreamingContext(String checkPointPath, Function0<JavaStreamingContext> createContextFunc){
        JavaStreamingContext ssc = JavaStreamingContext.getOrCreate(checkPointPath, createContextFunc);
        return ssc;
    }


    /**
     * 获取 kerberos 认证的 sparkstreaming kafka params配置
     * @param bootServers  kafka 服务器
     * @param groupId  消费组id
     * @param userName  认证用户
     * @param passWord  认证密码
     * @return   kafkaParams
     */
    public static Map<String,Object> getKafkaParams(String bootServers,String groupId,String userName,String passWord){
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootServers);
        kafkaParams.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        kafkaParams.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        kafkaParams.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        kafkaParams.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
                SecurityProtocol.SASL_PLAINTEXT.name);   //  kafkaParams.put("security.protocol","SASL_PLAINTEXT");
        kafkaParams.put("sasl.mechanism","PLAIN");
        kafkaParams.put("sasl.jaas.config","");
        return kafkaParams;
    }

    /**
     * 获取kafka 生产者客户端
     * @param userName  认证用户
     * @param password  认证密码
     * @return  生产者配置
     */
    public static  Properties getProducerConfig(String bootServers,String userName, String password){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
                SecurityProtocol.SASL_PLAINTEXT.name);   //  properties.put("security.protocol","SASL_PLAINTEXT");
        properties.put("sasl.mechanism","PLAIN");
        properties.put("sasl.jaas.config","");
        return properties;
    }


    public static void main(String[] args) {
        Logger logger = getLogger(DateUtil.class);
        logger.debug("nihao");
    }
}
