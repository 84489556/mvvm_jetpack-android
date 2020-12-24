package com.reactlibrary.utils;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by yorkeehuang on 2017/4/19.
 */

public class ImageLoaders {

    public static void displayImage(String url, ImageView imageView, int defaultId) {
        DisplayImageOptions defaultOptions = buildDisplayImageOptions(defaultId);
        getImageLoader().displayImage(url, imageView, defaultOptions);
    }
    public static DisplayImageOptions buildDisplayImageOptions(final int drawableId) {
        return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(drawableId)
                .showImageOnFail(drawableId)
                .showImageOnLoading(drawableId).build();
    }

    public static ImageLoader getImageLoader() {
        return ImageLoader.getInstance();
    }
}