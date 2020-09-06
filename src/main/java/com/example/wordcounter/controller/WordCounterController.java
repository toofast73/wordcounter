package com.example.wordcounter.controller;

import com.example.wordcounter.service.WordCounterService;
import com.example.wordcounter.service.console.writer.Pill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class WordCounterController {

    private final WordCounterService wordCounterService;

    Logger logger = LoggerFactory.getLogger(WordCounterController.class);


    @Value("${hello.greeting}")
    private String greeting;

    private Pill pill;

    public WordCounterController(WordCounterService wordCounterService, Pill pill) {
        this.wordCounterService = wordCounterService;
        this.pill = pill;
    }

    @PostMapping("/wordCount")
    public WordCountResponse wordCounter(@RequestBody WordCountRequest wordCountRequest){

        Map<String, Long> stringLongMap = wordCounterService.countWord(wordCountRequest.getText());

        WordCountResponse response = new WordCountResponse();
        response.setIdRequest(wordCountRequest.getIdRequest());
        response.setMapCounts(stringLongMap);
        return response;
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name){

        logger.error("ТРЕВОГА");
        logger.warn("Осторожно");
        logger.info("Для информации: все работает");
        logger.debug("Я зашел в метод greet");

        return greeting + name;

    }


}
