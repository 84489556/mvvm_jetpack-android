package com.yuanda.cy_professional_select_stock.view.echarts;

import com.github.abel533.echarts.Grid;
import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.axis.AxisTick;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.SplitLine;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.gauge.Detail;
import com.github.abel533.echarts.style.ItemStyle;
import com.yuanda.cy_professional_select_stock.view.echarts.entity.GaugeYd;
import com.yuanda.cy_professional_select_stock.view.echarts.entity.gauge.DetailYd;
import com.yuanda.cy_professional_select_stock.view.echarts.entity.gauge.PointerYd;
import com.yuanda.cy_professional_select_stock.view.echarts.entity.gauge.ProgressYd;

public class EchartOptionUtil {

    public static GsonOption getLineChartOptions(Object[] xAxis, Object[] yAxis) {
        GsonOption option = new GsonOption();
        option.title("折线图");
        option.legend("销量");
        option.tooltip().trigger(Trigger.axis);

        ValueAxis valueAxis = new ValueAxis();
        option.yAxis(valueAxis);

        CategoryAxis categorxAxis = new CategoryAxis();
        categorxAxis.axisLine().onZero(false);
        categorxAxis.boundaryGap(true);
        categorxAxis.data(xAxis);
        option.xAxis(categorxAxis);

        Line line = new Line();
        line.smooth(false).name("销量").data(yAxis).itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
        option.series(line);
        return option;
    }

    /**
     * 折线图
     *
     * @return
     */
    public static GsonOption getLineChartOptions() {
        //创建Option
        GsonOption option = new GsonOption();

        //横轴为值轴x
        option.xAxis(new ValueAxis().type(AxisType.category));
        //横轴为值轴
        option.yAxis(new ValueAxis().type(AxisType.value));

        option.grid(new Grid().bottom(10).left(20).right(20).top(30).containLabel(true));

        Line line = new Line();
        line.type(SeriesType.line);
//        line.areaStyle(new AreaStyle().color("#ff55ee"));
        line.data(150, 230, 224, 218, 135, 147, 260,110,null,null,null,null,null,null);
        Line line2 = new Line();
        line2.type(SeriesType.line);
//        line.areaStyle(new AreaStyle().color("#ff55ee"));
        line2.showAllSymbol(false);
        line2.data(50, 330, 204, 218, 235, 47, 60,310,null,null,null,null,60,null);

        option.series(line, line2);
        return option;
    }
    /**
     * 折线和柱状图混合图
     *
     * @return
     */
    public static GsonOption getGauGeChartOptions() {
        //创建Option
        GsonOption option = new GsonOption();

        //横轴为值轴x
        option.xAxis(new ValueAxis().type(AxisType.category));
        //横轴为值轴
        option.yAxis(new ValueAxis().type(AxisType.value));

        GaugeYd gauge = new GaugeYd();
        gauge.startAngle(180)
                .type(SeriesType.gauge)
                .endAngle(0)
                .min(0)
                .max(100)
                .splitNumber(20)
                .data(40)
                .itemStyle(new ItemStyle().color("#5833F9"));
        Object[] aa= {0,"-30%"};
        Detail detail = new DetailYd()
//                .rich(new RichYd().value(new ValueYd().color("#777").fontSize(50).fontWeight("bolder"))
//                        .unit(new UnitYd().fontSize(20).color("#999")))
                .height("15%").offsetCenter(aa)
//                .formatter("function (value) { return '{value|' + value.toFixed(0) + '}{unit|km/h}'; }")
                .formatter(" {value}km/h")
                ;

        gauge.progress(new ProgressYd().show(true).roundCap(true))
                .axisTick(new AxisTick().show(false))
                .splitLine(new SplitLine().show(false))
                .axisLabel(new Label().show(false))
                .pointer(new PointerYd().setShow(false))
                .center("25%", "50%")
                .detail(detail) ;

        GaugeYd gauge2 = new GaugeYd();
        gauge2.startAngle(180)
                .type(SeriesType.gauge)
                .endAngle(0)
                .min(0)
                .max(100)
                .splitNumber(20)
                .data(40)
                .itemStyle(new ItemStyle().color("#5833F9"));
//        Object[] aa= {0,"-30%"};
        Detail detail2 = new DetailYd()
//                .rich(new RichYd().value(new ValueYd().color("#777").fontSize(50).fontWeight("bolder"))
//                        .unit(new UnitYd().fontSize(20).color("#999")))
                .height("15%").offsetCenter(aa)
//                .formatter("function (value) { return '{value|' + value.toFixed(0) + '}{unit|km/h}'; }")
                .formatter(" {value} C")
                ;

        gauge2.progress(new ProgressYd().show(true).roundCap(true))
                .axisTick(new AxisTick().show(false))
                .splitLine(new SplitLine().show(false))
                .axisLabel(new Label().show(false))
                .pointer(new PointerYd().setShow(false))
                .center("75%", "50%")
                .detail(detail2) ;

        option.series( gauge, gauge2);
        return option;
    }
}