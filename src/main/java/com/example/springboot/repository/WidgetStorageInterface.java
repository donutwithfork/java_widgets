package com.example.springboot.repository;

import java.util.Collection;

import com.example.springboot.exception.WidgetNotFoundException;
import com.example.springboot.model.Widget;

public interface WidgetStorageInterface 
{
	public Collection<Widget> getWidgetList();

	public Widget findWidgetById(int id);

	public Widget addNewWidget(Widget newWidgetDto);

	public Widget updateWidget(Widget widgetDto, Widget actualWidget) throws WidgetNotFoundException;

	public void removeWidgetById(int id) throws WidgetNotFoundException;
}
