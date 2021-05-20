package com.mightyjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import com.mightyjava.captcha.CaptchaGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EntityScan(basePackages = {"com.mightyjava.model"})
@EnableJpaRepositories(repositoryBaseClass = SimpleJpaRepository.class,
		basePackages = {"com.mightyjava.repository"})
@ComponentScan()
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CaptchaGenerator getCaptchaGenerator() {
		return new CaptchaGenerator();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
