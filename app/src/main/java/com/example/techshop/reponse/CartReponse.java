package com.example.techshop.reponse;

import com.example.techshop.models.Cart;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartReponse {
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataCart dataCart;
    public class DataCart{
        @SerializedName("cart")
        @Expose
        private List<Cart> carts;

        public List<Cart> getCarts() {
            return carts;
        }

        public void setCarts(List<Cart> carts) {
            this.carts = carts;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataCart getDataCart() {
        return dataCart;
    }

    public void setDataCart(DataCart dataCart) {
        this.dataCart = dataCart;
    }
}
