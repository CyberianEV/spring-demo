package org.spring.jpademo.controllers;

import org.spring.jpademo.entities.Product;
import org.spring.jpademo.exceptions.ItemNotFoundException;
import org.spring.jpademo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ItemNotFoundException("Product not found, id:" + id));
    }

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping("/products")
    public void saveProduct(@RequestBody Product product) {
        productService.save(product);
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
