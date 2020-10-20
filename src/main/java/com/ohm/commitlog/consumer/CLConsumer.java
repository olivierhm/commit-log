package com.ohm.commitlog.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 *  Commit Log Consumer based on Kafka 
 */
public class CLConsumer {

    // TODO - Move these default values to a common module and remove duplication from CLConsumer.
    private static final String GROUP_ID = "commitlog";
    private static final String KAFKA_SERVER_URL = "172.17.74.66";
    private static final int KAFKA_SERVER_PORT = 9092;
    
    public static void main( String[] args )
    {
        List<String> topics = new ArrayList<String>();
        if (args.length == 0) {
            topics.add(GROUP_ID);
        } else {
            // Prefix each topic to subscribe to with "commitlog"
            topics = Arrays.asList(args);
        }

        // TODO - Fetch those values from a resources file and override defaults as necessary
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
        properties.setProperty("group.id", GROUP_ID);
        properties.setProperty("client.id", GROUP_ID + ".consumer");
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        CLConsumerLoop consumerLoop = new CLConsumerLoop(consumer, topics);
        consumerLoop.run();
    }
}





