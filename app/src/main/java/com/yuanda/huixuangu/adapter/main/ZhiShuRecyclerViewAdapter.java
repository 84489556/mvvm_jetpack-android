package com.yuanda.huixuangu.adapter.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.huixuangu.ui.fragment.main.HomeFragment;
import com.yuanda.huixuangu.viewmodel.main.ItemMainZhuShuVM;

import java.util.ArrayList;
import java.util.List;

public class ZhiShuRecyclerViewAdapter extends RecyclerView.Adapter<ZhiShuRecyclerViewAdapter.ZhishuHoder> {
    private List<ItemMainZhuShuVM> mDataList;
    HomeFragment.HomeFragmentCallBack homeFragmentCallBack;

    public ZhiShuRecyclerViewAdapter(HomeFragment.HomeFragmentCallBack callBack) {
        this.homeFragmentCallBack = callBack;
    }


    public void setzhishuDataList(List<ItemMainZhuShuVM> mDataList) {
        if (this.mDataList == null) {
            this.mDataList = new ArrayList<>();
        }
        this.mDataList.clear();
        this.mDataList.addAll(mDataList);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ZhishuHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_home_recyclerview_zhishu, parent, false);
        return new ZhishuHoder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ZhishuHoder holder, int position) {
        holder.binding.setVariable(BR.mainZhushu, mDataList.get(position));
        holder.binding.setVariable(BR.mainfragmentcallback, homeFragmentCallBack);
        holder.binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    static class ZhishuHoder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        public ZhishuHoder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
