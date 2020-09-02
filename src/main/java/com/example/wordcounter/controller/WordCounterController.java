package com.example.wordcounter.controller;

import com.example.wordcounter.service.WordCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordCounterController {

    @Autowired
    private WordCounterService wordCounterService;


    @PostMapping("/wordCount")
    public WordCountResponse wordCounter(@RequestBody WordCountRequest wordCountRequest){
        return wordCounterService.countWord(wordCountRequest);
    }

}
