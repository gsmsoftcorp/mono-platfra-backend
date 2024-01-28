package com.gsm.platfra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.gsm.platfra"}, exclude = { SecurityAutoConfiguration.class })
public class PlatfraApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatfraApplication.class, args);
	}

}
