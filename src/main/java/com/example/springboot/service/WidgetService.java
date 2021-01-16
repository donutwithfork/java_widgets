package com.example.springboot.service;

import java.util.Collection;

import com.example.springboot.exception.WidgetNotFoundException;
import com.example.springboot.model.Widget;
import com.example.springboot.repository.WidgetStorageInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WidgetService {
	private WidgetStorageInterface widgetStorage;

	@Autowired
	public WidgetService(WidgetStorageInterface widgetStorage) {
		this.widgetStorage = widgetStorage;
	}

	public Collection<Widget> getWidgetList() {
		return this.widgetStorage.getWidgetList();
	}

	public void removeWidgetById(int id) throws WidgetNotFoundException {
		this.widgetStorage.removeWidgetById(id);
	}


	public Widget findWidgetById(int id) {
		Widget widget = this.widgetStorage.findWidgetById(id);
		if (widget == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Widget not found"); //#TODO не работает сообщение
		}
		return widget;
	}
	
	public Widget updateWidget(Widget widgetDto) throws WidgetNotFoundException {
		Widget actualWidget = this.widgetStorage.findWidgetById(widgetDto.getId());

		if (actualWidget == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Widget not found"); // #TODO не работает сообщение
		}

		return this.widgetStorage.updateWidget(widgetDto, actualWidget);
	}

	public Widget saveWidget(Widget newWidgetDto) {
		return this.widgetStorage.addNewWidget(newWidgetDto);
	}
}
