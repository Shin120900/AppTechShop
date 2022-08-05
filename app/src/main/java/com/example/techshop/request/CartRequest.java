package com.example.techshop.request;

public class CartRequest {
    private int product_id;

    public CartRequest(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
