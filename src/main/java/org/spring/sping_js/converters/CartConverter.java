package org.spring.sping_js.converters;

import lombok.RequiredArgsConstructor;
import org.spring.sping_js.dto.CartDto;
import org.spring.sping_js.entities.Cart;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final ProductConverter productConverter;

    public CartDto entityToDto(Cart cart) {
        List<CartDto.ProductDtoInCart> productsDtoInCart = new ArrayList<>();
        CartDto cartDto = new CartDto(productsDtoInCart);
        cart.getProductsInCart().stream()
                .forEach(productInCart ->
                    cartDto.getProductsDtOInCart()
                            .add(cartDto.new ProductDtoInCart(
                                    productInCart.getIdInCart(),
                                    productConverter.entityToDto(productInCart.getProduct()),
                                    productInCart.getQuantity()
                            ))
                );
        return cartDto;
    }
}
