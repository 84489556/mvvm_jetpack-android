package com.yuanda.cy_professional_select_stock.ui.fragment.guandian;

import android.widget.Toast;

import com.lxj.xpopup.XPopup;
import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.ui.widget.guandian.SharePopupView;
import com.yuanda.cy_professional_select_stock.viewmodel.guandian.ExpertAnalysisViewModel;


public class ExpertAnalysisnFragment extends BaseFragment {

    ExpertAnalysisViewModel expertAnalysisViewModel;

    private static ExpertAnalysisnFragment expertAnalysisnFragment;
    public static ExpertAnalysisnFragment getInstance(){
        if(expertAnalysisnFragment == null){
            expertAnalysisnFragment = new ExpertAnalysisnFragment();
        }
        return expertAnalysisnFragment;
    }


    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_expertanalysis, BR.expertanalysis, expertAnalysisViewModel)
                .addBindingParam(BR.click,new ClickProxy());

    }

    @Override
    protected void initViewModel() {
        expertAnalysisViewModel = getFragmentScopeViewModel(ExpertAnalysisViewModel.class);
    }

    public class ClickProxy {
        //分享测试点击事件
        public void shareClick() {
           // Toast.makeText(getContext(),"分享",Toast.LENGTH_SHORT).show();
            SharePopupView sharePopupView = new SharePopupView(getActivity());
            sharePopupView.setOnPopClick(new SharePopupView.SetPopClick() {
                @Override
                public void wxFriendClick() {
                    Toast.makeText(getActivity(),"微信好友",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void wxCircleClick() {
                    Toast.makeText(getActivity(),"微信朋友圈",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void qqFriendClick() {
                    Toast.makeText(getActivity(),"QQ好友",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void qqZoomClick() {
                    Toast.makeText(getActivity(),"QQ空间",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void sinaClick() {
                    Toast.makeText(getActivity(),"新浪微博",Toast.LENGTH_SHORT).show();
                }
            });
            //使用弹窗
            new XPopup.Builder(getContext()).asCustom(sharePopupView).show();
        }



    }
}
