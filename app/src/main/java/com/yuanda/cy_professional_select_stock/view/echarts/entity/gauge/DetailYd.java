package com.yuanda.cy_professional_select_stock.view.echarts.entity.gauge;

import com.github.abel533.echarts.series.gauge.Detail;

/**
 * DetailYd
 * Created by sh on 2021-01-07.
 *
 */

public class DetailYd extends Detail {
    private RichYd rich;

    public DetailYd rich(RichYd rich){
        this.rich = rich;
        return this;
    }
}
