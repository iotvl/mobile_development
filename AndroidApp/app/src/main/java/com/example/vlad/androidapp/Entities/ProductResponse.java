package com.example.vlad.androidapp.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("result")
    @Expose
    private Product product;

    public Integer getStatus() {
        return status;
    }

    public Object getMessage() {
        return message;
    }

    public Product getProduct() {
        return product;
    }
}
