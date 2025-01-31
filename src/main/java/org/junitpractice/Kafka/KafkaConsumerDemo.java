package org.junitpractice.Kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemo {
    private final static String TOPIC_NAME="arrisepp-hyd";
    public static  void main(String[] args){
        String bootstrapServers="127.0.0.1:9092";
        String groupId="Group99";

        Properties properties=new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String,String> consumer=new KafkaConsumer<>(properties);
        final  Thread mainThread=Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                    System.out.println("Detected a shutdown lets exit by calling consumer.wakeup()");
                    consumer.wakeup();

                    try{
                        mainThread.join();
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
            }
        });
        try{
            consumer.subscribe(Arrays.asList(TOPIC_NAME));
            // poll
            while(true){
                ConsumerRecords<String,String> consumerRecords=consumer.poll(Duration.ofMillis(100));
                for(ConsumerRecord<String,String> consumerRecord:consumerRecords){
                    System.out.println("Key "+consumerRecord.key()+", Value"+consumerRecord.value());
                    System.out.println("Partition: "+consumerRecord.partition()+", Offset:"+consumerRecord.offset());
                }
            }

        } catch (WakeupException e) {
            System.out.println("Wake up exception");
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            consumer.close();
            System.out.println("THe consumer is now closed");
        }
    }
}
