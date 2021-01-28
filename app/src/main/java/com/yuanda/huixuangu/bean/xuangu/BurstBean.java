package com.yuanda.huixuangu.bean.xuangu;

import java.util.ArrayList;
import java.util.List;

public class BurstBean extends XGBaseResp {

    public Data data = new Data();

    public class Data {
        public int count;
        public List<Item> list = new ArrayList<>();
    }
    public class Item {
        public String currentDates;//: "2021-01-08 00:00:00"
        public String dates;//: "2021-01-07 00:00:00.0"
        public String grade;//: "增持"
        public int id;//: 7
        public String marketCode;//: "SZ002416"
        public int pageNumber;//: 6
        public String publishingAgency;//: "国泰君安"
        public String releaseDateClosePrice;//: 8.48
        public String researcher;//: "王彦龙"
        public String secCode;//: "002416"
        public String secName;//: "爱施德"
        public String targeIncrease;//: 171.23
        public String targetPrice;//: 23
        public String  targetPriceDown;//: null
        public String title;//: "2020业绩预告点评：业绩超预期，品类拓展成效已现"
        public String ybabstract;//: "　　上调盈利预
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("count: ").append(data.count).append("\n");
        sb.append("list:").append(data.list.size()).append("\n");
        for(int i=0;i<data.list.size();i++){
            sb.append("list item:").append(data.list.get(i).title).append(",");
        }

        return sb.toString();
    }
}
