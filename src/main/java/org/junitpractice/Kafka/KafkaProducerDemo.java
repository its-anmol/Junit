package org.junitpractice.Kafka;


import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Properties;

public class KafkaProducerDemo {
    private final static String TOPIC_NAME="arrisepp-hyd";
    public static void main(String[] argv){
            KafkaProducer<String,String> producer=null;
        try{
            String bootstrapServer="127.0.0.1:9092";
            Properties properties=new Properties();
            properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
            properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

            producer =new KafkaProducer<>(properties);
            ArrayList<String> employeeList= getEmployeeList();

            for (String intern:employeeList){
                Integer partitionNumber=2;
                ProducerRecord<String,String>  producerRecord=new ProducerRecord<>(TOPIC_NAME,partitionNumber,"intern",intern);
                // send data - asynchronous
                producer.send(producerRecord, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        Logger logger= LoggerFactory.getLogger(KafkaProducerDemo.class);
                        if(e==null){
                            logger.info(" successfully received the details as: \n"+
                                    "Topic = "+recordMetadata.topic()+"\n"+
                                    "Partition = " +recordMetadata.partition()+" \n"+
                                    "Offset = "+recordMetadata.offset()+"\n"+
                                    "TimeStamp = "+recordMetadata.timestamp());
                        }
                        else {
                            logger.error("Can't produce,getting error",e);
                        }
                    }
                });
                System.out.println("Succesfully sent intern name to "+intern+" to the topic");
                Thread.sleep(4000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (producer!=null){
                producer.flush();
                producer.close();
            }
        }
    }

    private  static  ArrayList<String> getEmployeeList(){
        ArrayList<String> employeeList=new ArrayList<>();
        employeeList.add("anmol");
        employeeList.add("vedika");
        employeeList.add("manoj");
        return  employeeList;
    }
}
