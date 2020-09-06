package com.example.wordcounter.service.console.writer;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class BluePill implements Pill {

    @Override
    public void causeEffect() {
        System.out.println("Добро пожаловать в реальный мир, Мистер Андерсон");
    }
}
