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

    @GetMapping("/products/price/greater")
    public List<Product> findByPriceGreaterThan(@RequestParam(name = "min") Double price) {
        return productService.findByPriceGreaterThan(price);
    }

    @GetMapping("/products/price/less")
    public List<Product> findByPriceLessThan(@RequestParam(name = "max") Double price) {
        return productService.findByPriceLessThan(price);
    }

    @GetMapping("/products/price/between")
    public List<Product> findByPriceBetween(@RequestParam(defaultValue = "0") Double min,
                                            @RequestParam(defaultValue = "9999999999999999999") Double max) {
        return productService.findByPriceBetween(min, max);
    }
}
