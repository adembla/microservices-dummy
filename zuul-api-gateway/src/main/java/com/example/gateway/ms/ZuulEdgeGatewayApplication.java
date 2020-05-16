package com.example.gateway.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//@EnableZuulProxy
//@EnableDiscoveryClient
@SpringBootApplication
@EnableAuthorizationServer
public class ZuulEdgeGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulEdgeGatewayApplication.class, args); 
	}
}
