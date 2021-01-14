package com.example.springboot;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.example.springboot.controller.WidgetController;
import com.example.springboot.repository.WidgetInMemoryStorage;
import com.example.springboot.service.WidgetService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// а вот тут я не понял(
	// когда распихал зависимости и по директориям разнёс начали сыпать исключения мол "ты шо тут уже есть контроллер, покайся"
	// wut?
	// @Bean
    // public WidgetController WidgetController() {
    //     return new WidgetController(WidgetService());
	// }
	
	@Bean
    public WidgetService WidgetService() {
        return new WidgetService(WidgetInMemoryStorage());
    }

	@Bean
    public WidgetInMemoryStorage WidgetInMemoryStorage() {
        return new WidgetInMemoryStorage();
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
