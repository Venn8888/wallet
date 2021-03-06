package com.venn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author venn
 * @version 1.0.0
 * @date 2019/11/4 16:48
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableScheduling
public class WalletApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletApiApplication.class, args);
        System.out.println("api is started");
    }
}
