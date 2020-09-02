package com.example.wordcounter;

import com.example.wordcounter.controller.WordCountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;

@SpringBootTest
@AutoConfigureMockMvc
class WordCounterApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void check() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        WordCountRequest wordCountRequest = new WordCountRequest(1L, "Grut! I am grut!");

        //выполним запрос с обычный строкой
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/wordCount")
                .content(objectMapper.writerFor(WordCountRequest.class).writeValueAsString(wordCountRequest))
                .contentType("application/json")
        );

        //проверим статус и содержимое овтета
        result.andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertThat(result.andReturn().getResponse().getContentAsString()).isEqualTo("{\"idRequest\":1,\"mapCounts\":{\"i\":1,\"grut\":2,\"am\":1}}");

        //зачитаем файл
        String data = FileUtils.readFileToString(new File(this.getClass().getClassLoader().getResource("LOTR.txt").getFile()), "cp1251");
        wordCountRequest = new WordCountRequest(2L, data);

        //выполним запрос с большим текстом
        result = mockMvc.perform(MockMvcRequestBuilders
                .post("/wordCount")
                .content(objectMapper.writerFor(WordCountRequest.class).writeValueAsString(wordCountRequest))
                .contentType("application/json")
                .characterEncoding("cp1251")
        );
        //проверим, что сервис справился успешно
        result.andExpect(MockMvcResultMatchers.status().isOk());
        System.out.println(result.andReturn().getResponse().getContentAsString());
    }

}
