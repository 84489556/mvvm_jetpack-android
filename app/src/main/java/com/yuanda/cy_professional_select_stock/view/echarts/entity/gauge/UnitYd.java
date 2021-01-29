package com.yuanda.cy_professional_select_stock.view.echarts.entity.gauge;

/**
 * ValueYd
 * Created by sh on 2021-01-07.
 *  fontSize: 50,
 *  fontWeight: 'bolder',
 *  color: '#777'
 */

public class UnitYd {
    private Integer fontSize;
    private Object padding;
    private Object color;

    public UnitYd fontSize(Integer fontSize){
        this.fontSize = fontSize;
        return this;
    }
    public UnitYd padding(Object padding){
        this.padding = padding;
        return this;
    }
    public UnitYd color(Object color){
        this.color = color;
        return this;
    }
}
