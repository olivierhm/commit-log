package com.ohm.commitlog.producer;

import java.util.Properties;

import com.ohm.commitlog.message.CLMessageFactory;
import com.ohm.commitlog.message.ICLMessageFactory;

/**
 * Sample Kafka message producer
 *
 */
public class CLProducer {
    // TODO - Move these default values to a common module and remove duplication from CLConsumer.
    private static final String TOPIC = "commitlog";
    private static final String KAFKA_SERVER_URL = "localhost";
    private static final int KAFKA_SERVER_PORT = 9092;
 
    public static void main(String[] args) {
        String topic = (args.length == 0) ? TOPIC + "." + "default" : TOPIC + "." + args[0];

        Properties properties = new Properties();
        properties.put("bootstrap.servers", KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
        properties.put("client.id", topic);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        // Each client has its own message factory with its specific commit ID built in.
		ICLMessageFactory<String> messageFactory = new CLMessageFactory<String>(topic);

        // TBD: start multiple client threads randomly on a list of topics either configured or passed in as arguments
        CLProducerThread producer = new CLProducerThread(properties, messageFactory);
        producer.start();
    }
}
