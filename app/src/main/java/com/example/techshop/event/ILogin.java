package com.example.techshop.event;

import com.example.techshop.reponse.UserReponse;

public interface ILogin {
    void getSuccessful(UserReponse userReponse);
    void onMessenger (String mes);
}
