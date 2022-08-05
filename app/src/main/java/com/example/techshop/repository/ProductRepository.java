package com.example.techshop.repository;

import com.example.techshop.retrofit.Retrofit;
import com.example.techshop.event.IGetProductData;
import com.example.techshop.event.APIService;
import com.example.techshop.reponse.CartReponse;
import com.example.techshop.reponse.ProductResponse;
import com.example.techshop.request.CartRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private APIService APIService;
    private IGetProductData iGetProductData;


    private ProductResponse productResponse;


    public ProductRepository(IGetProductData iGetProductData) {
        APIService = Retrofit.getInstance().create(APIService.class);
        this.iGetProductData = iGetProductData;
    }


    public void getAllProduct(){
        APIService.getAll().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.body() != null) {
                    productResponse = response.body();
                    iGetProductData.getAllProduct(productResponse);


                }else {
                    iGetProductData.onMessenger("Lỗi");
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                iGetProductData.onMessenger("Lỗi");
            }
        });
    }



    public void getProductLapTop(){
        APIService.getLapTop().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.body() != null) {
                    productResponse = response.body();
                    iGetProductData.getLapTop(productResponse);


                }else {
                    iGetProductData.onMessenger("Lỗi");
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                iGetProductData.onMessenger("Lỗi");
            }
        });

    }

    public void getProductSmartPhone(){
        APIService.getSmartPhone().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.body() != null) {
                    productResponse = response.body();
                    iGetProductData.getSmartPhone(productResponse);

                }else {
                    iGetProductData.onMessenger("Lỗi");
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                iGetProductData.onMessenger("Lỗi");
            }
        });

    }

    public void getProductPart(){
        APIService.getPart().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.body() != null) {
                    productResponse = response.body();
                    iGetProductData.getPart(productResponse);

                }else {
                    iGetProductData.onMessenger("Lỗi");
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                iGetProductData.onMessenger("Lỗi");
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
                        iGetProductData.onMessenger("Thêm sản phẩm vào giỏ hàng thành công");
                    }
                }else {
                    iGetProductData.onMessenger("Lỗi");
                }
            }

            @Override
            public void onFailure(Call<CartReponse> call, Throwable t) {
                iGetProductData.onMessenger("Lỗi");
            }
        });
    }
}
