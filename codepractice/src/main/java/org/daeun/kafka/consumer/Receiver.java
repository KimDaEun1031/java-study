package org.daeun.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Receiver {

    private KafkaConsumer<String, String> consumer;
    private String name;

    public Receiver(String name, String group, String topic) {
        this.name = name;
        this.consumer = new KafkaConsumer<>(receiverProps(group));
        consumer.subscribe(Collections.singletonList(topic), new WatchOffsetOnRebalance(name));
    }

    public void close() {
        consumer.close();
    }

    public void poll() {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, String> record :
                records) {
            System.out.printf("%s, groupId = %s, name = %s, offset = %d, partition = %d, key = %s, value = %s%n",
                    LocalDateTime.now(), consumer.groupMetadata().groupId(), name,
                    record.offset(), record.partition(), record.key(), record.value());
        }
        consumer.commitSync();
    }

    public Map<String, Object> receiverProps(String group) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.28:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, name);
        return props;
    }
}
