package com.demo.demo.controller;

import com.demo.demo.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private com.demo.demo.repository.ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public com.demo.demo.model.Product getProductById(@PathVariable(name = "id") Long productId) {
        return productRepository.findById(productId).get();
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<com.demo.demo.model.Product> getProductsByCategoryAndDepartment() {
        return productRepository.findAll();
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody @Valid com.demo.demo.model.Product product){
        productService.saveProduct(product);
    }

    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id,@RequestBody @Valid com.demo.demo.model.Product product){
        productService.update(id, product);
    }


    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
