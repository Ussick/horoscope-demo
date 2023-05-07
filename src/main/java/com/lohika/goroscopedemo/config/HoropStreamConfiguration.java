package com.lohika.goroscopedemo.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lohika.goroscopedemo.service.KafkaMQService;
import com.lohika.goroscopedemo.service.UserDTOMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class HoropStreamConfiguration {


    @Primary
    @Bean("user-producer")
    public UserDTOMessageProducer userProducer() {
        return new KafkaMQService();
    }

}
