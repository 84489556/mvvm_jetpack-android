package com.yd.huixuangu.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseActivity;
import com.yd.huixuangu.databinding.ActivityMainBinding;
import com.yd.huixuangu.viewmodel.UserViewModel;


public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    private UserViewModel mModel;
    Observer<String> observer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }



    public void init() {
        /*
          初始化binding对象 必写
         */

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        /*
         * 初始化viewModel对象 必写
         */
        mModel = new ViewModelProvider(this).get(UserViewModel.class);


        /* 初始化被观察者
         */
        observer = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.textview.setText(s);
            }
        };

        /*
         * 可不写
         */
        binding.setUser(mModel);

    }


    @Override
    protected void onResume() {
        super.onResume();

        /*
        粘性事件
         */
        mModel.getData().observe(this, observer);
    }

    public void button(View view) {
        /*
         * 发送请求
         */
        mModel.getRemoteData();
    }
}
