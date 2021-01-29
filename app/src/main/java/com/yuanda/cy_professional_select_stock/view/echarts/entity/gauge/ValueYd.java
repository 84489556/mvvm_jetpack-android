package com.yuanda.cy_professional_select_stock.view.echarts.entity.gauge;

/**
 * ValueYd
 * Created by sh on 2021-01-07.
 *  fontSize: 50,
 *  fontWeight: 'bolder',
 *  color: '#777'
 */

public class ValueYd {
    private Integer fontSize;
    private Object fontWeight;
    private Object color;

    public ValueYd fontSize(Integer fontSize){
        this.fontSize = fontSize;
        return this;
    }
    public ValueYd fontWeight(Object fontWeight){
        this.fontWeight = fontWeight;
        return this;
    }
    public ValueYd color(Object color){
        this.color = color;
        return this;
    }
}
