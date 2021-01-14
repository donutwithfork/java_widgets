package com.example.springboot.repository;

public class WidgetStorageFactory 
{
	// get from config
	// return storage
	public WidgetStorageInterface getImplementation()
	{
		return new WidgetInMemoryStorage();
	}
}
