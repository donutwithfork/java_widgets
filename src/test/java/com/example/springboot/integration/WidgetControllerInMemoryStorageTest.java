package com.example.springboot.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.springboot.controller.WidgetController;
import com.example.springboot.model.Widget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(WidgetController.class)
public class WidgetControllerInMemoryStorageTest {
    @Autowired
	private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    // тут наверное стоило замокать сервис и бахнуть как-то грамотно, но я решил сделать в лоб чтобы не тратить лишнее время
    @Test
    public void createSomeWidgetAndGetListSuccess() throws Exception 
    {
        Widget expectedWidget = new Widget(0, 1, 1, 4, 4, 2);
        Widget widgetDto = new Widget(0, 1, 1, 4, 4, 2);
        String json = this.mapper.writeValueAsString(widgetDto);

        MvcResult mvcResult = this.mockMvc.perform(post("/widget")
                .contentType("application/json")
                .content(json)
            )
            .andExpect(status().isOk())
            .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        Widget responseWidget = this.mapper.readValue(actualResponseBody, Widget.class);
        assertEquals(expectedWidget, responseWidget);


        Widget expectedWidget2 = new Widget(1, 1, 1, 4, 4, 2);
        mvcResult = this.mockMvc.perform(post("/widget")
                .contentType("application/json")
                .content(json)
            )
            .andExpect(status().isOk())
            .andReturn();
        actualResponseBody = mvcResult.getResponse().getContentAsString();
        responseWidget = this.mapper.readValue(actualResponseBody, Widget.class);
        assertEquals(expectedWidget2, responseWidget);

        mvcResult = this.mockMvc.perform(get("/widget")).andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        
        actualResponseBody = mvcResult.getResponse().getContentAsString();
        Widget[] listResult = mapper.readValue(actualResponseBody, Widget[].class);
        Widget[] expectedResult = {
            new Widget(1, 1, 1, 4, 4, 2),
            new Widget(0, 1, 1, 4, 4, 3),
        };
        assertEquals(expectedResult.length, listResult.length);
        assertEquals(expectedResult[0], listResult[0]);
        assertEquals(expectedResult[1], listResult[1]);

	}

}
