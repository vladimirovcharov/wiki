package org.example.wikiproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static org.example.wikiproducer.constant.AppConstant.TOPIC_WIKI_RECENT_CHANGE;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC_WIKI_RECENT_CHANGE).build();
    }
}