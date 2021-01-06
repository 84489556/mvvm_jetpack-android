package com.yd.huixuangu.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yd.huixuangu.R;
import com.yd.huixuangu.bean.main.ItemMainEntranceBean;

import java.util.ArrayList;
import java.util.List;

public class EntranceRecyclerViewAdapter extends RecyclerView.Adapter<
        EntranceRecyclerViewAdapter.HomeRVVH> {


    private List<ItemMainEntranceBean> mDataList;
    private LayoutInflater mInflater;

    public EntranceRecyclerViewAdapter(Context context) {
        mDataList = new ArrayList<>();
        ItemMainEntranceBean bean = new ItemMainEntranceBean();
        mDataList.add(bean);
        mDataList.add(bean);
        mDataList.add(bean);
        mDataList.add(bean);
        mDataList.add(bean);
        mDataList.add(bean);
        mDataList.add(bean);
        mDataList.add(bean);
        mDataList.add(bean);
        mDataList.add(bean);
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HomeRVVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_home_recyclerview_entrance, parent, false);
        return new HomeRVVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRVVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class HomeRVVH extends RecyclerView.ViewHolder {

        public HomeRVVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
