package com.knowledge.KafkaAnalysisKnowledgeProducer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.knowledge.KafkaAnalysisKnowledgeProducer.constants.KafkaProducerConstants;
import com.knowledge.KafkaAnalysisKnowledgeProducer.dto.Employee;

@Configuration
public class KafkaConfiguration {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	@Value("${spring.kafka.trusted.packages:com.knowledge.KafkaAnalysisKnowledgeProducer.dto}")
	private String trustedPackages;

	 @Bean
	    public NewTopic createTopic(@Value("${spring.kafka.topic.default}") String topicName) {
	        return TopicBuilder.name(topicName).build();
	    }
	 
	 @Bean
	    public ProducerFactory<String, Object> genericProducerFactory() {
	        Map<String, Object> configProps = new HashMap<>();
	        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	        configProps.put("spring.json.trusted.packages", trustedPackages);
	        
	        return new DefaultKafkaProducerFactory<>(configProps);
	    }

	@Bean
    public KafkaTemplate<String, Object> genericKafkaTemplate() {
        return new KafkaTemplate<>(genericProducerFactory());
    }
}