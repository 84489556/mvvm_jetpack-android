package com.yuanda.cy_professional_select_stock.view.echarts.entity.gauge;

/**
 * RichYd
 * Created by sh on 2021-01-07.
 *
 */

public class RichYd {
    private ValueYd value;
    private UnitYd unit;

    public RichYd value(ValueYd value){
        this.value = value;
        return this;
    }
    public RichYd unit(UnitYd unit){
        this.unit = unit;
        return this;
    }

}
