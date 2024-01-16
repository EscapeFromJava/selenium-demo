package com.example.seleniumdemo;

import com.example.seleniumdemo.service.PharmacyService;
import com.example.seleniumdemo.service.impl.KataChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class SeleniumDemoApplication implements CommandLineRunner {

    private final KataChatService kataChatService;
    private final PharmacyService pharmacyService;

    public static void main(String[] args) {
        SpringApplication.run(SeleniumDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        kataChatService.loadMessagesPage();
//        kataChatService.parsePosts();
        pharmacyService.load();
    }
}
