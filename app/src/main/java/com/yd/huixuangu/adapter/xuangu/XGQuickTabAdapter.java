package com.yd.huixuangu.adapter.xuangu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.databinding.ViewXuanguQuickTabItemBinding;
import com.yd.huixuangu.ui.fragment.xuangu.view.XGTabItem;

import java.util.ArrayList;
import java.util.List;

public class XGQuickTabAdapter extends RecyclerView.Adapter<XGQuickTabAdapter.QuickTabViewHolder>{

    private List<XGTabItem> mDatas = new ArrayList<>();
    private Context mContext;
    private int mTabIndex;

    public XGQuickTabAdapter(Context mContext, List<XGTabItem> datas, int tabIndex){
        this.mContext = mContext;
        this.mDatas = datas;
        this.mTabIndex = tabIndex;
    }

    @NonNull
    @Override
    public QuickTabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewXuanguQuickTabItemBinding binding = ViewXuanguQuickTabItemBinding.inflate(LayoutInflater.from(mContext));
        QuickTabViewHolder holder = new QuickTabViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuickTabViewHolder holder, int position) {
        holder.mBinding.setVariable(BR.tabName,mContext.getResources().getString(mDatas.get(position).text));
        holder.mBinding.tabLine.setVisibility(position == this.mTabIndex?View.VISIBLE:View.INVISIBLE);
       // holder.mBinding.executePendingBindings();//防止闪烁
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class QuickTabViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ViewXuanguQuickTabItemBinding mBinding;

        public QuickTabViewHolder(@NonNull ViewXuanguQuickTabItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
