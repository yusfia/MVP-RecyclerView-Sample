package com.sample.pro.sample_of_mvp_and_rv.network;

public interface LoadDataCallback<T> {
    void onDataLoaded(T data);
    void onDataFailure(Throwable t);
}
