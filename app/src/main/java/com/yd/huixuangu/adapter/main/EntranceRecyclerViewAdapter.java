package com.yd.huixuangu.adapter.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.yd.huixuangu.R;
import com.yd.huixuangu.viewmodel.main.ItemHomeEntranceViewModel;

import java.util.ArrayList;
import java.util.List;

public class EntranceRecyclerViewAdapter extends RecyclerView.Adapter<
        EntranceRecyclerViewAdapter.HomeRVVH> {
    private List<ItemHomeEntranceViewModel> mDataList;

    public void setDataList(List<ItemHomeEntranceViewModel> mDataList) {
        this.mDataList.clear();
        this.mDataList.addAll(mDataList);
        notifyDataSetChanged();
    }

    public EntranceRecyclerViewAdapter() {
        mDataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public HomeRVVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_home_recyclerview_entrance, parent, false);
        return new HomeRVVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRVVH holder, int position) {
        holder.binding.setVariable(BR.entrance, mDataList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class HomeRVVH extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        public HomeRVVH(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
