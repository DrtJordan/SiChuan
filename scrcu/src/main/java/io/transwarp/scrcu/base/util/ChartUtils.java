package io.transwarp.scrcu.base.util;

import io.transwarp.echarts.*;
import io.transwarp.echarts.axis.*;
import io.transwarp.echarts.code.*;
import io.transwarp.echarts.data.Data;
import io.transwarp.echarts.data.TreeData;
import io.transwarp.echarts.data.WordCloudData;
import io.transwarp.echarts.json.GsonUtil;
import io.transwarp.echarts.series.*;
import io.transwarp.echarts.series.force.Link;
import io.transwarp.echarts.series.force.Node;
import io.transwarp.echarts.style.*;
import io.transwarp.echarts.style.itemstyle.Normal;
import org.apache.commons.lang.StringUtils;
import test.io.transwarp.echarts.util.EnhancedOption;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChartUtils {

    /**
     * 设置字符云参数
     *
     * @param name
     * @param dataList
     * @return
     */
    public static String genWordCloud(String name, List<Object> dataList) {
        EnhancedOption option = new EnhancedOption();
        option.tooltip().show(true);
        WordCloud wordCloud = new WordCloud(name);
        wordCloud.size("80%", "80%");
        wordCloud.textRotation(0, 45, 90, -45);
        wordCloud.textPadding(10);
        wordCloud.setData(dataList);
        option.series(wordCloud);
        return GsonUtil.format(option);
    }

    /**
     * 获取饼状图数据
     *
     * @param name
     * @param dataList
     * @return
     */
    public static String genPie(String name, List<Object> dataList) {
        EnhancedOption option = new EnhancedOption();
        //设置标题的显示位置
        option.title().text(name).x("center").y("bottom");
        //设置触发类型
        option.tooltip().trigger(Trigger.item).formatter("{b}<br/>{a}: {c} ({d}%)");
        option.calculable(false);
        //option.toolbox().show(true).feature(Tool.magicType);
        Pie p1 = new Pie(name);
        //设置饼图的内半径、外半径
        p1.radius(30, 60).setData(dataList);
        option.series(p1);
        return GsonUtil.format(option);

    }

    /**
     * 生成按照渠道分布统计的饼图数据
     *
     * @param dateType 日期类型
     * @param name     饼图名称
     * @param dataList 数据
     * @return 饼图json数据
     */
    public static String genPie(String dateType, String name, List<Object> dataList) {

        EnhancedOption option = new EnhancedOption();

        if (StringUtils.equals(dateType, "day")) {
            option.title().subtext("按天汇总").textStyle(new TextStyle().fontSize(15));
        }
        if (StringUtils.equals(dateType, "week")) {
            option.title().subtext("按周汇总").textStyle(new TextStyle().fontSize(15));
        }
        if (StringUtils.equals(dateType, "month")) {
            option.title().subtext("按月汇总").textStyle(new TextStyle().fontSize(15));
        }
        if (StringUtils.equals(dateType, "quarter")) {
            option.title().subtext("按季汇总").textStyle(new TextStyle().fontSize(15));
        }
        if (StringUtils.equals(dateType, "year")) {
            option.title().subtext("按年汇总").textStyle(new TextStyle().fontSize(15));
        }

        //设置标题的显示位置
        option.title().text(name).x("center").y("bottom");
        //设置触发类型
        option.tooltip().trigger(Trigger.item).formatter("{b}<br/>{a}: {c} ({d}%)");
        option.calculable(false);
        Pie p1 = new Pie(name);
        //设置饼图的内半径、外半径
        p1.radius(0, 70).setData(dataList);
        option.series(p1);
        return GsonUtil.format(option);

    }

    /**
     * 生成漏斗图
     *
     * @param name     名称
     * @param dataList 数据
     * @return
     */

    public static String genFunnel(String name, List<Object> dataList) {
        EnhancedOption option = new EnhancedOption();
        option.title().text(name).x("center").y("bottom");
        option.tooltip().trigger(Trigger.item).formatter("{b}<br/>{a}: {c}");
        option.calculable(false);
        Funnel funnel = new Funnel(name);
        funnel.sort(Sort.ascending).setData(dataList);
        option.series(funnel);
        return GsonUtil.format(option);
    }

    /**
     * 用户交易金额分布图形数据
     *
     * @param name
     * @param map
     * @return
     */
    public static String genPortraitPie(String name, java.util.Map<String, Integer> map) {
        List<Object> list = new ArrayList<>();
        for (String key : map.keySet()) {
            Data data = new Data(key, map.get(key));
            if (!data.getValue().equals(0)) {
                list.add(data);
            }
        }
        EnhancedOption option = new EnhancedOption();
        option.title().text(name).x("left").y("top");
        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
        option.toolbox().show(false);
        option.calculable(false);
        Pie p1 = new Pie(name);
        p1.radius(20, 50).setData(list);
        option.series(p1);
        return GsonUtil.format(option);

    }

    /**
     * 生成性别和终端饼图数据
     *
     * @param name     图名称
     * @param dataList 数据
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String genMutilPie(String name, List<Object>... dataList) {
        EnhancedOption option = new EnhancedOption();
        option.title().text(name).x("center").y("bottom");
        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
        option.toolbox().show(false);
        option.calculable(false);
        for (int i = 0; i < dataList.length; i++) {
            Pie p1 = new Pie(name);
            p1.radius("30%").center(30 * (i + 1) + "%", "50%").setData(dataList[i]);
            option.series(p1);
        }
        return GsonUtil.format(option);

    }

    /**
     * 生成首页中多条折线图型数据
     *
     * @param xAxisList x轴
     * @param nameList  图例名称
     * @param dataList  数据
     * @return chart json
     */
    @SuppressWarnings("unchecked")
    public static String genMultiLineChart(List<Object> xAxisList, Object[] nameList, List<Object>... dataList) {
        EnhancedOption option = new EnhancedOption();
        option.tooltip().trigger(Trigger.axis);
        Legend legend = new Legend();
        //将名称设置为底部显示
        legend.y("bottom").data(nameList);
        option.legend(legend);
        option.toolbox().show(true);
        option.calculable(false);
        //设置类目起始和结束两端空白策略，true：留空，false：顶头
        CategoryAxis axis = new CategoryAxis().boundaryGap(false);
        //给x轴添加数据
        axis.setData(xAxisList);
        option.xAxis(axis);
        option.yAxis(new ValueAxis());
        Grid grid = new Grid();
        //设置x，y轴的位置
        grid.x("60").x2("30").y("10").y2("60");
        option.grid(grid);
        for (int i = 0; i < dataList.length; i++) {
            List<Object> list = dataList[i];
            Line line = new Line().smooth(true).name(String.valueOf(nameList[i]));
            line.setData(list);
            option.series(line);
        }

        return GsonUtil.format(option);

    }

    /**
     * 生成app统计的折线图
     *
     * @param dateType  日期类型：day,week,month,quarter,year
     * @param chartType 图表类型： LINE--折线图, BAR--柱状图
     * @param xAxisList X周数据
     * @param nameList  图例名称
     * @param dataList  数据列表
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String genAppMultiLineCharts(String dateType, ChartType chartType, List<Object> xAxisList, Object[] nameList, List<Object>... dataList) {

        EnhancedOption option = new EnhancedOption();
        option.tooltip().trigger(Trigger.axis);

        if (StringUtils.equals(dateType, "day")) {
            option.title().subtext("按天汇总").x("right").textStyle(new TextStyle().fontSize(15));
        }
        if (StringUtils.equals(dateType, "week")) {
            option.title().subtext("按周汇总").x("right").textStyle(new TextStyle().fontSize(15));
        }
        if (StringUtils.equals(dateType, "month")) {
            option.title().subtext("按月汇总").x("right").textStyle(new TextStyle().fontSize(15));
        }
        if (StringUtils.equals(dateType, "quarter")) {
            option.title().subtext("按季汇总").x("right").textStyle(new TextStyle().fontSize(15));
        }
        if (StringUtils.equals(dateType, "year")) {
            option.title().subtext("按年汇总").x("right").textStyle(new TextStyle().fontSize(15));
        }

        Legend legend = new Legend();
        //将名称设置为底部显示
        legend.y("bottom").data(nameList);
        option.legend(legend);
        option.toolbox().show(true);
        //设置类目起始和结束两端空白策略，true：留空，false：顶头
        CategoryAxis axis = new CategoryAxis().boundaryGap(false);

        //判断是否为柱状图，若是，则设置空白策略以及坐标轴指示器样式
        if (chartType.equals(ChartType.BAR)) {
            axis.boundaryGap(true);
            option.tooltip().axisPointer().setType(PointerType.shadow);
        }
        //给x轴添加数据
        axis.setData(xAxisList);
        option.xAxis(axis);
        option.yAxis(new ValueAxis());

        for (int i = 0; i < dataList.length; i++) {
            List<Object> list = dataList[i];
            if (chartType.equals(ChartType.LINE)) {
                Line line = new Line().smooth(true).name(String.valueOf(nameList[i]));
                //设置折线的样式(width表示线条粗细)
                line.itemStyle().normal().setLineStyle(new LineStyle().width(1));
                line.setData(list);
                option.series(line);
            }
            if (chartType.equals(ChartType.BAR)) {
                Bar bar = new Bar().name(String.valueOf(nameList[i]));
                if (xAxisList.size() != 0 && !xAxisList.get(0).equals("Android")) {
                    bar.setStack("bar");
                }
                bar.setData(list);
                option.series(bar);
            }

        }
        Grid grid = new Grid();
        //设置x，y轴的位置
        grid.x("60").x2("30").y("5").y2("60");

        //判断是否为折线图，若是，则设置折线图的值域范围以及显示位置
        if (chartType.equals(ChartType.LINE)) {
            //设置值域的选择范围以及样式
            DataZoom dataZoom = new DataZoom();
            //设置数据缩放结束比例
            dataZoom.setEnd(30);
            dataZoom.y(226);
            dataZoom.show(true);
            option.setDataZoom(dataZoom);
            grid.setHeight("70%");
        }

        option.grid(grid);

        return GsonUtil.format(option);

    }

    /**
     * 业务分析生成折线图
     *
     * @param name
     * @param xAxisList
     * @param dataList
     * @return
     */
    public static String genLineChart(String name, List<Object> xAxisList, List<Object> dataList) {
        EnhancedOption option = new EnhancedOption();
        option.tooltip().trigger(Trigger.axis);
        Legend legend = new Legend();
        legend.y("bottom").data(name);
        option.legend(legend);
        option.toolbox().show(true);
        option.calculable(true);
        CategoryAxis axis = new CategoryAxis().boundaryGap(false);
        axis.setData(xAxisList);
        option.xAxis(axis);
        option.yAxis(new ValueAxis());
        Line line = new Line().smooth(true).name(name);
        line.setData(dataList);
        Grid grid = new Grid();
        //设置折线图位置样式布局
        grid.x("60").x2("30").y("20").y2("60");
        option.grid(grid);
        option.series(line);
        return GsonUtil.format(option);

    }

    /**
     * 四川省地域分布图
     *
     * @param name     名称
     * @param dataList 数据
     * @param maxValue 最大值
     * @return
     */
    public static String genMapChart(String name, String mapType, List<Object> dataList, Integer maxValue) {

        EnhancedOption option = new EnhancedOption();
        option.setBackgroundColor("#E0EAEC");

        Legend legend = new Legend();
        legend.x("right").data(name);
        option.legend(legend);

        //设置地图数据值范围展示及其显示位置
        DataRange dr = new DataRange();
        dr.calculable(true);
        dr.min(0);
        //设置访客数最大值
        dr.max(maxValue);
        dr.x("left");
        dr.y("bottom");
        dr.text("高, 低");
        option.dataRange(dr);

        //设置工具箱提示文字
        Toolbox tb = new Toolbox();
        tb.show(true);
        option.toolbox(tb);

        Map map = new Map("Map");
        if (dataList.size() > 0) {
            for (Object aDataList : dataList) {
                Data ts = (Data) aDataList;
                Data data = new Data(ts.getName());
                data.value(ts.getValue());
                map.data(data);
            }
        } else {
            Data data = new Data();
            map.data(data);
        }

        map.itemStyle().normal().label().show(true);
        map.itemStyle().emphasis().label().show(true);
        map.setMapType(mapType);
        map.setSelectedMode(SelectedMode.single);
        map.setName(name);

        option.title().text("全国34个省市自治区").x(X.left).y(Y.top);
        option.title().subtext("（点击切换）");
        option.series(map);
        return GsonUtil.format(option);

    }

    /**
     * 生成人际关系图表
     *
     * @param name      图名称
     * @param dataList
     * @param dataList1
     * @return
     */
    public static String getForce(String name, List<Object> dataList, List<Object> dataList1) {
        EnhancedOption option = new EnhancedOption();
        option.tooltip().trigger(Trigger.item).formatter("{a} : {b}");
        option.legend("收入", "支出").legend().x(X.left);

        // 数据
        Force force = new Force("交易关系");
        force.categories("类型", "收入", "支出");
        if (!name.equals("")) {
            force.itemStyle().normal().label(new Label().show(true).textStyle(new TextStyle().color("#333"))).nodeStyle()
                    .brushType(BrushType.both).borderWidth(1);

            force.itemStyle().emphasis().linkStyle(new LinkStyle()).nodeStyle(new NodeStyle()).label().show(true);
            force.useWorker(false).minRadius(15).maxRadius(25).gravity(1.1).scaling(1.1).linkSymbol(Symbol.arrow);
            // 主要人物
            force.nodes(new Node(0, name, 12));
            // 分之人物
            for (Object object : dataList) {
                Node node = new Node();
                node.setCategory(1);
                node.setName(object.toString());
                node.setValue(13);
                force.nodes(node);
            }

            for (Object object : dataList) {
                Link link = new Link();
                link.setSource(object);
                link.setTarget(name);
                link.setWeight(1);
                force.links(link);
            }

            for (Object object : dataList1) {
                Node node = new Node();
                node.setCategory(2);
                node.setName(object.toString());
                node.setValue(14);
                force.nodes(node);
            }

            for (Object object : dataList1) {
                Link link = new Link();
                link.setSource(name);
                link.setTarget(object);
                link.setWeight(3);
                force.links(link);
            }
        }
        option.series(force);
        return GsonUtil.format(option);
    }

    /**
     * 生成用户关注度雷达图
     *
     * @param name 名称
     * @param data 数据
     * @return
     */
    public static String genRadar(String name, java.util.Map<String, Integer> data) {
        EnhancedOption option = new EnhancedOption();
        option.title().text(name);
        option.tooltip().trigger(Trigger.axis);
        option.toolbox().show(false).feature(Tool.mark, Tool.dataView, Tool.restore, Tool.saveAsImage);
        option.calculable(true);
        Polar polar = new Polar();
        int count = 0;

        if (data != null) {

            for (int i : data.values()) {
                count = count / 4 * 3;
                if (i > count) {
                    count = i;
                }
                count = count * 4 / 3;
            }

            for (String s : data.keySet()) {
                polar.indicator(new Data().text(s).max(count));
            }
            option.polar(polar.radius(60));

            Radar radar = new Radar();
            radar.itemStyle().normal().areaStyle().typeDefault();
            radar.data(new Data().name(name).setValue(data.values()));
            option.series(radar);
        }

        return GsonUtil.format(option);
    }

    /**
     * 生成注册年限的图表json数据
     *
     * @param name  图表名称
     * @param key   key
     * @param value value
     * @return
     */
    public static String genRadar(String name, List<String> key, List<Integer> value) {
        EnhancedOption option = new EnhancedOption();
        option.title().text(name).x("center").y("bottom");
        option.tooltip().trigger(Trigger.axis);
        option.toolbox().show(false).feature(Tool.mark, Tool.dataView, Tool.restore, Tool.saveAsImage);
        option.calculable(true);
        Polar polar = new Polar();
        int count = 0;
        for (int i : value) {
            count = count / 4 * 3;
            if (i > count) {
                count = i;
            }
            count = count * 4 / 3;
        }

        if (key != null) {
            for (String s : key) {
                polar.indicator(new Data().text(s).max(count));
            }
            option.polar(polar.radius(60));
        }
        Radar radar = new Radar();
        radar.itemStyle().normal().areaStyle().typeDefault();
        radar.data(new Data().name(name).setValue(value));
        option.series(radar);
        return GsonUtil.format(option);
    }

    /**
     * 生成随机颜色样式
     *
     * @return 颜色
     */
    private static Normal createRandomItemStyle() {
        Normal normal = new Normal();
        normal.color("rgb(" + Math.round(Math.random() * 160) + "," + Math.round(Math.random() * 160) + ","
                + Math.round(Math.random() * 160) + ")");
        return normal;
    }

    /**
     * 生成树状图
     *
     * @param name
     * @param dataList
     * @return
     */
    public static String genTree(String name, List<String> dataList) {
        EnhancedOption option = new EnhancedOption();
        option.title().text("家庭关系");
        option.tooltip().trigger(Trigger.item).formatter("{b}: {c}");
        option.toolbox().show(false);
        option.calculable(false);

        Tree tree = new Tree("树图");
        tree.symbol(Symbol.rectangle);
        tree.orient(Orient.vertical).nodePadding(1).rootLocation().x(X.center).y(50);
        tree.itemStyle().normal().label(new Label().position(Position.inside).formatter("{b}")).lineStyle().width(3)
                .color("#545CA6").type(LineType.broken);
        tree.itemStyle().emphasis().label().show(true);
        List<Integer> size = new ArrayList<Integer>();
        size.add(60);
        size.add(30);
        TreeData root = new TreeData(name, 4).symbolSize(size);

        List<TreeData> children1 = new LinkedList<TreeData>();
        if (dataList != null) {
            for (String str : dataList) {
                children1.add(new TreeData(str, 4).symbolSize(size));

            }
        }

        root.setChildren(children1);

        tree.data(root);

        option.series(tree);
        return GsonUtil.format(option);
    }

    public static String genPercentBar(String title, String name, List<Object> key, List<Object> values) {
        EnhancedOption option = new EnhancedOption();
        Grid grid = new Grid();
        grid.x("40").x2("10").y("10").y2("60");
        option.grid(grid);
        option.title().x("left").text(title).subtext("").show(false);
        option.tooltip().trigger(Trigger.axis);
        Legend legend = new Legend();
        legend.y("bottom").data(name);
        option.legend(legend);
        option.toolbox().show(false);
        option.calculable(true);
        CategoryAxis axis = new CategoryAxis();
        axis.setData(key);
        option.xAxis(axis);
        option.yAxis(new ValueAxis());

        Bar bar = new Bar(name);
        bar.itemStyle(new ItemStyle()
                .normal(new Normal().label(new Label().show(true).position(Position.inside).formatter("{c}%"))));
        bar.setData(values);
        option.series(bar);

        return GsonUtil.format(option);
    }

    public static String genBar(String title, String name, List<Object> key, List<Object> values) {
        EnhancedOption option = new EnhancedOption();
        Grid grid = new Grid();
        grid.x("60").x2("10").y("10").y2("60");
        option.grid(grid);
        option.title().x("left").text(title).subtext("").show(false);
        option.tooltip().trigger(Trigger.axis);
        Legend legend = new Legend();
        legend.y("bottom").data(name);
        option.legend(legend);
        option.toolbox().show(false);
        option.calculable(true);
        CategoryAxis axis = new CategoryAxis();
        axis.setData(key);
        option.xAxis(axis);
        option.yAxis(new ValueAxis());

        Bar bar = new Bar(name);
        bar.setData(values);
        option.series(bar);
        return GsonUtil.format(option);
    }

    /*  public static void main(String[] args) {
        // List<Object> xAxisListMoney = new ArrayList<Object>();
        // xAxisListMoney.add("历史1");
        // xAxisListMoney.add("历史2");
        // xAxisListMoney.add("历史3");
        // xAxisListMoney.add("历史4");
        // xAxisListMoney.add("历史5");
        // List<Object> xAxisListMoney1 = new ArrayList<Object>();
        // xAxisListMoney1.add("物理1");
        // xAxisListMoney1.add("物理2");
        // xAxisListMoney1.add("物理3");
        // xAxisListMoney1.add("物理4");
        // xAxisListMoney1.add("物理5");
        // getForce("张三",xAxisListMoney,xAxisListMoney1);
        List<String> dataList = new ArrayList<String>();
        dataList.add("李四");
        dataList.add("王五");
        genTree("张三", dataList);

    }*/
}
