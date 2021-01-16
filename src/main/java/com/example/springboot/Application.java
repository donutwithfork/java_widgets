package com.example.springboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.example.springboot.exception.WrongStorageConfigurationException;
import com.example.springboot.repository.WidgetStorageFactory;
import com.example.springboot.repository.WidgetStorageInterface;
import com.example.springboot.service.WidgetService;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application {

	@Value("${dbType}")
	private String dbType;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public WidgetService WidgetService() throws WrongStorageConfigurationException {
		return new WidgetService(WidgetStorage());
	}

	@Bean
	public WidgetStorageInterface WidgetStorage() throws WrongStorageConfigurationException {
		return new WidgetStorageFactory(this.dbType).getImplementation();
    }

	@Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

}
