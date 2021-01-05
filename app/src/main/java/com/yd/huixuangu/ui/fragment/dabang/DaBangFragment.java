package com.yd.huixuangu.ui.fragment.dabang;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reactlibrary.utils.FLog;
import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.adapter.dabang.DaBangFunctionAdapter;
import com.yd.huixuangu.adapter.dabang.interfaces.DaBangFunItemClick;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.databinding.FragmentDabangBinding;
import com.yd.huixuangu.viewmodel.dabang.DaBangFragmentViewModel;

/**
 * 打榜首页
 */
public class DaBangFragment extends BaseFragment {

    DaBangFragmentViewModel mViewModel;
    FragmentDabangBinding binding;
    private static DaBangFragment daBangFragment;
    public static DaBangFragment getInstance(){
        if(daBangFragment == null){
            daBangFragment = new DaBangFragment();
        }
        return daBangFragment;
    }



    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_dabang, BR.dabang, mViewModel);

    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(DaBangFragmentViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void initView(){
//        if (binding != null){
//            return;
//        }
        binding = (FragmentDabangBinding) this.mBinding;

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        binding.dabangFunctionLay.setLayoutManager(manager);
        binding.dabangFunctionLay.setAdapter(new DaBangFunctionAdapter(new DaBangFunItemClick() {
            @Override
            public void onClicked(int fragmentId) {
                if (fragmentId < 1){
                    FLog.e("DaBangFragment","onClicked fragmentId is " + fragmentId);
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("key", "DaBangFragment");
                nav().navigate(fragmentId,bundle);
            }
        }));

        binding.dabangLonghubangWv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
                refreshLineChart();
            }
        });
    }
    private void refreshLineChart(){
        Object[] x = new Object[]{
                "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
        };
        Object[] y = new Object[]{
                820, 932, 901, 934, 1290, 1330, 1320
        };
        binding.dabangLonghubangWv.refreshEchartsWithOption(EchartOptionUtil.getLineChartOptions(x, y));
    }


}
