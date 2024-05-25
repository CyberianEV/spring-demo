package org.spring.demo.misc;

public class Product {
    private Long id;
    private String name;

    private static Long counter = 1L;

    public Product() {

    }

    public Product(String name) {
        id = counter++;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
