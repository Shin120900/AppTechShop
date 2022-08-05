package com.example.techshop.repository;

import com.example.techshop.retrofit.Retrofit;
import com.example.techshop.event.IGetDetail;
import com.example.techshop.event.APIService;
import com.example.techshop.reponse.CartReponse;
import com.example.techshop.reponse.DetailProductResponse;
import com.example.techshop.request.CartRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductRepository {
    private APIService APIService;
    private IGetDetail iGetDetail;
    private DetailProductResponse detailProductResponse;

    public DetailProductRepository(IGetDetail iGetDetail) {
        this.iGetDetail = iGetDetail;
        APIService = Retrofit.getInstance().create(APIService.class);
    }

    public void getDetailProduct(int id){
        APIService.getDetail(id).enqueue(new Callback<DetailProductResponse>() {
            @Override
            public void onResponse(Call<DetailProductResponse> call, Response<DetailProductResponse> response) {
                if(response.body()!=null){
                    detailProductResponse = response.body();
                    iGetDetail.getSuccessful(detailProductResponse);
                }
            }

            @Override
            public void onFailure(Call<DetailProductResponse> call, Throwable t) {
                iGetDetail.onMessenger("Loi");
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
                        iGetDetail.onMessenger("Thêm sản phẩm vào giỏ hàng thành công");
                    }
                }else {
                    iGetDetail.onMessenger("Lỗi");
                }
            }

            @Override
            public void onFailure(Call<CartReponse> call, Throwable t) {
                iGetDetail.onMessenger("Lỗi");
            }
        });
    }
}
