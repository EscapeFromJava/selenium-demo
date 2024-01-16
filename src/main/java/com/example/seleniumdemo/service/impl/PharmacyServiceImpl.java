package com.example.seleniumdemo.service.impl;

import com.example.seleniumdemo.model.PharmRecord;
import com.example.seleniumdemo.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {

    private final static String PATH = "C:\\Users\\PUDGE\\IdeaProjects\\selenium-demo\\src\\main\\resources\\chromedriver.exe";

    private final TelegramBotService telegramBotService;
    private WebDriver driver;
    private List<PharmRecord> pharmRecords = new ArrayList<>();

    @Override
    public void load() {
        System.setProperty("webdriver.chrome.driver", PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
////        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
////        WebDriver driver = new ChromeDriver(options);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.acmespb.ru/preparaty/staloral_allergen_pyltsy_berezy");
    }

    @Scheduled(initialDelay = 3 * 1000, fixedDelay = 5 * 60 * 1000)
    public void parse() {
        String localDateTime = getLocalDateTimeToString();
        log.info("Запуск шедулера в " + localDateTime);
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement table = driver.findElement(By.className("result__table")).findElement(By.className("table"));
        List<WebElement> rows = table.findElements(By.className("trow"));

        List<PharmRecord> newList = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            String name = rows.get(i).findElement(By.className("name")).getText();
            String pharm = rows.get(i).findElement(By.className("pharm")).getText();
            String address = rows.get(i).findElement(By.className("address")).getText();
            String date = rows.get(i).findElement(By.className("date")).getText();
            String pricefull = rows.get(i).findElement(By.className("pricefull")).getText();

            PharmRecord pharmRecord = new PharmRecord(name, pharm, address, date, pricefull);
            newList.add(pharmRecord);
        }

        for (PharmRecord curr : newList) {
            if (!pharmRecords.contains(curr)) {
                pharmRecords = newList;
                String countTitle = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div[2]/div[3]/div/p")).getText();
                telegramBotService.sendMessage("Дата обновления данных: " + localDateTime + "\n" +
                        countTitle + "\n\n" +
                        arrayToString(pharmRecords));
                log.info("Сообщение отправлено в телеграмм");
            }
        }

    }

    private String arrayToString(List<PharmRecord> records) {
        StringBuilder sb = new StringBuilder();
        for (PharmRecord record : records) {
            sb.append(record).append("\n=========\n");
        }
        return sb.toString();
    }

    private String getLocalDateTimeToString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }

}
