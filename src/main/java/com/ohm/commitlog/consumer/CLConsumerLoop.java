package com.ohm.commitlog.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

import com.ohm.commitlog.message.CLMessage;
import com.ohm.commitlog.message.ICLMessage;



public class CLConsumerLoop extends CLAbstractConsumerLoop {
    
    private final Logger logger = LoggerFactory.getLogger(CLConsumerLoop.class);
   
    public CLConsumerLoop(KafkaConsumer<String, String> consumer, List<String> topics) {
      super(consumer, topics);
    }
    
    /**
     * Rebuild the received message to its original form and log its contents.
     * @param record
     */
    @Override
    public void process(ConsumerRecord<String, String> record) {
      logger.debug("Received message " + record.key() + " on topic " + record.topic() + " at offset " + record.offset());
      ICLMessage<String> msg = new CLMessage<String>(record.topic(), UUID.fromString(record.key()), record.value());
      logger.info("Received from " + record.topic() + " {key:" + msg.getUniqueId() + ", value: " + msg.getData().toString() + "}");
    }
  }
