package org.spring.sping_js.services;

import lombok.RequiredArgsConstructor;
import org.spring.sping_js.exceptions.ItemNotFoundException;
import org.spring.sping_js.dto.ProductDto;
import org.spring.sping_js.entities.Product;
import org.spring.sping_js.repositories.ProductRepository;
import org.spring.sping_js.repositories.specifications.ProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> find(Double minPrice, Double maxPrice, String titlePart, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessThanOrEquals(maxPrice));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterThanOrEquals(minPrice));
        }
        if (titlePart != null) {
            spec = spec.and(ProductSpecifications.titleLike(titlePart));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = findById(productDto.getId()).orElseThrow(() -> new ItemNotFoundException(
                "Unable to update product, not found in database, id: " + productDto.getId()
        ));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
