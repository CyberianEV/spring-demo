package org.spring.sping_js.validators;

import org.spring.sping_js.dto.ProductDto;
import org.spring.sping_js.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getTitle() == null || productDto.getTitle().isBlank()) {
            errors.add("A product cannot have an empty title");
        }
        if (productDto.getPrice() == null || productDto.getPrice() < 0) {
            errors.add("A product cannot be less than 0");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
