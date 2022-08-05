package com.example.techshop.event;

import com.example.techshop.reponse.DetailProductResponse;

public interface IGetDetail {
    void getSuccessful(DetailProductResponse detailProductResponse);
    void onMessenger (String mes);

}
