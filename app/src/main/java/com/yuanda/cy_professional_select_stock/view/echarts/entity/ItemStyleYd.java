package com.yuanda.cy_professional_select_stock.view.echarts.entity;

import com.github.abel533.echarts.style.ItemStyle;

/**
 * ItemStyleYd
 * Created by sh on 2021-01-07.
 *              color: '#5833F9',
 *             shadowColor: 'rgba(0,138,255,0.45)',
 *             shadowBlur: 10,
 *             shadowOffsetX: 2,
 *             shadowOffsetY: 2
 */

public class ItemStyleYd extends ItemStyle {

    Object color;

    public ItemStyleYd color(Object color){
        this.color = color;
        return this;
    }
}
