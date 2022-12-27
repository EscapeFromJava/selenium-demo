package com.example.seleniumdemo;

import com.example.seleniumdemo.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SeleniumDemoApplication implements CommandLineRunner {

    private final ParserService parserService;

    @Autowired
    public SeleniumDemoApplication(ParserService parserService) {
        this.parserService = parserService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SeleniumDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        parserService.loadMessagesPage();
        parserService.parsePosts();
    }
}
