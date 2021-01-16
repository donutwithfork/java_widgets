package com.example.springboot.repository;

import com.example.springboot.exception.WrongStorageConfigurationException;

public class WidgetStorageFactory 
{
	public static String MEMORY_STORAGE = "memory";
	public static String H2_STORAGE = "h2";
	private final String dbType;

	public WidgetStorageFactory(String dbType) {
		this.dbType = dbType;
	}

	public WidgetStorageInterface getImplementation() throws WrongStorageConfigurationException {
		if (this.dbType.equals(WidgetStorageFactory.MEMORY_STORAGE)) {
			return new WidgetInMemoryStorage();
		}
		if (this.dbType.equals(WidgetStorageFactory.H2_STORAGE)) {
			return new WidgetH2Storage();
		}
		throw new WrongStorageConfigurationException();
	}
}
