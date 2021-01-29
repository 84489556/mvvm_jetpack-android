package com.yuanda.cy_professional_select_stock.adapter.dabang;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.reactlibrary.utils.FLog;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.adapter.dabang.interfaces.DaBangFunItemClick;
import com.yuanda.cy_professional_select_stock.adapter.dabang.interfaces.DaBangFunViewClick;
import com.yuanda.cy_professional_select_stock.databinding.AdapterDabangFunctionBinding;

import java.util.ArrayList;
import java.util.List;

public class DaBangFunctionAdapter extends RecyclerView.Adapter<DaBangFunctionAdapter.ViewHolder> implements DaBangFunViewClick {
    String TAG = DaBangFunctionAdapter.class.getSimpleName();
    List<Item> data = new ArrayList<>();
    DaBangFunViewClick daBangFunViewClick;
    DaBangFunItemClick daBangFunItemClick;
    public DaBangFunctionAdapter(DaBangFunItemClick click){
        initData();
        daBangFunViewClick = this;
        daBangFunItemClick = click;
    }

    private void initData() {
        data.add(new Item(R.mipmap.dabang_lhb, "龙虎榜", true));
        data.add(new Item(R.mipmap.dabang_ggjjb, "高管交易榜", true));
        data.add(new Item(R.mipmap.dabang_zjlxtj, "资金流向统计", true));
        data.add(new Item(R.mipmap.dabang_ztzb, "涨停炸板", false));
        data.add(new Item(R.mipmap.dabang_jgdy, "机构调研", false));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View binding = inflater.inflate( R.layout.adapter_dabang_function, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = data.get( position );
        holder.bind( item );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @BindingAdapter({"android:src"})
    public static void setImageResource( ImageView imageView, int resource ) {
        imageView.setImageResource( resource );
    }

    @Override
    public void onClicked(View view, Item item) {
        if (item != null){
            int fragmentId = 0;
            if (TextUtils.equals("龙虎榜", item.title)){
                fragmentId = R.id.action_homeFragment_to_longHuBangFragment;
            }else if (TextUtils.equals("高管交易榜", item.title)){

            }else if (TextUtils.equals("资金流向统计", item.title)){

            }else if (TextUtils.equals("涨停炸板", item.title)){

            }else if (TextUtils.equals("机构调研", item.title)){

            }
            if (daBangFunItemClick != null && fragmentId > 0){
                daBangFunItemClick.onClicked(fragmentId);
            }
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        AdapterDabangFunctionBinding mBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind( itemView);
        }
        public void bind(@NonNull Item item ) {
            mBinding.setItem( item );
            mBinding.setViewClick(daBangFunViewClick);
            if (item.isVip) {
                FLog.e(TAG,"item.isVip true");
                mBinding.funTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, mBinding.funTitle.getContext().getResources().getDrawable(R.mipmap.dabang_vip), null);
            }else {
                FLog.e(TAG,"item.isVip false");
                mBinding.funTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
        }
    }
    public class Item{
        public Item(int resId,String title,boolean isVip){
            this.resId = resId;
            this.title = title;
            this.isVip = isVip;
        }
        private int resId;
        private String title;
        private boolean isVip;

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isVip() {
            return isVip;
        }

        public void setVip(boolean vip) {
            isVip = vip;
        }
    }

}
