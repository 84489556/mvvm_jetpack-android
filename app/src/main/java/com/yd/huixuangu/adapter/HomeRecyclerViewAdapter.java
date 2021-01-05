package com.yd.huixuangu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yd.huixuangu.R;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<
        HomeRecyclerViewAdapter.HomeRVVH> {

    public static final int TYPE_ITEM_ENTRANCE = 0;
    public static final int TYPE_ITEM_MARKET = 1;
    public static final int TYPE_ITEM_UPSDOWNS = 2;
    public static final int TYPE_ITEM_FOCUSHOT = 3;
    public static final int TYPE_ITEM_VIEWPOINT = 4;

    private List<String> mDataList ;
    private LayoutInflater mInflater;

    public HomeRecyclerViewAdapter(Context context) {
        mDataList = new ArrayList<>();
        mDataList.add("1111");
        mDataList.add("22222");
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HomeRVVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case TYPE_ITEM_ENTRANCE:
                view = mInflater.inflate(R.layout.item_home_recyclerview_entrance, parent, false);
                break;
            case TYPE_ITEM_MARKET:
                view = mInflater.inflate(R.layout.item_home_recyclerview_market, parent, false);
                break;
            case TYPE_ITEM_UPSDOWNS:
                view = mInflater.inflate(R.layout.item_home_recyclerview_upsdowns, parent, false);
                break;
            case TYPE_ITEM_FOCUSHOT:
                view = mInflater.inflate(R.layout.item_home_recyclerview_focushot, parent, false);
                break;
            case TYPE_ITEM_VIEWPOINT:
                view = mInflater.inflate(R.layout.item_home_recyclerview_viewpoint, parent, false);
                break;
            default:
                return null;
        }
        return new HomeRVVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRVVH holder, int position) {

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_ITEM_ENTRANCE:
                        case TYPE_ITEM_MARKET:
                        case TYPE_ITEM_UPSDOWNS:
                        case TYPE_ITEM_FOCUSHOT:
                        case TYPE_ITEM_VIEWPOINT:
                    }
                    return 1;
                }
            });
        }


    }


    @Override
    public int getItemViewType(int position) {

        return 1;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class HomeRVVH extends RecyclerView.ViewHolder {

        public HomeRVVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
