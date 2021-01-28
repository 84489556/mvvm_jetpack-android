package com.yuanda.huixuangu.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.huixuangu.base.BaseFragment;
import com.yuanda.huixuangu.base.DataBindingConfig;
import com.yuanda.huixuangu.viewmodel.MainActivityViewModel;
import com.yuanda.huixuangu.viewmodel.SecondFragmentViewModel;

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
