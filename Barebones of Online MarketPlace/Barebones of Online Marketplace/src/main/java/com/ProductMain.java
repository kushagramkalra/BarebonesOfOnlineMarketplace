package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com")
public class ProductMain {
    public static void main(String args[]){
        SpringApplication.run(ProductMain.class, args);
    }
}
