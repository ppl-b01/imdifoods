package com.imdifoods.imdifoodswebcommerce.controller;

import com.imdifoods.imdifoodswebcommerce.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    private final String SENDER_EMAIL = "test@gmail.com";
    private final String SENDER_MESSAGE = "this is a test message over here";

    @Test
    void postMessageTest() throws Exception {
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders
                .post("/message/send")
                .param("sender-email", SENDER_EMAIL)
                .param("sender-message", SENDER_MESSAGE);

        mockMvc.perform(postRequest)
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void whenPostMessage_ShouldSaveMessage() throws Exception {
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders
                .post("/message/send")
                .param("sender-email", SENDER_EMAIL)
                .param("sender-message", SENDER_MESSAGE);
        mockMvc.perform(postRequest)
                .andExpect(status().is3xxRedirection());

        verify(messageService).saveMessage(SENDER_EMAIL, SENDER_MESSAGE);
    }
}

