package com.example.demo.form;

import javax.validation.constraints.*;

public class ProductForm {

    @NotBlank(message = "Name can't blank")
    @Size(min = 1, max = 20, message = "Name between 1 and 20 letters")
    private String name;

    @NotNull(message = "Price can't empty")
    @Min(value = 0, message = "Price can't be negative")
    private Integer price;

    public ProductForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
