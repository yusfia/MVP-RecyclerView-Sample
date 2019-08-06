package com.sample.pro.sample_of_mvp_and_rv.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sample.pro.sample_of_mvp_and_rv.R;
import com.sample.pro.sample_of_mvp_and_rv.model.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RVItemAdapter extends RecyclerView.Adapter<RVItemAdapter.MyViewHolder> {
    private List<Item> items;
    private Context context;

    public RVItemAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_layout, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item passItem = items.get(position);
        holder.itemName.setText(passItem.getTitle());
        if (!passItem.getFilesource().isEmpty()) {
            Glide.with(context).load(passItem.getFilesource()).into(holder.itemImage);
        }
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to the other activity using Intent or any action
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemName)
        public TextView itemName;
        @BindView(R.id.itemPicture)
        public ImageView itemImage;

        public MyViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
