package com.sample.pro.sample_of_mvp_and_rv.repository;

import com.sample.pro.sample_of_mvp_and_rv.model.EntityResponse;
import com.sample.pro.sample_of_mvp_and_rv.model.Item;
import com.sample.pro.sample_of_mvp_and_rv.network.ItemService;

import java.util.List;

import io.reactivex.Observable;

public class ItemRepository {

    private ItemService itemService;

    public ItemRepository(ItemService itemService){
        this.itemService = itemService;
    }

    public Observable<EntityResponse<List<Item>>> getAllItems(){
        return itemService.fetchAllItems();
    }

    public Observable<EntityResponse<Item>> getItemById(int id){
        return itemService.fetchItemById(id);
    }
}
