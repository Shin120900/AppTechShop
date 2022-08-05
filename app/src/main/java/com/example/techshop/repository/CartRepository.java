package com.example.techshop.repository;

import com.example.techshop.request.CartRequest;
import com.example.techshop.request.RemoveFromCartRequest;
import com.example.techshop.retrofit.Retrofit;
import com.example.techshop.event.APIService;
import com.example.techshop.event.IGetCart;
import com.example.techshop.models.Cart;
import com.example.techshop.reponse.CartReponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {
    private APIService apiService;
    private CartReponse cartReponse;
    private IGetCart iGetCart;

    public CartRepository(IGetCart iGetCart) {
        this.apiService = Retrofit.getInstance().create(APIService.class);
        this.iGetCart = iGetCart;
    }

    public void getCart(String token){
        apiService.getCart("Bearer "+token).enqueue(new Callback<CartReponse>() {
            @Override
            public void onResponse(Call<CartReponse> call, Response<CartReponse> response) {
                if(response.body()!=null){
                    cartReponse = response.body();
                    int total=0;
                    for (Cart cart: cartReponse.getDataCart().getCarts()) {
                        total += (cart.getCount()*cart.getProduct().getPrice());
                    }
                    iGetCart.getSuccessful(cartReponse, total);
                }else {
                    iGetCart.onMessenger("Loi");
                }
            }

            @Override
            public void onFailure(Call<CartReponse> call, Throwable t) {
                iGetCart.onMessenger("Loi");
            }
        });
    }

    public void addToCart(String token, int id){
        apiService.addToCart("Bearer "+ token, new CartRequest(id)).enqueue(new Callback<CartReponse>() {
            @Override
            public void onResponse(Call<CartReponse> call, Response<CartReponse> response) {
                if(response.body()!=null){
                    cartReponse = response.body();
                    int total=0;
                    for (Cart cart: cartReponse.getDataCart().getCarts()) {
                        total += (cart.getCount()*cart.getProduct().getPrice());
                    }
                    iGetCart.getSuccessful(cartReponse, total);
                }else {
                    iGetCart.onMessenger("Lỗi");
                }
            }

            @Override
            public void onFailure(Call<CartReponse> call, Throwable t) {
                iGetCart.onMessenger("Lỗi");
            }
        });
    }

    public void removeFromCart(String token, int id){
        apiService.removeFromCart("Bearer "+ token, new  RemoveFromCartRequest(id)).enqueue(new Callback<CartReponse>() {
            @Override
            public void onResponse(Call<CartReponse> call, Response<CartReponse> response) {
                if(response.body()!=null){
                    cartReponse = response.body();
                    int total=0;
                    for (Cart cart: cartReponse.getDataCart().getCarts()) {
                        total += (cart.getCount()*cart.getProduct().getPrice());
                    }
                    iGetCart.getSuccessful(cartReponse, total);
                }else {
                    iGetCart.onMessenger("Số lượng tối thiểu là : 1");
                }
            }

            @Override
            public void onFailure(Call<CartReponse> call, Throwable t) {
                iGetCart.onMessenger("Lỗi");
            }
        });
    }

    public void deleteProductFromCart(String token, int id){
        apiService.deleteProductFromCart("Bearer "+ token, new CartRequest(id)).enqueue(new Callback<CartReponse>() {
            @Override
            public void onResponse(Call<CartReponse> call, Response<CartReponse> response) {
                if(response.body()!=null){
                    cartReponse = response.body();
                    int total=0;
                    for (Cart cart: cartReponse.getDataCart().getCarts()) {
                        total += (cart.getCount()*cart.getProduct().getPrice());
                    }
                    iGetCart.getSuccessful(cartReponse, total);
                }else {
                    iGetCart.onMessenger("loi");
                }
            }

            @Override
            public void onFailure(Call<CartReponse> call, Throwable t) {
                iGetCart.onMessenger("Loi");
            }
        });
    }


    public void clearCart(String token) {
        apiService.clearCart("Bearer "+ token).enqueue(new Callback<CartReponse>() {
            @Override
            public void onResponse(Call<CartReponse> call, Response<CartReponse> response) {
                if(response.body()!=null){
                    cartReponse = response.body();
                    int total=0;
                    for (Cart cart: cartReponse.getDataCart().getCarts()) {
                        total += (cart.getCount()*cart.getProduct().getPrice());
                    }
                    iGetCart.getSuccessful(cartReponse, total);
                }else {
                    iGetCart.onMessenger("Số lượng tối thiểu là : 1");
                }
            }

            @Override
            public void onFailure(Call<CartReponse> call, Throwable t) {
                iGetCart.onMessenger("Lỗi");
            }
        });


    }
}
