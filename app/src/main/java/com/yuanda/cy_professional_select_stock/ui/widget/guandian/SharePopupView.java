package com.yuanda.cy_professional_select_stock.ui.widget.guandian;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.yuanda.cy_professional_select_stock.R;


/**
 * author：created by tangqianzhu
 * mail：pillartang@sina.cn
 * date：2021/1/28 15
 * description:自定义分享弹窗
 */
public class SharePopupView extends BottomPopupView {

    public SharePopupView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.share_bottom_popup;
    }
}
