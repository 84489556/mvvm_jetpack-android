package com.yd.huixuangu.adapter.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yd.huixuangu.R;
import com.yd.huixuangu.bean.main.ItemMainEntranceBean;

import java.util.ArrayList;
import java.util.List;

public class ZhiShuRecyclerViewAdapter extends RecyclerView.Adapter<ZhiShuRecyclerViewAdapter.ZhishuHoder> {
    private List<String> mDataList;

    public ZhiShuRecyclerViewAdapter() {
        mDataList = new ArrayList<>();
        mDataList.add("1");
        mDataList.add("2");
        mDataList.add("3");
        mDataList.add("4");
        mDataList.add("5");
        mDataList.add("6");
    }

    @NonNull
    @Override
    public ZhishuHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.item_home_recyclerview_zhishu, parent, false);
        return new ZhishuHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZhishuHoder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class ZhishuHoder extends RecyclerView.ViewHolder {

        public ZhishuHoder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
