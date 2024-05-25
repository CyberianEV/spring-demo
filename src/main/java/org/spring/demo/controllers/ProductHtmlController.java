package org.spring.demo.controllers;

import org.spring.demo.misc.Product;
import org.spring.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductHtmlController {

    private ProductsRepository productsRepository;

    @Autowired
    public ProductHtmlController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping("/api/v1/products")
    public String getAllProducts(Model model) {
        List<Product> products = productsRepository.getProducts();
        model.addAttribute("items", products);
        return "main";
    }

    @GetMapping("/api/v1/products/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Optional<Product> wantedProduct = productsRepository.getProductByID(id);
        model.addAttribute("product", wantedProduct.orElseGet(Product::new));
        return "show_product";
    }

    @GetMapping("/api/v1/add_product")
    public String getAddingProductForm() {
        return "new_product";
    }

    @PostMapping("/api/v1/products")
    public String addProduct(@RequestParam String name, Model model) {
        productsRepository.addProduct(name);
        List<Product> products = productsRepository.getProducts();
        model.addAttribute("items", products);
        return "main";
    }

}
