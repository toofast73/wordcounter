package com.example.wordcounter;

import com.example.wordcounter.controller.WordCountRequest;
import com.example.wordcounter.service.console.writer.Pill;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class WordCounterApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private Pill pill;

    @Value("${hello.greeting}")
    String s;

    @Test
    void loggerTest() {
        System.out.println(s);
        pill.causeEffect();
    }

    @Test
    void check() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        WordCountRequest wordCountRequest = new WordCountRequest(1L, "Grut! I am grut!");
        objectMapper.writerFor(WordCountRequest.class).writeValue(new File("demo.json"), wordCountRequest);
        //выполним запрос с обычный строкой
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/wordCount")
                .content(objectMapper.writerFor(WordCountRequest.class).writeValueAsString(wordCountRequest))
                .contentType("application/json")
        );

        //проверим статус и содержимое овтета
        result.andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertThat(result.andReturn().getResponse().getContentAsString()).isEqualTo("{\"idRequest\":1,\"mapCounts\":{\"i\":1,\"grut\":2,\"am\":1}}");
    }

}
