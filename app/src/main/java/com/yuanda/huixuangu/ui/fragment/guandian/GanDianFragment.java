package com.yuanda.huixuangu.ui.fragment.guandian;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.huixuangu.base.BaseFragment;
import com.yuanda.huixuangu.base.DataBindingConfig;
import com.yuanda.huixuangu.viewmodel.guandian.GuanDianFragmentViewModel;

public class GanDianFragment extends BaseFragment {

    GuanDianFragmentViewModel mViewModel;

    private static GanDianFragment daBangFragment;
    public static GanDianFragment getInstance(){
        if(daBangFragment == null){
            daBangFragment = new GanDianFragment();
        }
        return daBangFragment;
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
        TextView tv_tab = (TextView) tab.getCustomView().findViewById(R.id.guandiantab__txt);
        if(isSelect) {
            tv_tab.setSelected(true);
            //选中后字体变大
           //tv_tab.setTextSize(TypedValue.COMPLEX_UNIT_PX,getContext().getResource().getDimensionPixelSize(R.dimen.sp_28));
        }else{
            tv_tab.setSelected(false);
        }
    }
}
