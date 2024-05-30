package org.spring.webjs.controllers;

import org.spring.webjs.data.Product;
import org.spring.webjs.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getProductList() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProductByID(@PathVariable Long id) {
        productService.deleteProductByID(id);
    }

    @GetMapping("/products/change_quantity")
    public void changeQuantity(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeProductQuantity(productId, delta);
    }
}
