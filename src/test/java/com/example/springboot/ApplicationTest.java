package com.example.springboot;

import com.example.springboot.integration.WidgetControllerInMemoryStorageTest;
import com.example.springboot.unit.WidgetInMemoryStorageTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.TestCase;

@RunWith(Suite.class)
@Suite.SuiteClasses({WidgetInMemoryStorageTest.class, WidgetControllerInMemoryStorageTest.class})
public class ApplicationTest extends TestCase {
    
}