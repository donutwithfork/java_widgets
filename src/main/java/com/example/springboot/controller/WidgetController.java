package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

import com.example.springboot.model.Widget;
import com.example.springboot.service.WidgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class WidgetController {
	private WidgetService widgetService;

	@Autowired
	public WidgetController(WidgetService widgetService) {
		this.widgetService = widgetService;
	}

	@PostMapping(value = "/widget", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Widget createWidget(@RequestBody Widget newWidgetDto) {
		// validate new Widget???
		return this.widgetService.saveWidget(newWidgetDto);
	}

	@PutMapping("/widget/{id}")
	@ResponseBody
	// @Transactional(isolation = Isolation.SERIALIZABLE)
	// @Transactional // должно работать по идее
	public Widget updateWidget(@PathVariable(required = true) long id, @RequestBody Widget newWidgetDto) {
		// #TODO ДОБАВИТЬ АТОМАРНОСТЬ ИЗМЕНЕНИЯМ
		this.widgetService.updateWidget(newWidgetDto);
		return newWidgetDto;
	}
	
	//#TODO получить по списку
	//#TODO добавить лимит сюда
	//#TODO сортировать по zIndex
	@GetMapping("/widget")
	@ResponseBody
	public Collection<Widget> getWidgetList() {
		return this.widgetService.getWidgetList();
	}

	@GetMapping("/widget/{id}")
	@ResponseBody
	public Widget getWidgetById(@PathVariable(required = true) int id) {
		return this.widgetService.findWidgetById(id);
	}

	@DeleteMapping("/widget/{id}")
	@ResponseBody
	public void removeWidgetById(@PathVariable(required = true) int id) throws Exception {
		Widget deletedWidget = this.widgetService.removeWidgetById(id);
		if (deletedWidget == null) 
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Widget not found");
		}
	}
}
