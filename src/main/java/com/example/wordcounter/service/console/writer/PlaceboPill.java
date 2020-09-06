package com.example.wordcounter.service.console.writer;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("default")
public class PlaceboPill implements Pill{
    @Override
    public void causeEffect() {
        System.out.println("Противовирусные от министерства здравоохранения");
    }
}
