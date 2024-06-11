package org.spring.sping_js.services;

import org.spring.sping_js.entities.Product;
import org.spring.sping_js.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByPriceBetween(Double min, Double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
