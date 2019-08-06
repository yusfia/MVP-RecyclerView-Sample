package com.sample.pro.sample_of_mvp_and_rv.presenter;

import android.util.Log;

import com.sample.pro.sample_of_mvp_and_rv.contract.MainActivityContract;
import com.sample.pro.sample_of_mvp_and_rv.model.EntityResponse;
import com.sample.pro.sample_of_mvp_and_rv.model.Item;
import com.sample.pro.sample_of_mvp_and_rv.repository.ItemRepository;
import com.sample.pro.sample_of_mvp_and_rv.view.MainActivity;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;
    private List<Item> rvItems;
    private ItemRepository itemRepository;

    public MainActivityPresenter(MainActivityContract.View view, ItemRepository itemRepository){
        this.view = view;
        this.rvItems = new ArrayList<>();
        this.view.initializeItems(rvItems);
        this.itemRepository = itemRepository;
    }

    @Override
    public void loadData() {
        itemRepository.getAllItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EntityResponse<List<Item>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EntityResponse<List<Item>> response) {
                        view.showLoadingAnimation();
                        rvItems.clear();
                        rvItems.addAll(response.getItem());
                        view.notifyItemsUpdates();
                        view.showMessage("Title", response.isSuccess()?"Success": "Response Message");
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.disposeLoadingAnimation();
                        view.showErrorMessage(e);
                    }

                    @Override
                    public void onComplete() {
                        view.disposeLoadingAnimation();
                    }
                });
    }

}
