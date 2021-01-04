package com.yd.huixuangu.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.MainActivityViewModel;
import com.yd.huixuangu.viewmodel.SecondFragmentViewModel;

public class SecondFragment extends BaseFragment {
    private SecondFragmentViewModel mState;

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_second, BR.secondViewModel, mState)
                .addBindingParam(BR.callback, new SecondFragmentCallBack());
    }

    @Override
    protected void initViewModel() {
        mState = getFragmentScopeViewModel(SecondFragmentViewModel.class);
        MainActivityViewModel activityScopeViewModel = getActivityScopeViewModel(MainActivityViewModel.class);
        activityScopeViewModel.getData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("wgl", activityScopeViewModel + "");//这里可以拿到此fragment的所属activity的viewmodel。通过打印地址可以知道是同一个实例。不会重新建一个，在activity里setValue里会收到回调

            }
        });
    }

    public class SecondFragmentCallBack {
        public void back() {
            nav().navigateUp();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStatusColor(R.color.color_btn_normal);
        if (getArguments() == null) return;
        String keyStr = getArguments().getString("key");
        Log.d("wgl keyStr", keyStr);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getContext(), "第二个fragment销毁了", Toast.LENGTH_LONG).show();
    }
}
