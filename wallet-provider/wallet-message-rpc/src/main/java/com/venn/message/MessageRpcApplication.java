package com.venn.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/31 14:03
 */
@SpringBootApplication
@EnableDiscoveryClient
//@MapperScan(basePackages = "com.venn.message.mapper")
public class MessageRpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageRpcApplication.class, args);
    }
}
