package com.yuanda.cy_professional_select_stock.ui.fragment.guandian;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.google.android.material.tabs.TabLayout;
import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.databinding.FragmentGuandianBinding;
import com.yuanda.cy_professional_select_stock.viewmodel.MainActivityViewModel;
import com.yuanda.cy_professional_select_stock.viewmodel.guandian.GuanDianFragmentViewModel;


public class GanDianFragment extends BaseFragment {

    GuanDianFragmentViewModel mViewModel;
    MainActivityViewModel mainActivityViewModel;
    private static GanDianFragment ganDianFragment;
    public static GanDianFragment getInstance(){
        if(ganDianFragment == null){
            ganDianFragment = new GanDianFragment();
        }
        return ganDianFragment;
    }


    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_guandian, BR.guandian, mViewModel)
                .addBindingParam(BR.event, new EventHandler())
                .addBindingParam(BR.click,new ClickProxy());//添加点击事件的回调;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(GuanDianFragmentViewModel.class);
        mainActivityViewModel = getActivityScopeViewModel(MainActivityViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivityViewModel.getInputEnabled().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                //只要收到了消息，就切换到第二个热点聚焦tab,这里目前只会是子类第一个tab往左滑时，会有通知
                FragmentGuandianBinding mmBinding = (FragmentGuandianBinding) mBinding;
                mmBinding.guandianViewpager2.setCurrentItem(1);

            }
        });


    }

    public class ClickProxy {

        public void testClick() {
            Toast.makeText(getContext(),"头像点击事件",Toast.LENGTH_SHORT).show();
//            mViewModel.tabNames.clear();
//            ArrayList<String> news = new ArrayList<>();
//            news.add("专家不分析分析");
//            news.add("热点不聚焦");
//            news.add("资讯222");
//            mViewModel.tabNames.addAll(news);
        }

    }

    //这个是drawer的监听
    public class EventHandler implements TabLayout.OnTabSelectedListener {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {

            updateTabView(tab, true);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            updateTabView(tab, false);
        }
        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
    /**
     *  用来改变tabLayout选中后的字体大小及颜色
     * @param tab
     * @param isSelect
     */
    private void updateTabView(TabLayout.Tab tab, boolean isSelect) {
        TextView tv_tab = tab.getCustomView().findViewById(R.id.guandiantab__txt);
        if(isSelect) {
            tv_tab.setSelected(true);
            //选中后字体变大
           //tv_tab.setTextSize(TypedValue.COMPLEX_UNIT_PX,getContext().getResource().getDimensionPixelSize(R.dimen.sp_28));
        }else{
            tv_tab.setSelected(false);
        }
    }
}
