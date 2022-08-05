package com.example.techshop.repository;

import com.example.techshop.retrofit.Retrofit;
import com.example.techshop.event.IGetHomeData;
import com.example.techshop.event.APIService;
import com.example.techshop.reponse.CartReponse;
import com.example.techshop.reponse.ProductResponse;
import com.example.techshop.request.CartRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeProductRepository {
    private APIService APIService;
    private IGetHomeData iGetHomeData;


    private ProductResponse productResponse;


    public HomeProductRepository(IGetHomeData iGetHomeData) {
        APIService = Retrofit.getInstance().create(APIService.class);
        this.iGetHomeData = iGetHomeData;
    }



    public IGetHomeData getiGetHomeData() {
        return iGetHomeData;
    }

    public void setiGetHomeData(IGetHomeData iGetHomeData) {
        this.iGetHomeData = iGetHomeData;
    }


    public void getProductLapTop(){
        APIService.getLapTop().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.body() != null) {
                    productResponse = response.body();
                    iGetHomeData.getLapTop(productResponse);


                }else {
                    iGetHomeData.onMessenger("Lỗi");
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                iGetHomeData.onMessenger("Lỗi");
            }
        });

    }

    public void getProductSmartPhone(){
        APIService.getSmartPhone().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.body() != null) {
                    productResponse = response.body();
                    iGetHomeData.getSmartPhone(productResponse);

                }else {
                    iGetHomeData.onMessenger("Lỗi");
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                iGetHomeData.onMessenger("Lỗi");
            }
        });

    }

    public void getProductPart(){
        APIService.getPart().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.body() != null) {
                    productResponse = response.body();
                    iGetHomeData.getPart(productResponse);

                }else {
                    iGetHomeData.onMessenger("Lỗi");
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                iGetHomeData.onMessenger("Lỗi");
            }
        });

    }

    public void addToCart(String token, int id){
        APIService.addToCart("Bearer "+ token, new CartRequest(id)).enqueue(new Callback<CartReponse>() {
            @Override
            public void onResponse(Call<CartReponse> call, Response<CartReponse> response) {
                if(response.body()!=null){
                    CartReponse cartReponse = response.body();
                    if(cartReponse.isSuccess()){
                        iGetHomeData.onMessenger("Thêm sản phẩm vào giỏ hàng thành công");
                    }
                }else {
                    iGetHomeData.onMessenger("Lỗi");
                }
            }

            @Override
            public void onFailure(Call<CartReponse> call, Throwable t) {
                iGetHomeData.onMessenger("Lỗi");
            }
        });
    }








}
