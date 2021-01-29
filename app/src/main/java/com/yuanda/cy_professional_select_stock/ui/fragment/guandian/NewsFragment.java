package com.yuanda.cy_professional_select_stock.ui.fragment.guandian;

import android.widget.TextView;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.databinding.FragmentNewsBinding;
import com.yuanda.cy_professional_select_stock.viewmodel.MainActivityViewModel;
import com.yuanda.cy_professional_select_stock.viewmodel.guandian.NewsViewModel;

import java.util.ArrayList;

public class NewsFragment extends BaseFragment {

    NewsViewModel newsViewModel;
    MainActivityViewModel mainActivityViewModel;
    private static NewsFragment newsFragment;
    public static NewsFragment getInstance(){
        if(newsFragment == null){
            newsFragment = new NewsFragment();
        }
        return newsFragment;
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_news, BR.news, newsViewModel)
                .addBindingParam(BR.event, new EventHandler())
                .addBindingParam(BR.viewpagerhandler, new ViewPagerHandler());

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

    @Override
    protected void initViewModel() {
        newsViewModel = getFragmentScopeViewModel(NewsViewModel.class);
        mainActivityViewModel= getActivityScopeViewModel(MainActivityViewModel.class);
    }

    //这个是viewPager的监听
    public class ViewPagerHandler extends ViewPager2.OnPageChangeCallback {
        private int currentPosition = 0  ;   //当前滑动位置
        private int oldPositon = 0 ;         //上一个滑动位置
        private int currentState = 0 ;       //记录当前手指按下状态
        private ArrayList<Integer> scrolledPixeledList = new ArrayList<>(); //记录手指滑动时的像素坐标记录
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            currentPosition = position;
            if (currentState == 1) {
                //手指按下滑动坐标记录
                scrolledPixeledList.add(positionOffsetPixels);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
            //viewPager.setUserInputEnabled(viewPager.getCurrentItem()<2);
            currentState = state;
            if (state == 0) {
                if (currentPosition == oldPositon) {
                    switch (currentPosition){
                        case 0:
                            if (scrolledPixeledList.size() > 1 && scrolledPixeledList.get(scrolledPixeledList.size()-1) == 0 || scrolledPixeledList.get(scrolledPixeledList.size()-1) - scrolledPixeledList.get(0) > 0) {
                                //有可能出现滑到一半放弃的情况也是可以出现currentPosition == oldPositon=0，则先判断是否是往右滑时放弃
                                return;
                            }
                            FragmentNewsBinding Binding = (FragmentNewsBinding) mBinding;
                            if(Binding.newsViewpager.getCurrentItem() <=0){
                               // Log.e("TAG", "onPageScrollStateChanged: 继续往左滑" );
                                mainActivityViewModel.getInputEnabled().setValue(false);
                            }
                            break;
                        default:
                            break;
                    }
                }
                oldPositon = currentPosition;
                scrolledPixeledList.clear();//清空滑动记录
            }
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);

        }
    }
}
