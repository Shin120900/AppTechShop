package com.example.techshop.event;

import com.example.techshop.reponse.CartReponse;
import com.example.techshop.reponse.UserReponse;

public interface IGetCart {
    void getSuccessful(CartReponse cartReponse, int total);
    void onMessenger (String mes);
}
