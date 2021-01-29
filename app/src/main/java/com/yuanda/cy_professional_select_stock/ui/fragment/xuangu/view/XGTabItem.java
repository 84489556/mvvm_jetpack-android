package com.yuanda.cy_professional_select_stock.ui.fragment.xuangu.view;


import com.yuanda.cy_professional_select_stock.R;

import java.util.ArrayList;
import java.util.List;

public class XGTabItem {

    public int text;
    public int drawableId;

    private XGTabItem(int text,int drawableId){
        this.text = text;
        this.drawableId = drawableId;
    }

    public static XGTabItem create(int text, int drawableId){
        return new XGTabItem(text,drawableId);
    }

    /**
     * 顶部tab
     * @param level
     * @return
     */
    public static List<XGTabItem> getHeaderTabs(int level){
        ArrayList<XGTabItem> tabs = new ArrayList<>();
        switch (level){
            case 0:
            case 1:
            case 2:
            case 3:
                tabs.add(XGTabItem.create(R.string.xuangu_tab_yanbaocelue,0));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_tesezhibiaoxuangu, R.mipmap.xuangu_three_vip));
                break;
            case 4:
                tabs.add(XGTabItem.create(R.string.xuangu_tab_yanbaocelue,0));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_tesezhibiaoxuangu, R.mipmap.xuangu_three_vip));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_zijinjiemi, R.mipmap.xuangu_four_vip));
                break;
            case 5:
                tabs.add(XGTabItem.create(R.string.xuangu_tab_yanbaocelue,0));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_tesezhibiaoxuangu, R.mipmap.xuangu_three_vip));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_zijinjiemi, R.mipmap.xuangu_four_vip));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_rediancelue, R.mipmap.xuanu_five_vip));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_zhuticelue, R.mipmap.xuanu_five_vip));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_jiazhicelue, R.mipmap.xuanu_five_vip));
                break;
            case 6:
                tabs.add(XGTabItem.create(R.string.xuangu_tab_yanbaocelue,0));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_tesezhibiaoxuangu, R.mipmap.xuangu_three_vip));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_zijinjiemi, R.mipmap.xuangu_four_vip));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_rediancelue, R.mipmap.xuanu_five_vip));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_zhuticelue, R.mipmap.xuanu_five_vip));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_jiazhicelue, R.mipmap.xuanu_five_vip));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_mingshijingxuan, R.mipmap.xuangu_six_vip));
                break;
            default:
        }
        return tabs;
    }

    /**
     * 研报策略tab
     * @return
     */
    public static List<XGTabItem> getCeLueTabs(){
        ArrayList<XGTabItem> tabs = new ArrayList<>();
        tabs.add(XGTabItem.create(R.string.xuangu_zhangfukongjianda,0));
        tabs.add(XGTabItem.create(R.string.xuangu_mingxingjigoutuijian,0));
        tabs.add(XGTabItem.create(R.string.xuangu_mingxingfenxishituijian,0));
        return tabs;
    }
}
