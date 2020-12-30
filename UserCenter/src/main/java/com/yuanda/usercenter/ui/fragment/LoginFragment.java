package com.yuanda.usercenter.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.yuanda.usercenter.BR;
import com.yuanda.usercenter.R;
import androidx.fragment.app.Fragment;

import com.yuanda.usercenter.base.BaseFragment;
import com.yuanda.usercenter.base.BaseResp;
import com.yuanda.usercenter.databinding.FragmentLoginBinding;
import com.yuanda.usercenter.viewModel.AccountViewModel;

public class LoginFragment extends BaseFragment {
    private String TAG = LoginFragment.class.getSimpleName();
    AccountViewModel mViewModel;
    FragmentLoginBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel =  new ViewModelProvider(this).get(AccountViewModel.class);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container,false);
        mBinding.setLifecycleOwner(this);
        mBinding.setVariable(BR.event, new EventHandler());
        mBinding.setVariable(BR.viewModel,mViewModel);

        init();
        View view = mBinding.getRoot();
        return view;
    }

    private void init(){
        mViewModel.getLoginType().observe(getViewLifecycleOwner(),new Observer<Integer>(){
            @Override
            public void onChanged(Integer integer) {
                Log.i(TAG,"onChanged codePwdf: "+integer);
                if(integer == 1){
                    mBinding.codePwdEt.setHint(R.string.login_code_hint);
                    mBinding.eyeIv.setVisibility(View.GONE);
                    mBinding.verfyCodeBtn.setVisibility(View.VISIBLE);
                    mBinding.registFogetRl.setVisibility(View.GONE);
                    mBinding.codePasswodTv.setText(R.string.login_passwordLogin_bnt);
                } else if(integer == 2){
                    mBinding.codePwdEt.setHint(R.string.login_pwd_hint);
                    mBinding.eyeIv.setVisibility(View.VISIBLE);
                    mBinding.verfyCodeBtn.setVisibility(View.GONE);
                    mBinding.registFogetRl.setVisibility(View.VISIBLE);
                    mBinding.codePasswodTv.setText(R.string.login_veriLogin_bnt);
                }
            }
        });
        mViewModel.getPhone().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.i(TAG,"s::::" + s);
            }
        });
        mViewModel.loginWithPwdData.observe(getViewLifecycleOwner(), new Observer<BaseResp>() {
            @Override
            public void onChanged(BaseResp baseResp) {
                Log.i(TAG,"login: " + baseResp.toString());
            }
        });
    }

    public class EventHandler {

        public void changeLoginView(){
            Log.i(TAG,"changePwdCodeView");
            mViewModel.changeLoginType();
            Log.i(TAG,"getValue():"+mViewModel.getPhone().getValue());
            mViewModel.setPhone("11111111111111111");
        }
        public void getSmsCode(){
            String phone = mBinding.nameEt.getText().toString();
            mViewModel.getSmsCode(phone);
        }
        public void login(){
            mViewModel.loginWithPassword("","");
        }
    }
}
