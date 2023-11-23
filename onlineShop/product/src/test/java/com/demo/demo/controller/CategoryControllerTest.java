package com.demo.demo.controller;

import com.demo.demo.model.Category;

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

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class CategoryControllerTest {


        @Autowired
        private WebApplicationContext webApplicationContext;

        @Autowired

        private CategoryRepository categoryRepository;

        private MockMvc mockMvc;
        private final ObjectMapper objectMapper = new ObjectMapper();

        @BeforeEach
        public void setUp() {
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
          Category category1 = new Category(1L,"Cloths","woman");
            Category category2= new Category(2L,"Cloths","man");
          categoryRepository.saveAll(List.of(category1, category2));
        }

        @AfterEach
        void tearDown() {
           categoryRepository.deleteAll();
        }

        @Test
        void store_Valid_Created() throws Exception {
            Category category = new Category();
            String body = objectMapper.writeValueAsString(category);
            MvcResult mvcResult = mockMvc.perform(post("/categories")
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON)

            ).andExpect(status().isCreated()).andReturn();

            assertTrue(mvcResult.getResponse().getContentAsString().contains("Another category"));
        }}











