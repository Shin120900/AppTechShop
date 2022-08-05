package com.example.techshop.reponse;

import com.example.techshop.models.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("current_page")
    @Expose
    private int current_page;
    @SerializedName("last_page")
    @Expose
    private int last_page;
    @SerializedName("per_page")
    @Expose
    private int per_page;
    @SerializedName("total")
    @Expose
    private int total;

    public class Data{
        @SerializedName("data")
        @Expose
        private List<Product> listProduct = new ArrayList<>();

        public List<Product> getListProduct() {
            return listProduct;
        }

        public void setListProduct(List<Product> listProduct) {
            this.listProduct = listProduct;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
