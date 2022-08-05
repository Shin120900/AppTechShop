package com.example.techshop.retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    public static retrofit2.Retrofit retrofit;
    final static String IP = "192.168.1.4";//192.168.1.164

    public static retrofit2.Retrofit getInstance(){
        if(retrofit == null){

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("http://"+IP+":8000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
