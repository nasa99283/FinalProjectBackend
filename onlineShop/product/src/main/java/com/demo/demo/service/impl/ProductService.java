package com.demo.demo.service.impl;

import com.demo.demo.service.interfaces.ProductServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {
    @Autowired
    private com.demo.demo.repository.ProductRepository productRepository;

    public com.demo.demo.model.Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public void saveProduct(com.demo.demo.model.Product product) {
        if (product.getId() != null) {
            Optional<com.demo.demo.model.Product> optionalProduct = productRepository.findById(product.getId());
            if (optionalProduct.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Product with id " + product.getId() + " already exist");
        }
        productRepository.save(product);
    }

    public void update(Long id, com.demo.demo.model.Product product) {
        com.demo.demo.model.Product productFromDB = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));
        product.setId(productFromDB.getId());
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        com.demo.demo.model.Product productFromDB = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        productRepository.deleteById(id);
    }
}
