package org.spring.sping_js.controllers;

import lombok.RequiredArgsConstructor;
import org.spring.sping_js.converters.ProductConverter;
import org.spring.sping_js.dto.ProductDto;

import org.spring.sping_js.entities.Product;
import org.spring.sping_js.exceptions.ItemNotFoundException;
import org.spring.sping_js.services.ProductService;
import org.spring.sping_js.validators.ProductValidator;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @GetMapping
    public Page<ProductDto> findProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Double min,
            @RequestParam(name = "max_price", required = false) Double max,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(min, max, titlePart, page).map(productConverter::entityToDto);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ItemNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.saveNewProduct(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
