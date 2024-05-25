package org.spring.demo.repository;

import jakarta.annotation.PostConstruct;
import org.spring.demo.misc.Product;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductsRepository {

    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(List.of(
                new Product("Milk"),
                new Product("Bread"),
                new Product("Cheese")
        ));
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> getProductByID(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public void addProduct(String name) {
        products.add(new Product(name));
    }

    public void deleteProductByID(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public void deleteAllProducts() {
        products.clear();
    }
}
