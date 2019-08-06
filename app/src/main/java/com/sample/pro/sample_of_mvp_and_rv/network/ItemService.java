package com.sample.pro.sample_of_mvp_and_rv.network;

import com.sample.pro.sample_of_mvp_and_rv.model.EntityResponse;
import com.sample.pro.sample_of_mvp_and_rv.model.Item;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ItemService {
    @GET("/item/{id}")
    Observable<EntityResponse<Item>> fetchItemById(@Path("id") int itemId);
    @GET("/itemavailable")
    Observable<EntityResponse<List<Item>>> fetchAllItems();
}
