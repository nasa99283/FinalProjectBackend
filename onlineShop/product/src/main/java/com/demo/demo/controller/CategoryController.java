package com.demo.demo.controller;

import com.demo.demo.service.impl.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCategory(@RequestBody com.demo.demo.DTO.CategoryDTO category){
        categoryService.saveCategory(category);
    }
}
