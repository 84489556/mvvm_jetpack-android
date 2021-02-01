package com.yuanda.cy_professional_select_stock.ui.widget.guandian;

import android.content.Context;
import android.view.View;

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
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.share_canale).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗
            }
        });
        findViewById(R.id.wx_friend).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 微信分享
                if(setPopClick!=null){
                    setPopClick.wxFriendClick();
                }
            }
        });
        findViewById(R.id.wx_circle).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 微信朋友圈
                if(setPopClick!=null){
                    setPopClick.wxCircleClick();
                }
            }
        });
        findViewById(R.id.qq_friend).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // QQ好友
                if(setPopClick!=null){
                    setPopClick.qqFriendClick();
                }
            }
        });
        findViewById(R.id.qq_zoom).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // 关闭弹窗
                if(setPopClick!=null){
                    setPopClick.qqZoomClick();
                }
            }
        });

        findViewById(R.id.sina_weibo).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              // 关闭弹窗
                if(setPopClick!=null){
                    setPopClick.sinaClick();
                }
            }
        });


    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.share_bottom_popup;
    }

    public SetPopClick setPopClick;

    // 提供注册事件监听的方法
    public void setOnPopClick(SetPopClick setPopClick) {
        this.setPopClick = setPopClick;
    }

    // 状态变化监听
    public interface SetPopClick {
        // 微信好友
        void wxFriendClick();
        // 微信朋友圈
        void wxCircleClick();
        // qqfriend
        void qqFriendClick();
        // qqzoom
        void qqZoomClick();
        // 新浪微博
        void sinaClick();
    }

}
