package com.yd.huixuangu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.adapter.MainFragmentStateAdapter;
import com.yd.huixuangu.base.BaseActivity;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.databinding.ActivityMainBinding;
import com.yd.huixuangu.ui.fragment.dabang.DaBangFragment;
import com.yd.huixuangu.ui.fragment.guandian.GanDianFragment;
import com.yd.huixuangu.ui.fragment.kanshi.KanShiFragment;
import com.yd.huixuangu.ui.fragment.main.MainFragment;
import com.yd.huixuangu.ui.fragment.xuangu.XuanGuFragment;
import com.yd.huixuangu.viewmodel.MainActivityViewModel;
import com.yuanda.usercenter.ui.activity.LoginActivity;

import java.util.ArrayList;


public class MainActivity extends BaseActivity implements TabLayoutMediator.TabConfigurationStrategy {
    private MainActivityViewModel mModel;
    private ArrayList<String> tabStrList;

    private TabLayoutMediator tabLayoutMediator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void initViewModel() {
        mModel = getActivityScopeViewModel(MainActivityViewModel.class);
        Log.d("wgl activity ", mModel + "");


    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main, BR.mainModel, mModel)
                .addBindingParam(BR.event, new EventHandler());
    }


    public class EventHandler {
        public void button() {
            mModel.getRemoteData();
        }

        public void gotopage() {


        }
        public void gotoLoginpage(){
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }
    }

    public void init() {

        initView();
    }

    private void initView() {
        tabStrList = new ArrayList<>();
        ActivityMainBinding mBinding = (ActivityMainBinding) this.mBinding;
        ArrayList<Fragment> fragments = new ArrayList<>();

        tabStrList.add("看势");
        tabStrList.add("观点");
        tabStrList.add("首页");
        tabStrList.add("打榜");
        tabStrList.add("选股");

        fragments.add(KanShiFragment.getInstance());
        fragments.add(GanDianFragment.getInstance());
        fragments.add(new MainFragment());
        fragments.add(DaBangFragment.getInstance());
        fragments.add(XuanGuFragment.getInstance());

        mBinding.mainViewPager2.setAdapter(new MainFragmentStateAdapter(this, fragments));
        tabLayoutMediator = new TabLayoutMediator(mBinding.mainTabLayout, mBinding.mainViewPager2, this);
        tabLayoutMediator.attach();

    }

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        tab.setText(tabStrList.get(position));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
