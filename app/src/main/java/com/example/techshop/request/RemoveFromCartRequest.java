package com.example.techshop.request;

public class RemoveFromCartRequest {
    private int product_id;
    private int count;

    public RemoveFromCartRequest(int product_id) {
        this.product_id = product_id;
        this.count = 1;
    }
}
