package org.spring.demo.controllers;

import org.spring.demo.misc.Product;
import org.spring.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
public class ProductRestController {

    private ProductsRepository productsRepository;

    @Autowired
    public ProductRestController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping("/api/v1/products")
    public List<Product> getAllProducts() {
        return productsRepository.getProducts();
    }

    @GetMapping("/api/v1/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        Optional<Product> wantedProduct = productsRepository.getProductByID(id);
        return wantedProduct.orElseGet(Product::new);
    }

    @PostMapping("/api/v1/products")
    public void createProduct(@RequestBody String name) {
        productsRepository.addProduct(name);
    }

    @DeleteMapping("/api/v1/products")
    public void deleteAllProduct() {
        productsRepository.deleteAllProducts();
    }

    @DeleteMapping("/api/v1/products/{id}")
    public void deleteProductByID(@PathVariable Long id) {
        productsRepository.deleteProductByID(id);
    }
}
