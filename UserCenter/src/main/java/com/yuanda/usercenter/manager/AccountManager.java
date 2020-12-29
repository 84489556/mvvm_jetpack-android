package com.yuanda.usercenter.manager;

public class AccountManager {

    public AccountManager(){
        init();
    }
    private void init(){

    }
    public void loginWithCode(String phone,String verCode,ILoginCallback loginCallback){

    }
    public void loginWithPwd(String phone,String password,ILoginCallback loginCallback){

    }
    public void loginWithWithWX(ILoginCallback loginCallback){

    }

    public void logout(ILogoutCallback logoutCallback){

    }

    /**
     * 是否已登录
     * @return
     */
    public boolean isLogin(){
        return true;
    }
}
