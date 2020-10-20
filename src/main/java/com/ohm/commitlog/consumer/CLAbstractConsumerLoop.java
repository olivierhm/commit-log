package com.ohm.commitlog.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;


/*
 * Based on https://docs.confluent.io/3.0.0/clients/consumer.html#synchronous-commits
 */
public abstract class CLAbstractConsumerLoop implements Runnable {
    
    private final Logger logger = LoggerFactory.getLogger(CLAbstractConsumerLoop.class);

    protected final KafkaConsumer<String, String> consumer;
    private final List<String> topics;
    private final CountDownLatch shutdownLatch;
   

    public CLAbstractConsumerLoop(KafkaConsumer<String, String> consumer, List<String> topics) {
      this.consumer = consumer;
      this.topics = topics;
      this.shutdownLatch = new CountDownLatch(1);
    }
  
    public abstract void process(ConsumerRecord<String, String> record);
  
    public void run() {
      try {
        consumer.subscribe(topics);
  
        while (true) {
          ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(Long.MAX_VALUE));
          records.forEach(record -> process(record));
        }
      } catch (WakeupException e) {
        // ignore, we're closing
      } catch (Exception e) {
        logger.error("Error", e);
      } finally {
        consumer.close();
        shutdownLatch.countDown();
      }
    }
  
    public void shutdown() throws InterruptedException {
      consumer.wakeup();
      shutdownLatch.await();
    }
  }
