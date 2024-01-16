package com.example.seleniumdemo.service.impl;

import com.example.seleniumdemo.model.entity.Post;
import com.example.seleniumdemo.repository.PostRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class KataChatService {
    /*private WebDriver driver;
    private final PostRepository postRepository;
    private final TelegramBotService telegramBotService;
    private List<Post> posts;
    private final String PATH = "C:\\Users\\PUDGE\\IdeaProjects\\Kata\\selenium-demo\\src\\main\\resources\\chromedriver.exe";

    @Autowired
    public KataChatService(PostRepository postRepository, TelegramBotService telegramBotService) {
        this.postRepository = postRepository;
        this.telegramBotService = telegramBotService;
    }

    public void loadMessagesPage() {
        System.out.println(LocalTime.now() + " start to load messages page");
        System.setProperty("webdriver.chrome.driver", PATH);
//        ChromeOptions options = new ChromeOptions();
////        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
////        WebDriver driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://vk.com/");

        WebElement loginForm = driver.findElement(By.id("index_email"));
        loginForm.sendKeys("***", Keys.ENTER); // email

        WebElement passwordForm = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[2]/div/div/div/form/div[1]/div[3]/div[1]/div/input"));
        passwordForm.sendKeys("***", Keys.ENTER); //password

        WebElement leftBarMessages = driver.findElement(By.xpath("//*[@id=\"l_msg\"]/a"));
        leftBarMessages.click();

        WebElement groupChat = driver.findElement(By.xpath("//*[@id=\"im_dialogs\"]/div[1]/div[1]/div/div[1]/li[3]"));
        groupChat.click();
        System.out.println(LocalTime.now() + " finish to load messages page");
    }

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void parsePosts() {
        System.out.println(LocalTime.now() + " start to parse");
        List<WebElement> elements = driver.findElements(By.className("im-mess-stack"));

        posts = new ArrayList<>();
        for (WebElement e : elements) {
            String[] head;
            int delim;
            try {
                delim = e.getText().indexOf("\n");
                head = e.getText().substring(0, delim).split(" ");
            } catch (Exception ex) {
                continue;
            }
            String userName = head[0] + " " + head[1];
            String time = head[2];
            String message = e.getText().substring(delim).replaceAll("\n", " ").trim();
            StringBuilder fullLink = new StringBuilder("https://meet.google.com/");
            boolean meet = message.contains("meet.google.com");
            String regex = "[a-z]{3}-[a-z]{4}-[a-z]{3}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher;
            if (message.contains("https://meet.google.com/")) {
                matcher = pattern.matcher(message);
                while (matcher.find()) {
                    String link = matcher.group(0);
                    fullLink.append(link);
                }
            } else if (message.contains("meet.google.com/")) {
                fullLink = new StringBuilder(message);
            } else if (message.contains("meet.google.com")) {
                String input = e.findElement(By.className("thumbed_link__subtitle")).getAttribute("href");
                matcher = pattern.matcher(input);
                while (matcher.find()) {
                    String link = matcher.group(0);
                    fullLink.append(link);
                }
            }
            Post post = new Post(userName, time, message, fullLink.toString(), meet);
            posts.add(post);
        }
//        driver.quit();
        System.out.println(LocalTime.now() + " finish to parse");
        savePosts();
    }

    private void savePosts() {
        for (Post p : posts) {
            Post post = postRepository.findByMessage(p.getMessage());
            if (post == null) {
                postRepository.save(p);
                if (p.isMeet()) {
                    String message = p.getMessage().replaceAll("^(https?://)?([\\w-]{1,32}\\.[\\w-]{1,32})[^\\s]*", "")
                            .replaceAll("https://meet\\.google\\.com/[a-z]{3}-[a-z]{4}-[a-z]{3}", "").trim();
                    String text = p.getTime() + " " + p.getUserName() + "\n" + message + "\n" + p.getLink();
                    telegramBotService.sendMessage(text);
                }
            }
        }
        System.out.println("Posts have been saved");
    }*/
}
