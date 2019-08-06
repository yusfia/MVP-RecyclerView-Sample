package com.sample.pro.sample_of_mvp_and_rv.model;

import com.google.gson.annotations.SerializedName;

public class EntityResponse<T> {
    @SerializedName("success")
    private boolean success;
    @SerializedName("items")
    private T item;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "EntityResponse{" +
                "success=" + success +
                ", item=" + item +
                '}';
    }
}
