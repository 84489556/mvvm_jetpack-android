package com.yd.huixuangu.ui.fragment;

import android.widget.Toast;

import androidx.navigation.Navigation;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.MainViewModel;
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
    }

    public class SecondFragmentCallBack {
        public void back() {
            nav().navigateUp();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getContext(),"第二个fragment销毁了",Toast.LENGTH_LONG).show();
    }
}
