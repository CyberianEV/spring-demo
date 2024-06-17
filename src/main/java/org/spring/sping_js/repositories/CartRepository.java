package org.spring.sping_js.repositories;

import lombok.RequiredArgsConstructor;
import org.spring.sping_js.entities.Cart;
import org.spring.sping_js.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartRepository {
    private final Cart cart;

    public Cart getCart() {
        return cart;
    }

    public Cart addProductToCart(Product product) {
        cart.getProductsInCart().add(cart.new ProductInCart(product));
        return cart;
    }

    public void removeProductFromCart(Integer idInCart) {
        cart.getProductsInCart().removeIf(productInCart -> productInCart.getIdInCart().equals(idInCart));
    }

    public Optional<Cart.ProductInCart> findProductInCartByProductId(Long productId) {
        return cart.getProductsInCart().stream()
                .filter(productInCart -> productInCart.getProduct().getId().equals(productId))
                .findFirst();
    }

    public Optional<Cart.ProductInCart> findProductInCartById(Integer idInCart) {
        return cart.getProductsInCart().stream()
                .filter(productInCart -> productInCart.getIdInCart().equals(idInCart))
                .findFirst();
    }

    public List<Cart.ProductInCart> getAllProductsInCart() {
        return cart.getProductsInCart();
    }
}
