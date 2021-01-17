package com.example.springboot.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.springboot.controller.WidgetController;
import com.example.springboot.model.Widget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(WidgetController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) 
@ContextConfiguration(classes = InMemoryConfig.class)
@ActiveProfiles("test")
public class WidgetControllerInMemoryStorageTest {
    @Autowired
    private MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getEmptyListTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/widget")).andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        Widget[] listResult = mapper.readValue(actualResponseBody, Widget[].class);
        assertEquals(0, listResult.length);
    }

    @Test
    public void deleteNonExistedWidgetTest() throws Exception {
        this.mockMvc.perform(delete("/widget/0")).andExpect(status().isNotFound());
    }

    @Test
    public void updateNonExistedWidgetTest() throws Exception {
        Widget updateWidgetDto = new Widget(0, 5, 5, 5, 5, 2);
        String json = this.mapper.writeValueAsString(updateWidgetDto);

        this.mockMvc.perform(put("/widget/0")
                .contentType("application/json")
                .content(json)
            )
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWidgetSuccessTest() throws Exception {
        Widget expectedWidget = new Widget(0, 1, 1, 4, 4, 2);
        Widget widgetDto = new Widget(0, 1, 1, 4, 4, 2);
        this.createWidgetTest(widgetDto, expectedWidget);

        Widget updateWidgetDto = new Widget(0, 5, 5, 5, 5, 2);
        String json = this.mapper.writeValueAsString(updateWidgetDto);

        MvcResult mvcResult = this.mockMvc.perform(put("/widget/0")
                .contentType("application/json")
                .content(json)
            )
            .andExpect(status().isOk())
            .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        Widget responseWidget = this.mapper.readValue(actualResponseBody, Widget.class);
        assertEquals(updateWidgetDto, responseWidget);
    }

    @Test
    public void deleteWidgetTest() throws Exception {
        Widget expectedWidget = new Widget(0, 1, 1, 4, 4, 2);
        Widget widgetDto = new Widget(0, 1, 1, 4, 4, 2);

        this.createWidgetTest(widgetDto, expectedWidget);

        this.mockMvc.perform(delete("/widget/0")).andExpect(status().isNoContent());
        MvcResult mvcResult = this.mockMvc.perform(get("/widget")).andDo(print())
        .andExpect(status().isOk())
        .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        Widget[] listResult = mapper.readValue(actualResponseBody, Widget[].class);
        assertEquals(0, listResult.length);
    }

    @Test
    public void createSomeWidgetAndGetListSuccess() throws Exception {
        Widget expectedWidget = new Widget(0, 1, 1, 4, 4, 2);
        Widget widgetDto = new Widget(0, 1, 1, 4, 4, 2);
        
        this.createWidgetTest(widgetDto, expectedWidget);

        Widget expectedWidget2 = new Widget(1, 1, 1, 4, 4, 2);
        this.createWidgetTest(widgetDto, expectedWidget2);

        MvcResult mvcResult = this.mockMvc.perform(get("/widget")).andDo(print()).andExpect(status().isOk()).andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        Widget[] listResult = mapper.readValue(actualResponseBody, Widget[].class);
        Widget[] expectedResult = { new Widget(1, 1, 1, 4, 4, 2), new Widget(0, 1, 1, 4, 4, 3), };

        assertEquals(expectedResult.length, listResult.length);
        assertEquals(expectedResult[0], listResult[0]);
        assertEquals(expectedResult[1], listResult[1]);

    }

    private void createWidgetTest(Widget createDto, Widget expectedWidget) throws Exception {
        String json = this.mapper.writeValueAsString(createDto);
        MvcResult mvcResult = this.mockMvc.perform(post("/widget")
                .contentType("application/json")
                .content(json)
            )
            .andExpect(status().isCreated())
            .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        Widget responseWidget = this.mapper.readValue(actualResponseBody, Widget.class);
        assertEquals(expectedWidget, responseWidget);
    }

}
