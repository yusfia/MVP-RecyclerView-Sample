package com.sample.pro.sample_of_mvp_and_rv.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.sample.pro.sample_of_mvp_and_rv.R;
import com.sample.pro.sample_of_mvp_and_rv.adapter.RVItemAdapter;
import com.sample.pro.sample_of_mvp_and_rv.config.RetrofitConfig;
import com.sample.pro.sample_of_mvp_and_rv.contract.MainActivityContract;
import com.sample.pro.sample_of_mvp_and_rv.model.Item;
import com.sample.pro.sample_of_mvp_and_rv.network.ItemService;
import com.sample.pro.sample_of_mvp_and_rv.presenter.MainActivityPresenter;
import com.sample.pro.sample_of_mvp_and_rv.repository.ItemRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private MainActivityContract.Presenter presenter;
    private RVItemAdapter rvItemAdapter;
    private ItemRepository itemRepository;

    @BindView(R.id.itemList)
    RecyclerView rvItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        itemRepository = new ItemRepository(RetrofitConfig.getDefaultUrlInstance().create(ItemService.class));
        presenter = new MainActivityPresenter(this, itemRepository);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadData();
    }

    @Override
    public void initializeItems(List<Item> items) {
        rvItemAdapter = new RVItemAdapter(items, this);
        //Create Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        //Set Adapter and Layout Manager to Recycler View
        rvItemList.setLayoutManager(layoutManager);
        rvItemList.setItemAnimator(new DefaultItemAnimator());
        rvItemList.setAdapter(rvItemAdapter);
    }

    @Override
    public void notifyItemsUpdates() {
        if (rvItemAdapter != null){
            rvItemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoadingAnimation() {

    }

    @Override
    public void disposeLoadingAnimation() {

    }

    @Override
    public void showErrorMessage(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
        t.printStackTrace();
    }

    @Override
    public void showMessage(String title, String messageContent) {
        Toast.makeText(this, messageContent, Toast.LENGTH_LONG).show();
    }
}
