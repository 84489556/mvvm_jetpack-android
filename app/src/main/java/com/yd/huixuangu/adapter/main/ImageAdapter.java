package com.yd.huixuangu.adapter.main;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yd.huixuangu.R;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class ImageAdapter extends BannerAdapter<Integer, ImageAdapter.BannerViewHolder> {


    public ImageAdapter(List<Integer> mDatas) {
        super(mDatas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, Integer data, int position, int size) {
        holder.imageView.setImageResource(R.mipmap.dabang_select);
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
