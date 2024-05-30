package org.spring.webjs.services;

import org.spring.webjs.data.Product;
import org.spring.webjs.repositiries.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

    public Product getProductByID(Long id) {
        return productRepository.getProductByID(id);
    }

    public void deleteProductByID(Long id) {
        productRepository.deleteByID(id);
    }

    public void changeProductQuantity(Long id,  Integer delta) {
        Product product = productRepository.getProductByID(id);
        product.setQuantity(product.getQuantity() + delta);
    }
}
