package com.stackroute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.stackroute.converter.DateToTimeConverter;
import com.stackroute.converter.TimeToDateConverter;

import java.util.Arrays;

@Configuration
public class CustomConverterConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
            new DateToTimeConverter(),
            new TimeToDateConverter()
        ));
    }
}

