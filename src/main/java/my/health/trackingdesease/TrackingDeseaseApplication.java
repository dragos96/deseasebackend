package my.health.trackingdesease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EntityScan(basePackages = {"my.health.trackingdesease.model"})
@EnableJpaRepositories(basePackages = {"my.health.trackingdesease.dao"})
@ComponentScan(basePackages = {"ro.health.trackingdesease.controllers", "my.health.trackingdesease.security.config", "my.health.trackingdesease.util", "my.health.trackingdesease.filters"})
public class TrackingDeseaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackingDeseaseApplication.class, args);
		
		
	
	}

}
