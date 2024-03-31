package org.example.wikiproducer;

import org.example.wikiproducer.service.WikiProducerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WikiProducerApplication implements CommandLineRunner {

	private final WikiProducerService wikiProducerService;

    public WikiProducerApplication(WikiProducerService wikiProducerService) {
        this.wikiProducerService = wikiProducerService;
    }

    public static void main(String[] args) {
		SpringApplication.run(WikiProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		wikiProducerService.sendMessage();
	}
}
