package com.yd.huixuangu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.yd.huixuangu.R;

import java.util.List;

public class MainViewPager2Adapter extends RecyclerView.Adapter<MainViewPager2Adapter.ViewHolder> {
    private List<String> mData;
    private LayoutInflater mInflater;


    private int[] colorArray = new int[]{android.R.color.black, android.R.color.holo_blue_dark, android.R.color.holo_green_dark, android.R.color.holo_red_dark, android.R.color.holo_orange_dark};


    public MainViewPager2Adapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public MainViewPager2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_mainview_pageradapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewPager2Adapter.ViewHolder holder, int position) {
        String animal = mData.get(position);
        holder.myTextView.setText(animal);
        holder.relativeLayout.setBackgroundResource(colorArray[position]);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        RelativeLayout relativeLayout;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvTitle);
            relativeLayout = itemView.findViewById(R.id.container);

        }
    }
}
