package com.example.techshop.reponse;

import com.example.techshop.models.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailProductResponse {
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataDetail dataDetail;
    public class DataDetail{
        @SerializedName("product")
        @Expose
        private Product product;

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
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

    public DataDetail getDataDetail() {
        return dataDetail;
    }

    public void setDataDetail(DataDetail dataDetail) {
        this.dataDetail = dataDetail;
    }
}
