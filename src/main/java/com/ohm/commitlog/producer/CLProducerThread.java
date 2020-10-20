package com.ohm.commitlog.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.ohm.commitlog.message.ICLMessage;
import com.ohm.commitlog.message.ICLMessageFactory;


class CLProducerCallBack implements Callback {

    private final Logger logger = LoggerFactory.getLogger(CLProducerCallBack.class);

    private final long timeStamp;
    private final String msgKey;
    private final String msgText;


    public CLProducerCallBack(long timeStamp, String key, String message) {
        this.timeStamp = timeStamp;
        this.msgKey = key;
        this.msgText = message;
    }
 
    /**
     * onCompletion is called when Kafka acknowledges reception of the message.
     *
     * @param metadata  holds the message partition and offset or null in case of error.
     * @param exception Exception thrown during the current message processing or null in case of successful processing.
     */
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - timeStamp;
        assert metadata != null: "Fatal error sending a message to Kafka: " + exception.getMessage();
        logger.info(
            "{" + msgKey + ", " + msgText + "} sent to partition " + metadata.partition() +
            " at offset " + metadata.offset() + " in " + elapsedTime + " ms");
    }
}

public class CLProducerThread extends Thread {
    private final static int MSG_NB = 100;
    private final String client;
    private final String topic;  // The producer's Commit ID will map to the Kafka topic
    private final KafkaProducer<String, String> producer;
    private final ICLMessageFactory<String> msgFactory;
 
    public CLProducerThread(Properties properties, ICLMessageFactory<String> messageFactory) {
        this.client = properties.getProperty("client.id");
        this.msgFactory = messageFactory;
        this.topic = messageFactory.getCommitId();
        this.producer = new KafkaProducer<String, String>(properties);
    }
 
    public void run() {
        for (int msgCnt = 1; msgCnt < MSG_NB; msgCnt++) {
            
            // Create a message
            String msgText = "Test message " + msgCnt;
            ICLMessage<?> message = this.msgFactory.createMessage(msgText);
            long timeStamp = System.currentTimeMillis();
            
            // Send the message to Kafka asynchronously, with a completion callback.
            producer.send(
                new ProducerRecord<String, String>(topic, msgText),
                new CLProducerCallBack(timeStamp, message.getUniqueId(), message.getData().toString())
            );
        }
    }
}
