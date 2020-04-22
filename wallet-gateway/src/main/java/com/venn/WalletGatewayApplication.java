package com.venn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @version 1.0.0
 * @date 2020/4/22
 */
@SpringBootApplication
public class WalletGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/about")
                        .uri("https://www.baidu.com"))
                .build();
    }
}
