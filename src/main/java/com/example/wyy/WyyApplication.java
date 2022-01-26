package com.example.wyy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class WyyApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(WyyApplication.class, args);
    }
}


