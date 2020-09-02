package com.example.wordcounter.service;

import com.example.wordcounter.controller.WordCountRequest;
import com.example.wordcounter.controller.WordCountResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WordCounterService {

    public WordCountResponse countWord(WordCountRequest wordCountRequest) {
        WordCountResponse wordCountResponse = new WordCountResponse();
        wordCountResponse.setIdRequest(wordCountRequest.getIdRequest());
        List<String> listStrings = Arrays.asList(wordCountRequest.getText().toLowerCase().split("\\W+"));
        for (String el : listStrings) {
            if (wordCountResponse.getMapCounts().containsKey(el)) {
                Long currentCount;
                currentCount = wordCountResponse.getMapCounts().get(el);
                wordCountResponse.getMapCounts().put(el, currentCount + 1);
            } else {
                wordCountResponse.getMapCounts().put(el, 1L);
            }
        }
        return wordCountResponse;
    }

}
