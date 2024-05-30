package org.spring.webjs.repositiries;

import jakarta.annotation.PostConstruct;
import org.spring.webjs.data.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> products;

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public Product getProductByID(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .get();
    }

    public void addProduct(String title, Integer quantity) {
        products.add(new Product(title, quantity));
    }

    public void deleteByID(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    @PostConstruct
    public void init() {
        products = new ArrayList<>(List.of(
                new Product("Bread", 150),
                new Product("Milk", 80),
                new Product("Eggs", 65)
        ));

    }
}
