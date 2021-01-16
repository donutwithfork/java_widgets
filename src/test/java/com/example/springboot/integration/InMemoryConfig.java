package com.example.springboot.integration;

import com.example.springboot.controller.WidgetController;
import com.example.springboot.exception.WrongStorageConfigurationException;
import com.example.springboot.repository.WidgetStorageFactory;
import com.example.springboot.repository.WidgetStorageInterface;
import com.example.springboot.service.WidgetService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryConfig {
    private String dbType = WidgetStorageFactory.MEMORY_STORAGE;
    
    @Bean
	public WidgetStorageInterface WidgetStorage() throws WrongStorageConfigurationException {
		return new WidgetStorageFactory(this.dbType).getImplementation();
    }
    @Bean
    public WidgetService WidgetService() throws WrongStorageConfigurationException {
        return new WidgetService(WidgetStorage());
    }
    @Bean
    public WidgetController WidgetController() throws WrongStorageConfigurationException {
        return new WidgetController(WidgetService());
    }
}
