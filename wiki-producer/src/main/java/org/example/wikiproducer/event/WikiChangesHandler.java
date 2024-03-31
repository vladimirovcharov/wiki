package org.example.wikiproducer.event;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class WikiChangesHandler implements BackgroundEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikiChangesHandler.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public WikiChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onOpen() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void onClosed() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) {
        LOGGER.info("event data -> {}", messageEvent.getData());
        kafkaTemplate.send(topic, messageEvent.getData());
    }

    @Override
    public void onComment(String s) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void onError(Throwable throwable) {
        throw new RuntimeException("Not implemented");
    }
}
