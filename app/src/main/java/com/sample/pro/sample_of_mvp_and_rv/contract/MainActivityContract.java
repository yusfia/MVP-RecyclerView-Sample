package com.sample.pro.sample_of_mvp_and_rv.contract;

import com.sample.pro.sample_of_mvp_and_rv.model.Item;

import java.util.List;

public class MainActivityContract {
    public interface View {
        void initializeItems(List<Item> items);
        void notifyItemsUpdates();
        void showLoadingAnimation();
        void disposeLoadingAnimation();
        void showErrorMessage(Throwable t);
        void showMessage(String title, String messageContent);
    }
    public interface Presenter {
        void loadData();
    }
}
