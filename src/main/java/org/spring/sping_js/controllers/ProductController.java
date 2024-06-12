package org.spring.sping_js.controllers;

import org.spring.sping_js.dto.ProductDto;

import org.spring.sping_js.entities.Product;
import org.spring.sping_js.exceptions.ItemNotFoundException;
import org.spring.sping_js.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> findProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name="min_price", defaultValue = "0") Double min,
            @RequestParam(name="max_price", required = false) Double max,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (max == null) {
            max = Double.MAX_VALUE;
        }
        if (page < 1) {
            page = 1;
        }
        return productService.find(min, max, titlePart, page).map(product -> new ProductDto(product));
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product not found, id: " + id)));
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return new ProductDto(productService.saveNewProduct(new Product(productDto)));
    }

    @PutMapping
    @Transactional
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productService.findById(productDto.getId())
                .orElseThrow(() -> new ItemNotFoundException("Product not found, id: " + productDto.getId()));
        if (productDto.getPrice() != null) {
            product.setPrice(productDto.getPrice());
        }
        if (productDto.getTitle() != null) {
            product.setTitle(productDto.getTitle());
        }
        return new ProductDto(productService.saveNewProduct(product));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
