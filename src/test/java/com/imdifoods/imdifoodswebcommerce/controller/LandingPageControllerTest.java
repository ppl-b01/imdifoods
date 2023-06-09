package com.imdifoods.imdifoodswebcommerce.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(LandingPageController.class)
class LandingPageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getLandingPageTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

}
