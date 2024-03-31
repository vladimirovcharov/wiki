package org.example.wikiconsumer.service;

import org.example.wikiconsumer.entity.WikiData;
import org.example.wikiconsumer.repository.WikiDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static org.example.wikiconsumer.constant.AppConstant.GROUP_ID;
import static org.example.wikiconsumer.constant.AppConstant.TOPIC_WIKI_RECENT_CHANGE;

@Service
public class WikiConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikiConsumerService.class);
    private final WikiDataRepository wikiDataRepository;

    public WikiConsumerService(WikiDataRepository wikiDataRepository) {
        this.wikiDataRepository = wikiDataRepository;
    }

    @KafkaListener(topics = TOPIC_WIKI_RECENT_CHANGE, groupId = GROUP_ID)
    public void consume(String eventMessage) {
        LOGGER.info("Event message received -> {}", eventMessage);

        WikiData wikiData = new WikiData();
        wikiData.setWikiEventData(eventMessage);
        wikiDataRepository.save(wikiData);
    }
}
