package com.example.techshop.repository;

import com.example.techshop.event.APIService;
import com.example.techshop.event.IRegister;
import com.example.techshop.reponse.RegisterRepone;
import com.example.techshop.request.RegisterRequest;
import com.example.techshop.retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {
    private APIService apiService;
    IRegister iRegister;

    public RegisterRepository(IRegister iRegister) {
        this.apiService = Retrofit.getInstance().create(APIService.class);
        this.iRegister = iRegister;
    }

    public void register(String frist_name, String last_name, String email, String pass){
        apiService.register(new RegisterRequest(frist_name, last_name, email, pass)).enqueue(new Callback<RegisterRepone>() {
            @Override
            public void onResponse(Call<RegisterRepone> call, Response<RegisterRepone> response) {
                if(response.body()!=null){
                    iRegister.getSuccessful();
                }else {
                    iRegister.onMessenger("Loi");
                }
            }

            @Override
            public void onFailure(Call<RegisterRepone> call, Throwable t) {
                iRegister.onMessenger("Loi");
            }
        });
    }
}
