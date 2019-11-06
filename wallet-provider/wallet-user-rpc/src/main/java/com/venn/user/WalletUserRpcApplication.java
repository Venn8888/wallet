package com.venn.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/30 13:30
 */
@SpringBootApplication
@MapperScan(basePackages = "com.venn.user.mapper")
@EnableDiscoveryClient
@ServletComponentScan
public class WalletUserRpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletUserRpcApplication.class, args);
        System.out.println("------>run is success");
    }

}
