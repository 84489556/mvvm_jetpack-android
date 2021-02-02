package com.yuanda.cy_professional_select_stock.adapter.main;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuanda.cy_professional_select_stock.R;

import java.util.List;

public class RightAdapter extends BaseQuickAdapter<Stock, BaseViewHolder> {
    public RightAdapter(int id, @Nullable List<Stock> data) {
        super(id, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Stock item) {
        helper.setText(R.id.right_item_textview0, item.getTxt1())
                .setText(R.id.right_item_textview1, item.getTxt1())
        ;
    }
}
