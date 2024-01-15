package com.gsm.platfra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.gsm.platfra"})
public class PlatfraApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatfraApplication.class, args);
	}

}
