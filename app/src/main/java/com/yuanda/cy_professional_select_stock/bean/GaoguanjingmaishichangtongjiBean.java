package com.yuanda.cy_professional_select_stock.bean;

import com.yuanda.cy_professional_select_stock.base.BaseResponse;

import java.util.List;

public class GaoguanjingmaishichangtongjiBean extends BaseResponse {


    private List<GaoguanjingmaishichangtongjiBean.Data> data;

    public void setData(List<GaoguanjingmaishichangtongjiBean.Data> data) {
        this.data = data;
    }
    public List<GaoguanjingmaishichangtongjiBean.Data> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "GaoguanjingmaishichangtongjiBean{" +
                "data=" + data +
                '}';
    }

    public static  class Data{
        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", shares=" + shares +
                    ", chgdate='" + chgdate + '\'' +
                    ", mktcode='" + mktcode + '\'' +
                    ", boardcode='" + boardcode + '\'' +
                    '}';
        }

        private int id;
        private int shares;
        private String chgdate;
        private String mktcode;
        private String boardcode;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setShares(int shares) {
            this.shares = shares;
        }
        public int getShares() {
            return shares;
        }

        public void setChgdate(String chgdate) {
            this.chgdate = chgdate;
        }
        public String getChgdate() {
            return chgdate;
        }

        public void setMktcode(String mktcode) {
            this.mktcode = mktcode;
        }
        public String getMktcode() {
            return mktcode;
        }

        public void setBoardcode(String boardcode) {
            this.boardcode = boardcode;
        }
        public String getBoardcode() {
            return boardcode;
        }
    }
}