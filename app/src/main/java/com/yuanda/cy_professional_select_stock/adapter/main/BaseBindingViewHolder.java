package com.yuanda.cy_professional_select_stock.adapter.main;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseBindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public T getBinding() {
        return binding;
    }

    private final T binding;

    public BaseBindingViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
