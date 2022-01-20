package com.example.demo.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ProductUpdateForm {

    private Integer productId;

    @Size(max = 20, message = "Name between 1 and 20 letters")
    private String name;

    @Min(value = 0, message = "Price can't be negative")
    private Integer price;

    public ProductUpdateForm() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
