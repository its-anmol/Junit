package org.junitpractice.KafkaCluster;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junitpractice.Kafka.KafkaProducerDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaConfig {


    public static KafkaProducer<String, String> getKafkaProducer(String bootstrapServers){
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(properties);
    }

    public static void sendMessage(String TOPIC_NAME, Producer producer, String message){
        ProducerRecord<String,String> producerRecord=new ProducerRecord<>(TOPIC_NAME,message);
        producer.send(producerRecord,callBack());
    }

    public static void sendMessage(String TOPIC_NAME,Producer producer,int partition, String key,String message){
        ProducerRecord<String,String>  producerRecord=new ProducerRecord<>(TOPIC_NAME,partition,key,message);
        producer.send(producerRecord,callBack());
    }

    public static void sendMessage(String TOPIC_NAME,Producer producer,String key,String message){
        ProducerRecord<String,String>  producerRecord=new ProducerRecord<>(TOPIC_NAME,key,message);
        producer.send(producerRecord,callBack());
    }

    public static Callback callBack(){
        Callback callback = new Callback() {
            Logger logger= LoggerFactory.getLogger(KafkaProducerDemo.class);
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    logger.info("Successfully received the details as: \n" +
                            "Topic = " + recordMetadata.topic() + "\n" +
                            "Partition = " + recordMetadata.partition() + " \n" +
                            "Offset = " + recordMetadata.offset() + "\n" +
                            "Timestamp = " + recordMetadata.timestamp());
                } else {
                    logger.error("Can't produce, error occurred", e);
                }
            }
        };
        return callback;
    }

    public static KafkaConsumer<String, String> getKafkaConsumer(String BootstrapServers, String groupId){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", BootstrapServers);
        properties.put("group.id", groupId);
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        properties.put("auto.offset.reset", "earliest");
        return new KafkaConsumer<>(properties);
    }

    public static void pollConsumer(KafkaConsumer<String, String> consumer) {
        try {
            while (true) {
                consumer.poll(100).forEach(record -> {
                    System.out.println("Consumed message: " + record.value() + " from topic: " + record.topic()+" partition "+record.partition());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
