package com.example.techshop.event;

import com.example.techshop.reponse.CartReponse;
import com.example.techshop.reponse.DetailProductResponse;
import com.example.techshop.reponse.ProductResponse;
import com.example.techshop.reponse.RegisterRepone;
import com.example.techshop.reponse.UserReponse;
import com.example.techshop.request.CartRequest;
import com.example.techshop.request.RegisterRequest;
import com.example.techshop.request.RemoveFromCartRequest;
import com.example.techshop.request.UserRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    //Product
    @GET("product/app")
    Call<ProductResponse> getAll();
    @GET("product/laptop")
    Call<ProductResponse> getLapTop();
    @GET("product/smartphone")
    Call<ProductResponse> getSmartPhone();
    @GET("product/part")
    Call<ProductResponse> getPart();
    @GET("product/{id}")
    Call<DetailProductResponse> getDetail(@Path("id") Integer id);

    //User
    @POST("auth/login")
    Call<UserReponse> login(@Body UserRequest userRequest);
    @POST("register")
    Call<RegisterRepone> register(@Body RegisterRequest registerRequest);

    //Cart
    @GET("cart")
    Call<CartReponse> getCart(@Header("Authorization") String token);
    @POST("cart/add")
    Call<CartReponse> addToCart(@Header("Authorization") String token, @Body CartRequest cartRequest);
    @POST("cart/remove")
    Call<CartReponse> removeFromCart(@Header("Authorization") String token, @Body RemoveFromCartRequest removeFromCartRequest);
    @POST("cart/delete")
    Call<CartReponse> deleteProductFromCart(@Header("Authorization") String token, @Body CartRequest cartRequest);
    @DELETE("cart")
    Call<CartReponse> clearCart(@Header("Authorization") String token);
}
