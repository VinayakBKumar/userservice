package com.dev.userservice.configs.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${KAFKA_BOOTSTRAP_SERVER}")
    private String bootstrapAddress;

    @Bean
    public Map<String, Object> producerConfigs(){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                Object.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                org.springframework.kafka.support.serializer.JsonSerializer.class);

        return  configProps;
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }



//    @Bean
//    public ProducerFactory<String, EmailNotificationRequestDto> emailNotificationRequestDtoProducerFactory() {
//        Map<String, Object> configProps = producerConfigs();
//        configProps.put(
//                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                org.springframework.kafka.support.serializer.JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, EmailNotificationRequestDto> emailNotificationRequestDtoKafkaTemplate() {
//        return new KafkaTemplate<>(emailNotificationRequestDtoProducerFactory());
//    }


//        public ProducerFactory<String, String> producerFactory() {
//            Map<String, Object> configProps = new HashMap<>();
//            configProps.put(
//                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                    bootstrapAddress);
//            configProps.put(
//                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//                    StringSerializer.class);
//            configProps.put(
//                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                    StringSerializer.class);
//            return new DefaultKafkaProducerFactory<>(configProps);
//        }
//
//        @Bean
//        public KafkaTemplate<String, String> kafkaTemplate() {
//            return new KafkaTemplate<>(producerFactory());
//        }

}
