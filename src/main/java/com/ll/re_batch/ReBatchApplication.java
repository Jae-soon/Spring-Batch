package com.ll.re_batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing // Spring Boot App -> Batch App => 스키마 생성
public class ReBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReBatchApplication.class, args);
    }

}
