package org.junitpractice.KafkaCluster;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junitpractice.Kafka.KafkaProducerDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Properties;
import java.util.Scanner;

import static org.junitpractice.KafkaCluster.KafkaConfig.getKafkaProducer;
import static org.junitpractice.KafkaCluster.KafkaConfig.sendMessage;

public class KafkaProducerExample {
    private final static String Broker1_TOPIC_NAME = "topicA";
    private final static String Broker2_TOPIC_NAME = "topicB";
    private final static String BootstrapServers = "127.0.0.1:9092,127.0.0.1:9093";

    public static void main(String[] argv) {

        Scanner sc = new Scanner(System.in);

        final KafkaProducer<String, String> producer1 = getKafkaProducer(BootstrapServers);

        try {
            int partionNumber=2;
            while(partionNumber>=0){
                String key="PARTION "+partionNumber+" KEY";
                String broker1Message= sc.nextLine();
                sendMessage(Broker1_TOPIC_NAME,producer1,partionNumber,key,broker1Message);
                String broker2Message=sc.nextLine();
                sendMessage(Broker2_TOPIC_NAME,producer1,partionNumber,key,broker2Message);
                partionNumber--;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            producer1.flush();
            producer1.close();
        }
    }
}
