package com.yuanda.cy_professional_select_stock.adapter.main;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuanda.cy_professional_select_stock.R;

import java.util.List;

public class LeftAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public LeftAdapter(int id, @Nullable List<String> data) {
        super(id, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.leftTitle,item);
    }

}
