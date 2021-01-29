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
    public static XGTabItem create(int text){
        return new XGTabItem(text,0);
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
                tabs.add(XGTabItem.create(R.string.xuangu_tab_yanbaocelue));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_tesezhibiaoxuangu));
                break;
            case 4:
                tabs.add(XGTabItem.create(R.string.xuangu_tab_yanbaocelue));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_tesezhibiaoxuangu));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_zijinjiemi));
                break;
            case 5:
                tabs.add(XGTabItem.create(R.string.xuangu_tab_yanbaocelue));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_tesezhibiaoxuangu));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_zijinjiemi));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_rediancelue));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_zhuticelue));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_jiazhicelue));
                break;
            case 6:
                tabs.add(XGTabItem.create(R.string.xuangu_tab_yanbaocelue));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_tesezhibiaoxuangu));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_zijinjiemi));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_rediancelue));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_zhuticelue));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_jiazhicelue));
                tabs.add(XGTabItem.create(R.string.xuangu_tab_mingshijingxuan));
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
        tabs.add(XGTabItem.create(R.string.xuangu_zhangfukongjianda));
        tabs.add(XGTabItem.create(R.string.xuangu_mingxingjigoutuijian));
        tabs.add(XGTabItem.create(R.string.xuangu_mingxingfenxishituijian));
        return tabs;
    }
}
