package org.spring.sping_js.controllers;

import lombok.RequiredArgsConstructor;
import org.spring.sping_js.converters.CartConverter;
import org.spring.sping_js.converters.ProductConverter;
import org.spring.sping_js.dto.CartDto;
import org.spring.sping_js.dto.ProductDto;
import org.spring.sping_js.entities.Product;
import org.spring.sping_js.services.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;
    private final ProductConverter productConverter;

    @GetMapping
    public CartDto getCart() {
        return cartConverter.entityToDto(cartService.getCart());
    }

    @PostMapping
    public CartDto addProductToCart(@RequestBody ProductDto productDto) {
        Product product = productConverter.dtoToEntity(productDto);
        return cartConverter.entityToDto(cartService.addProductToCart(product));
    }

    @PutMapping
    public CartDto changeProductQuantityInCart(
            @RequestParam(name = "id_in_cart") Integer idInCart,
            @RequestParam(name = "delta") Integer delta
    ) {
        return  cartConverter.entityToDto(cartService.changeProductInCartQuantity(idInCart, delta));
    }

    @DeleteMapping("/{id}")
    public void removeProductFromCart(@PathVariable(name = "id") Integer idInCart) {
        cartService.removeProductFromCart(idInCart);
    }
}
