package org.spring.sping_js.entities;

import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class Cart {
    private List<ProductInCart> productsInCart;
    private static Integer counter = 1;

    @Data
    @NoArgsConstructor
    public class ProductInCart {
        private Integer idInCart;
        private Product product;
        private Integer quantity;

        public ProductInCart(Product product) {
            this.product = product;
            quantity = 1;
            idInCart = counter++;
        }
    }

    @PostConstruct
    public void init() {
        productsInCart = new ArrayList<>();
    }
}
