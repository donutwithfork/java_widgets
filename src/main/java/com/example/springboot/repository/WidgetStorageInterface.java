package com.example.springboot.repository;

import java.util.Collection;

import com.example.springboot.model.Widget;

public interface WidgetStorageInterface 
{
	public Collection<Widget> getWidgetList();

	public Widget findWidgetById(int id);

	public Widget saveWidget(Widget newWidgetDto);

	public Widget updateWidget(Widget widgetDto, Widget actualWidget);

	public Widget removeWidgetById(int id);
}
