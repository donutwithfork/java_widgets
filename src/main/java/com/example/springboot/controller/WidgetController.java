package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import javax.transaction.Transactional;

import com.example.springboot.exception.WidgetNotFoundException;
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
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class WidgetController {
	private WidgetService widgetService;

	// по солиду тут должен быть интерфейс а не конкретная реализация.
	// я напишу это, но сделаю прямую реализацию)) чтобы не тратить время
	@Autowired
	public WidgetController(WidgetService widgetService) {
		this.widgetService = widgetService;
	}

	@PostMapping(value = "/widget", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.CREATED)
	public Widget createWidget(@RequestBody Widget newWidgetDto) {
		return this.widgetService.saveWidget(newWidgetDto);
	}

	@PutMapping("/widget/{id}")
	@ResponseBody
	// @Transactional(isolation = Isolation.SERIALIZABLE)
	@Transactional
	public Widget updateWidget(@PathVariable(required = true) long id, @RequestBody Widget newWidgetDto)
			throws WidgetNotFoundException {
		this.widgetService.updateWidget(newWidgetDto);
		return newWidgetDto;
	}

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
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeWidgetById(@PathVariable(required = true) int id) throws WidgetNotFoundException {
		this.widgetService.removeWidgetById(id);
	}
}
