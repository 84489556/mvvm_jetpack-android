package com.yuanda.usercenter.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.yuanda.usercenter.R;
import com.yuanda.usercenter.base.BaseActivity;
import com.yuanda.usercenter.databinding.ActivityLogin2Binding;
import com.yuanda.usercenter.viewModel.AccountViewModel;

public class LoginActivity extends BaseActivity {

    final String TAG = LoginActivity.class.getSimpleName();
    ActivityLogin2Binding mLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutBinding = ActivityLogin2Binding.inflate(LayoutInflater.from(this));
        setContentView(mLayoutBinding.getRoot());
    }
}
