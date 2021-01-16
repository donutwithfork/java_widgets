package com.example.springboot.repository;

import java.util.Collection;

import com.example.springboot.model.Widget;


public class WidgetH2Storage implements WidgetStorageInterface
{
	// тут надо запилить работу с h2 грамотно

	@Override
	public Collection<Widget> getWidgetList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Widget findWidgetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Widget addNewWidget(Widget newWidgetDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeWidgetById(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Widget updateWidget(Widget widgetDto, Widget actualWidget) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
