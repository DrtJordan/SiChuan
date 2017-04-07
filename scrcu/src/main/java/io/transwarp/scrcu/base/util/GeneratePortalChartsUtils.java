package io.transwarp.scrcu.base.util;

import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.echarts.data.Data;
import io.transwarp.scrcu.base.util.ChartUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hang_xiao
 * @date 2017/3/9
 */
public class GeneratePortalChartsUtils {

    public static final Res res = I18n.use("i18n", "zh_CN");

    /**
     * 生成访问时长的折线图数据
     *
     * @param dateType  日期类型 day,week,month,quarter,year
     * @param chartType 图表类型： LINE--折线图, BAR--柱状图
     * @param timeData  访问深度数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genUseTimeCharts(String dateType, ChartType chartType, List<List<String>> timeData) {

        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        //定义时长对应的集合用来接收对应的数据
        List<Object> lessThanTenSecList = new ArrayList<>();
        List<Object> elevenToSixtySecList = new ArrayList<>();
        List<Object> oneToTenMinList = new ArrayList<>();
        List<Object> thirtyOverMinList = new ArrayList<>();
        List<Object> tenToThirtyMinList = new ArrayList<>();

        Object[] nameList = new Object[]{"小于10秒", "11~60秒", "1~10分钟", "10~30分钟", "30分钟以上"};

        for (List<String> list : timeData) {

            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
            }
            if (list.get(1).contains("小于10秒")) {
                lessThanTenSecList.add(list.get(2));
            }
            if (list.get(1).contains("11~60秒")) {
                elevenToSixtySecList.add(list.get(2));
            }
            if (list.get(1).contains("1~10分钟")) {
                oneToTenMinList.add(list.get(2));
            }
            if (list.get(1).contains("10~30分钟")) {
                tenToThirtyMinList.add(list.get(2));
            }
            if (list.get(1).contains("30分钟以上")) {
                thirtyOverMinList.add(list.get(2));
            }

        }
        //生成使用时长的折线图数据,此处传入list数据的顺序必须按照nameList中的顺序传入，否则会造成数据对应错误
        return ChartUtils.genAppMultiLineCharts(dateType, chartType, xAxisList, nameList, lessThanTenSecList, elevenToSixtySecList,
                oneToTenMinList, tenToThirtyMinList, thirtyOverMinList);
    }

    /**
     * 生成访问深度的折线图数据
     *
     * @param dateType  日期类型 day,week,month,quarter,year
     * @param chartType 图表类型： LINE--折线图, BAR--柱状图
     * @param depthData 访问深度数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genVisitDepthCharts(String dateType, ChartType chartType, List<List<String>> depthData) {

        // 返回结果
        List<Object> depthXAxisList = new ArrayList<>();
        Object[] depthNames = new Object[]{"小于2", "2~4", "4~10", "10以上"};
        List<Object> lessThanTwoList = new ArrayList<>();
        List<Object> twoToFourList = new ArrayList<>();
        List<Object> fourToTenList = new ArrayList<>();
        List<Object> tenOverList = new ArrayList<>();

        for (List<String> list : depthData) {

            if (!depthXAxisList.contains(list.get(0))) {
                depthXAxisList.add(list.get(0));
            }
            if (list.get(1).contains("小于2")) {
                lessThanTwoList.add(list.get(2));
            }
            if (list.get(1).contains("2~4")) {
                twoToFourList.add(list.get(2));
            }
            if (list.get(1).contains("4~10")) {
                fourToTenList.add(list.get(2));
            }
            if (list.get(1).contains("10以上")) {
                tenOverList.add(list.get(2));
            }

        }
        //生成使用深度的折线图数据,此处传入list数据的顺序必须按照nameList中的顺序传入，否则会造成数据对应错误
        return ChartUtils.genAppMultiLineCharts(dateType, chartType, depthXAxisList, depthNames, lessThanTwoList, twoToFourList,
                fourToTenList, tenOverList);
    }

    /**
     * 生成流量趋势的折线图数据
     *
     * @param dateType      日期类型 day,week,month,quarter,year
     * @param chartType     图表类型： LINE--折线图, BAR--柱状图
     * @param dataFlowTrend 流量趋势的数据
     * @return 流量趋势的折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genFlowTrendCharts(String dateType, ChartType chartType, List<List<String>> dataFlowTrend) {

        List<Object> xAxisList = new ArrayList<>();
        List<Object> pvList = new ArrayList<>();
        List<Object> uvList = new ArrayList<>();
        List<Object> ipList = new ArrayList<>();
        List<Object> loginCntList = new ArrayList<>();

        for (List<String> list : dataFlowTrend) {
            xAxisList.add(list.get(0));
            pvList.add(list.get(2));
            uvList.add(list.get(3));
            ipList.add(list.get(4));
            loginCntList.add(list.get(5));
        }
        Object[] nameList = new Object[]{res.get("portal.pageViewPV"), res.get("portal.visitorUV"), res.get("portal.IPCount"), res.get("portal.loginUser")};
        //获取折线图数据
        return ChartUtils.genAppMultiLineCharts(dateType, chartType, xAxisList, nameList, pvList, uvList, ipList, loginCntList);

    }

    /**
     * 生成新访客的折线图数据
     *
     * @param dateType       日期类型 day,week,month,quarter,year
     * @param chartType      图表类型： LINE--折线图, BAR--柱状图
     * @param dataNewVisitor 新访客的数据
     * @return 新访客的折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genNewVisitorCharts(String dateType, ChartType chartType, List<List<String>> dataNewVisitor) {

        List<Object> xAxisList = new ArrayList<>();
        List<Object> newVisitorList = new ArrayList<>();

        for (List<String> list : dataNewVisitor) {
            xAxisList.add(list.get(0));
            newVisitorList.add(list.get(1));
        }
        Object[] nameList = new Object[]{"新访客"};
        //获取折线图数据
        return ChartUtils.genAppMultiLineCharts(dateType, chartType, xAxisList, nameList, newVisitorList);

    }

    /**
     * 生成搜索引擎的饼图数据
     *
     * @param searchEngineData 终端数据
     * @return 终端饼图数据
     */
    public static String genSearchEngineCharts(List<List<String>> searchEngineData) {

        List<Object> dataList = new ArrayList<Object>();
        for (List<String> list : searchEngineData) {
            Data d = new Data(list.get(0), list.get(1));
            dataList.add(d);
        }

        //生成饼图数据
        return ChartUtils.genPie(res.get("portal.visitorCount"), dataList);
    }
}
