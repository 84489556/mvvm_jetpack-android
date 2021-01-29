package com.yuanda.cy_professional_select_stock.view.echarts.entity.gauge;

/**
 * ProgressYd
 * Created by sh on 2021-01-07.
 *    show: true,
 *    roundCap: true,
 *    width: 18
 */

public class ProgressYd {
    private Boolean show;
    private Boolean roundCap;
    private Integer width;

    public ProgressYd show(Boolean show){
        this.show = show;
        return this;
    }
    public ProgressYd roundCap(Boolean roundCap){
        this.roundCap = roundCap;
        return this;
    }
    public ProgressYd width(Integer width){
        this.width = width;
        return this;
    }
}
