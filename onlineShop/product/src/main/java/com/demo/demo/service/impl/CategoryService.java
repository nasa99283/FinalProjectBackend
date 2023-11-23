package com.demo.demo.service.impl;

import com.demo.demo.service.interfaces.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements CategoryServiceInterface {

    @Autowired
    private com.demo.demo.repository.ProductRepository productRepository;

    @Autowired
    private com.demo.demo.repository.CategoryRepository categoryRepository;

    public void saveCategory(com.demo.demo.DTO.CategoryDTO category) {
        com.demo.demo.model.Category newCategory = new  com.demo.demo.model.Category();
        newCategory.setTitle(category.getTitle());
        newCategory.setDescription(category.getDescription());
        newCategory.setProduct(productRepository.findById(category.getProductId()).get());
       categoryRepository.save(newCategory);
    }
}
