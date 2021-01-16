package com.example.springboot.repository;

import com.example.springboot.exception.WrongStorageConfigurationException;

public class WidgetStorageFactory 
{
	private final String dbType;

	public WidgetStorageFactory(String dbType) {
		this.dbType = dbType;
	}

	public WidgetStorageInterface getImplementation() throws WrongStorageConfigurationException {
		if (this.dbType.equals("memory")) {
			return new WidgetInMemoryStorage();
		}
		if (this.dbType.equals("h2")) {
			return new WidgetH2Storage();
		}
		throw new WrongStorageConfigurationException();
	}
}
