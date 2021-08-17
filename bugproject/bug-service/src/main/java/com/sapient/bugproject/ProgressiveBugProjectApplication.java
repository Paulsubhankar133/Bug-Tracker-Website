package com.sapient.bugproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author suvpaul
 *
 */
@SpringBootApplication
@EnableMongoRepositories
@EnableSwagger2
@EnableDiscoveryClient
@RibbonClient(name = "bugservice")

@org.springframework.web.bind.annotation.CrossOrigin
public class ProgressiveBugProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgressiveBugProjectApplication.class, args);
	}

}
