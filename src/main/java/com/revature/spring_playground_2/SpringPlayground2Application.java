package com.revature.spring_playground_2;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringPlayground2Application {

	@Value("${spring.application.name}")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(SpringPlayground2Application.class, args);
	}

	@Bean
	public CommandLineRunner inspectorBean(ApplicationContext context) {
		return args -> {
			System.out.printf("Inspecting the beans provided by Spring Boot in %s", appName).println();

			String[] beans = context.getBeanDefinitionNames();
			Arrays.stream(beans).sorted().forEach(System.out::println); 

			System.out.println("Inspection complete!");
		};
	}

}
