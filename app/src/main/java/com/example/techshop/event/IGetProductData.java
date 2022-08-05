package com.example.techshop.event;

import com.example.techshop.reponse.ProductResponse;

public interface IGetProductData {
    void getAllProduct(ProductResponse productResponse);
    void getLapTop(ProductResponse productResponse);
    void getSmartPhone(ProductResponse productResponse);
    void getPart(ProductResponse productResponse);
    void onMessenger (String mes);
}
