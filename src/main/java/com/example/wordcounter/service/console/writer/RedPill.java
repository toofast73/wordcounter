package com.example.wordcounter.service.console.writer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service(value = "RedRedRedPill")
@Profile("test")
public class RedPill implements Pill {

    @Override
    public void causeEffect() {
        System.out.println("Мистер Андерсон, проснитесь, пора на работу");
    }
}
