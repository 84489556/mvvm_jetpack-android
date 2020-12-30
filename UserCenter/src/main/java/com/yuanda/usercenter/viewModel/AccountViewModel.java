package com.yuanda.usercenter.viewModel;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.yuanda.usercenter.base.BaseResp;
import com.yuanda.usercenter.bean.Account;
import com.yuanda.usercenter.repository.AccountRepository;

public class AccountViewModel extends ViewModel {
    private final String tag = AccountViewModel.class.getSimpleName();
    private MutableLiveData<String> phone = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<Integer> loginType; //1 验证码登录，2 账号密码登录
    public MutableLiveData<String> sendCodeLiveData = new MutableLiveData<>();

    public MutableLiveData<Integer> getLoginType(){
        if(loginType == null){
            loginType = new MutableLiveData<>();
            loginType.setValue(1);
        }
        return loginType;
    }
    public MutableLiveData<String> getPhone(){
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.setValue(phone);
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(MutableLiveData<String> password) {
        this.password = password;
    }

    public void changeLoginType(){
        int flag = loginType.getValue() ==1?2:1;
        loginType.setValue(flag);
    }

    /**
     * let port = 2;//1位pc,2为app
     * let param = { 'phone': mobile, 'port': port };
     * @param phone
     */
    public void getSmsCode(String phone){
        String params = "{\"phone\":\""+phone+"\",\"port\":2}";


    }

    private MutableLiveData<Account> accountLiveData = new MutableLiveData<>();
    public LiveData<BaseResp> loginWithPwdData = Transformations.switchMap(accountLiveData, new Function<Account, LiveData<BaseResp>>() {
        @Override
        public LiveData<BaseResp> apply(Account input) {
            return AccountRepository.getInstance().loginWithPassword(input.getPhone(),input.getPwd());
        }
    });
    public void loginWithPassword(String phone,String password){
        Account account = new Account();
        account.setPhone(phone);
        account.setPwd(password);
        accountLiveData.setValue(account);
    }
}
