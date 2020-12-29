package com.yuanda.usercenter.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountViewModel extends ViewModel {
    private final String tag = AccountViewModel.class.getSimpleName();
    public MutableLiveData<Integer> codePwdView = new MutableLiveData<>(); //1 验证码登录，2 账号密码登录
    public MutableLiveData<Integer> sendSmsCode = new MutableLiveData<>();

    public void changeCodePwdView(){
        int flag = (codePwdView.getValue() == null||codePwdView.getValue() ==1)?2:1;
        codePwdView.setValue(flag);
    }
    public int getCodePwdFlag(){
        return codePwdView.getValue();
    }

    /**
     * let port = 2;//1位pc,2为app
     * let param = { 'phone': mobile, 'port': port };
     * @param phone
     */
    public void sendSmsCode(String phone){

    }
}
