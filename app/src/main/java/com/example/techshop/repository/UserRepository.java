package com.example.techshop.repository;

import com.example.techshop.reponse.RegisterRepone;
import com.example.techshop.request.RegisterRequest;
import com.example.techshop.retrofit.Retrofit;
import com.example.techshop.event.APIService;
import com.example.techshop.event.ILogin;
import com.example.techshop.reponse.UserReponse;
import com.example.techshop.request.UserRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private APIService apiService;
    private UserReponse userReponse;
    ILogin iLogin;

    public UserRepository(ILogin iLogin) {
        this.apiService = Retrofit.getInstance().create(APIService.class);
        this.iLogin = iLogin;
    }

    public void login(String email, String password){
        apiService.login(new UserRequest(email,password)).enqueue(new Callback<UserReponse>() {
            @Override
            public void onResponse(Call<UserReponse> call, Response<UserReponse> response) {
                if(response.body()!=null){
                    userReponse = response.body();
                    iLogin.getSuccessful(userReponse);
                }else {
                    iLogin.onMessenger("Loi");
                }
            }

            @Override
            public void onFailure(Call<UserReponse> call, Throwable t) {
                iLogin.onMessenger("loi");
            }
        });
    }



}
