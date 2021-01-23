package sparkkstreaming.getKafkaOffsetLeader;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

/**
 * @author fendoukaoziji
 * @time 2019-10-29 21:43
 * @action 功能描述
 **/
public class TestKafkaGetOffset {
    public static void main(String[] args) {
        Properties properties = new Properties();
        KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(properties);
        final List<TopicPartition> topicPartitions=new ArrayList<TopicPartition>();
        Map<TopicPartition,Long> offsetMap=new HashMap<>();
        List<PartitionInfo> partitionInfos = consumer.partitionsFor("topic");
        partitionInfos.forEach(pinfo -> topicPartitions.add(new TopicPartition(pinfo.topic(), pinfo.partition())));
        consumer.poll(0);
        consumer.seekToEnd(topicPartitions);
        Map<TopicPartition, Long> topicPartitionLongMap = consumer.endOffsets(topicPartitions);

    }
}











