package com.yd.huixuangu.base;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yd.huixuangu.net.socket.SocketModule;
import com.yd.huixuangu.net.socket.WebSocketListener;
import com.yd.huixuangu.net.socket.WebSocketReceive;
import com.yd.huixuangu.utils.BarUtils;

public abstract class BaseActivity extends AppCompatActivity implements WebSocketListener {


    private ViewModelProvider mActivityProvider;
    private ViewModelProvider mApplicationProvider;
    protected ViewDataBinding mBinding;

    private WebSocketListener wsCallBack;

    /**
     * initViewModel();
     * initBinding();
     * <p>
     * 顺序不能乱
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);//透明状态栏
        super.onCreate(savedInstanceState);
        WebSocketReceive.setListener(this);
        initViewModel();
        initBinding();

    }

    protected void initBinding() {
        /**
         * 创建一个config对象，每一个activity都会有一个配置文件
         */
        DataBindingConfig dataBindingConfig = getDataBindingConfig();
        /**
         * 根据config文件生成binding对象
         */
        ViewDataBinding binding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
        /**
         * 指定binding对象为当前activity所有
         */
        binding.setLifecycleOwner(this);
        /**
         * 给binding对象设置一个ViewModel对象
         */
        binding.setVariable(dataBindingConfig.getVmVariableId(), dataBindingConfig.getStateViewModel());
        /**
         * 给binding对象设置属性
         */
        SparseArray bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        mBinding = binding;
    }

    /**
     * 初始化viewModel对象
     */
    protected abstract void initViewModel();

    /**
     * 需要new一个 DataBindingConfig对象 填入相关参数
     *
     * @return DataBindingConfig
     */
    protected abstract DataBindingConfig getDataBindingConfig();

    /**
     * 获取到一个ViewModel
     * get（）源码：如果有就直接返回 ，没有就创建
     * 把这个viewModel 保存在当前的activity中
     */
    protected <T extends ViewModel> T getActivityScopeViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(this);
        }
        return mActivityProvider.get(modelClass);
    }

    /**
     * 如上
     * 把这个viewModel 保存在当前的Application中
     */
    protected <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass) {
        if (mApplicationProvider == null) {
            mApplicationProvider = new ViewModelProvider((HuiXuanGuApplication) this.getApplicationContext(),
                    getAppFactory(this));
        }
        return mApplicationProvider.get(modelClass);
    }


    protected void setSubscribe(WebSocketListener wsCallBack) {
        if (wsCallBack != null) {
            this.wsCallBack = wsCallBack;
        }
    }

    /**
     * socket返回数据时会掉
     */
    public void onReceiveSocket(SocketModule data) {
        if (this.wsCallBack != null) {
            this.wsCallBack.onReceiveSocket(data);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinding != null) mBinding.unbind();
        if (mBinding != null) mBinding = null;
    }


    /**
     * 工具
     */
    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    /**
     * 工具
     */
    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        Application application = checkApplication(activity);
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application);
    }


}
