package org.daeun.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Sender {

    private KafkaProducer<String, String> producer;

    public Sender() {
        this.producer = new KafkaProducer<String, String>(senderProps());
    }

    public void close() {
        producer.close();
    }

    public void sendMessage(String topic, String key, String value) {
        producer.send(new ProducerRecord<>(topic, key, value));
        System.out.printf("%s, Producer Message : %s%n", LocalDateTime.now(), value);
    }

    public Map<String, Object> senderProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.28:9092");
        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
}
