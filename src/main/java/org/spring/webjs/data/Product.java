package org.spring.webjs.data;


public class Product {
    private Long id;
    private String title;
    private Integer quantity;

    private static Long counter = 1L;

    public Product(String title, Integer quantity) {
        this.title = title;
        this.quantity = quantity;
        id = counter++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
