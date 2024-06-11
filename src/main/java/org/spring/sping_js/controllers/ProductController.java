package org.spring.sping_js.controllers;

import org.spring.sping_js.entities.Product;
import org.spring.sping_js.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    @GetMapping("products/filter")
    public List<Product> findByPriceBetween(@RequestParam(defaultValue = "0") Double min,
                                            @RequestParam(defaultValue = "9999999") Double max) {
        return productRepository.findByPriceBetween(min, max);
    }
}
