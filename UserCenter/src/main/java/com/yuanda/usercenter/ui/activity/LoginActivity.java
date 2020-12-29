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

    final String tag = LoginActivity.class.getSimpleName();
    ActivityLogin2Binding mLayoutBinding;
    AccountViewModel mAccountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutBinding = ActivityLogin2Binding.inflate(LayoutInflater.from(this));
        setContentView(mLayoutBinding.getRoot());

        mAccountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        //binding = DataBindingUtil.setContentView(this,R.layout.activity_login2);

        init();
    }
    private void init(){
        mAccountViewModel.codePwdView.observe(this,new Observer<Integer>(){
            @Override
            public void onChanged(Integer integer) {
                Log.i(tag,"onChanged codePwdf: "+integer);
                if(integer == 1){
                    mLayoutBinding.codePwdEt.setHint(R.string.login_code_hint);
                    mLayoutBinding.eyeIv.setVisibility(View.GONE);
                    mLayoutBinding.verfyCodeBtn.setVisibility(View.VISIBLE);
                    mLayoutBinding.registFogetRl.setVisibility(View.GONE);
                    mLayoutBinding.codePasswodTv.setText(R.string.login_passwordLogin_bnt);
                } else if(integer == 2){
                    mLayoutBinding.codePwdEt.setHint(R.string.login_pwd_hint);
                    mLayoutBinding.eyeIv.setVisibility(View.VISIBLE);
                    mLayoutBinding.verfyCodeBtn.setVisibility(View.GONE);
                    mLayoutBinding.registFogetRl.setVisibility(View.VISIBLE);
                    mLayoutBinding.codePasswodTv.setText(R.string.login_veriLogin_bnt);
                }
            }
        });
    }
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.codePasswodBtn){
            mAccountViewModel.changeCodePwdView();
        } else if(id == R.id.verfyCodeBtn){

        }
    }
}
