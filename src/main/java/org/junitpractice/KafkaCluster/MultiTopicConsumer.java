package org.junitpractice.KafkaCluster;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junitpractice.KafkaCluster.KafkaConfig.getKafkaConsumer;
import static org.junitpractice.KafkaCluster.KafkaConfig.pollConsumer;


public class MultiTopicConsumer {

    private static final String BOOTSTRAP_SERVER="localhost:9092,localhost:9093";

    public static void main(String[] args) {

        // Consumer Group having 3 consumer
        KafkaConsumer<String, String> consumer1=getKafkaConsumer(BOOTSTRAP_SERVER,"consumerGroup1");
        KafkaConsumer<String, String> consumer2=getKafkaConsumer(BOOTSTRAP_SERVER,"consumerGroup1");
        KafkaConsumer<String, String> consumer3=getKafkaConsumer(BOOTSTRAP_SERVER,"consumerGroup1");

        consumer1.subscribe(Arrays.asList("topicA", "topicB"));
        consumer2.subscribe(Arrays.asList("topicA", "topicB"));
        consumer3.subscribe(Arrays.asList("topicA", "topicB"));

        System.out.println("Subscribed to topicA and topicB");

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(() -> pollConsumer(consumer1));
        executorService.submit(() -> pollConsumer(consumer2));
        executorService.submit(() -> pollConsumer(consumer3));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                consumer1.close();
                consumer2.close();
                consumer3.close();
                executorService.shutdown();
                System.out.println("Shutting down consumers and executor.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }
}
