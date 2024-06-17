package org.spring.sping_js.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private List<ProductDtoInCart> productsDtOInCart = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ProductDtoInCart {
        private Integer idInCart;
        private ProductDto productDto;
        private Integer quantity;
    }
}
