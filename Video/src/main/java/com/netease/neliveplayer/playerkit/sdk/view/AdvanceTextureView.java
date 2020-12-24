package com.netease.neliveplayer.playerkit.sdk.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.netease.neliveplayer.playerkit.core.view.BaseTextureView;


/**
 * TextureView控件
 * 对TextureView做了封装，可直接用于播放器播放，支持后台播放
 * @author netease
 */

public class AdvanceTextureView extends BaseTextureView {
    public AdvanceTextureView(Context context) {
        super(context);
    }

    public AdvanceTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdvanceTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /**
     * 暂停时初始化位图
     */
    public Bitmap initCover() {
            try {
                Bitmap bitmap = Bitmap.createBitmap(
                        getWidth(), getWidth(), Bitmap.Config.RGB_565);
                return getBitmap(bitmap);
            }catch (Exception ignored){}
            return null;
        }

}
