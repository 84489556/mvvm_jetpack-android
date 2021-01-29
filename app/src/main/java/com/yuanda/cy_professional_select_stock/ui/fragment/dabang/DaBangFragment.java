package com.yuanda.cy_professional_select_stock.ui.fragment.dabang;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reactlibrary.utils.FLog;
import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.adapter.dabang.DaBangFunctionAdapter;
import com.yuanda.cy_professional_select_stock.adapter.dabang.interfaces.DaBangFunItemClick;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.databinding.FragmentDabangBinding;
import com.yuanda.cy_professional_select_stock.view.echarts.EChartsWebViewClient;
import com.yuanda.cy_professional_select_stock.view.echarts.EchartOptionUtil;
import com.yuanda.cy_professional_select_stock.viewmodel.dabang.DaBangFragmentViewModel;

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


        binding.dabangLonghubangWv.setWebViewClient(new EChartsWebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
                refreshLineChart();
            }
        });
        binding.dabangUpdownWv.setWebViewClient(new EChartsWebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
                refreshLineChart();
            }
        });
        binding.dabangUpWv.setWebViewClient(new EChartsWebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
                refreshLineChart();
            }
        });
    }
    private void refreshLineChart(){
        binding.dabangUpdownWv.refreshLineChart(EchartOptionUtil.getLineChartOptions().toString());
        binding.dabangUpWv.refreshLineChart(EchartOptionUtil.getGauGeChartOptions().toString());
//            wv_analysis.refreshLineChart(EchartOptionUtil.getBar().toString());
//        binding.dabangLonghubangWv.refreshEchartsWithOption(EchartOptionUtil.getLineChartOptions(x, y));
    }


}
