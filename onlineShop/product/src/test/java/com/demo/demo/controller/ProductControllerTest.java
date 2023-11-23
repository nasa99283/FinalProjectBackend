package com.demo.demo.controller;

import com.demo.demo.model.Product;
import com.demo.demo.repository.CategoryRepository;
import com.demo.demo.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Product product1 = new Product(1L, "T-shirt", "summerseason", List.of(categoryRepository.getReferenceById(1L)));
        Product product2 = new Product(2L, "T-shirt", "summerseason", List.of(categoryRepository.getReferenceById(2L)));

        productRepository.saveAll(List.of(product1, product2));
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void store_Valid_Created() throws Exception {
       Product product = new Product();
        String body = objectMapper.writeValueAsString(product);
        MvcResult mvcResult = mockMvc.perform(post("/products")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Another course"));
    }


    @Test
    void getAll_Valid_AllCourses() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Intro to Java"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Databases"));
    }
    }




