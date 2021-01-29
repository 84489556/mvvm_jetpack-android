package com.yuanda.cy_professional_select_stock.view.echarts.entity;

import com.github.abel533.echarts.series.Gauge;
import com.yuanda.cy_professional_select_stock.view.echarts.entity.gauge.ProgressYd;

/**
 * GaugeYd
 * Created by sh on 2021-01-07.
 */

public class GaugeYd extends Gauge {
    private ProgressYd progress;

    public GaugeYd progress(ProgressYd progress){
        this.progress = progress;
        return this;
    }
}
