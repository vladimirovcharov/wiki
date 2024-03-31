package org.example.wikiproducer.service;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.example.wikiproducer.event.WikiChangesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import static org.example.wikiproducer.constant.AppConstant.TOPIC_WIKI_RECENT_CHANGE;
import static org.example.wikiproducer.constant.AppConstant.WIKI_URL;

@Service
public class WikiProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikiProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WikiProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        BackgroundEventHandler backgroundEventHandler = new WikiChangesHandler(kafkaTemplate, TOPIC_WIKI_RECENT_CHANGE);
        BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(backgroundEventHandler,
                new EventSource.Builder(URI.create(WIKI_URL)));
        try (BackgroundEventSource eventSource = builder.build()) {
            eventSource.start();
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
