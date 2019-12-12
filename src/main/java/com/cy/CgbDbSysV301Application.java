package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CgbDbSysV301Application {

    public static void main(String[] args) {
        SpringApplication.run(CgbDbSysV301Application.class, args);
    }

}
