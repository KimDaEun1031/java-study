package org.daeun.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

public class WatchOffsetOnRebalance implements ConsumerRebalanceListener {

    private String name;

    public WatchOffsetOnRebalance(String name) {
        this.name = name;
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        System.out.println(name + " Revoked " + partitions);
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        System.out.println(name + " Assigned " + partitions);
    }

    @Override
    public void onPartitionsLost(Collection<TopicPartition> partitions) {
        System.out.println(name + " Lost " + partitions);
    }
}
