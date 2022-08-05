package com.example.techshop.event;

public interface ICartAdapter {
    void addCount(int id);
    void subCount(int id);
    void deleteProduct(int id);
}
