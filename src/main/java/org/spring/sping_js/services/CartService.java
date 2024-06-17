package org.spring.sping_js.services;

import lombok.RequiredArgsConstructor;
import org.spring.sping_js.entities.Cart;
import org.spring.sping_js.entities.Product;
import org.spring.sping_js.exceptions.ItemNotFoundException;
import org.spring.sping_js.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Cart getCart() {
        return cartRepository.getCart();
    }

    public Cart addProductToCart(Product product) {
        Optional<Cart.ProductInCart> productInCart = cartRepository.findProductInCartByProductId(product.getId());
        if (productInCart.isEmpty()) {
            return cartRepository.addProductToCart(product);
        }
        productInCart.get().setQuantity(productInCart.get().getQuantity() + 1);
        return cartRepository.getCart();
    }

    public void removeProductFromCart(Integer idInCart) {
        cartRepository.removeProductFromCart(idInCart);
    }

    public Cart changeProductInCartQuantity(Integer idInCart, Integer delta) {
        Cart.ProductInCart productInCart = cartRepository.findProductInCartById(idInCart)
                .orElseThrow(() -> new ItemNotFoundException("Unable to change quantity, " +
                        "product not found in cart, id: " + idInCart));
        Integer quantity = productInCart.getQuantity() + delta;
        if (quantity > 0) {
            productInCart.setQuantity(quantity);
            return cartRepository.getCart();
        }
        return cartRepository.getCart();
    }
}
