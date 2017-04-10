package io.transwarp.scrcu.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.echarts.data.Data;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.ChartType;
import io.transwarp.scrcu.base.util.ChartUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

import java.util.ArrayList;
import java.util.List;

import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.genMemberRunOffCharts;

/**
 * Web端用户分析的业务逻辑层
 *
 * @author hang_xiao
 * @date 2017/4/10
 */
public class UserAnalysisService {

    //引入国际化配置
    private static Res res = I18n.use("i18n", "zh_CN");

    /**
     * 生成web端地域分布的json数据
     *
     * @param dateType  日期类型：day,month,quarter,year
     * @param condition 查询条件
     * @return area json
     */
    public static JSONObject area(String dateType, String condition) {

        // 执行查询
        List<List<String>> areaData = new ArrayList<>();
        if (dateType != null) {
            if (dateType.equals("day")) {
                // 执行查询
                areaData = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_area_query_day, condition), false);
            }

            if (dateType.equals("month")) {
                // 执行查询
                areaData = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_area_query_month, condition), false);
            }

            if (dateType.equals("quarter")) {
                // 执行查询
                areaData = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_area_query_quarter, condition), false);
            }

            if (dateType.equals("year")) {
                // 执行查询
                areaData = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_area_query_year, condition), false);
            }
        }

        //查询访客数最大值
        List<List<String>> maxUv = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_max_uv));
        // 返回结果
        JSONObject result = new JSONObject();
        List<Object> dataList = new ArrayList<>();
        for (List<String> list : areaData) {
            String name = list.get(0).replace(res.get("area.ganZiZhou"), res.get("area.ganZiZhouAll")).replace(res.get("area.aBaZhou"), res.get("area.aBaZhouAll")).replace(res.get("area.liangShanZhou"), res.get("area.liangShanZhouAll"));
            //获取访客数量
            Data uvValue = new Data(name, list.get(1));
            dataList.add(uvValue);
        }

        //判断地域值域的最大值是否为0
        Integer maxValue = 0;
        if (maxUv.size() != 0) {
            maxValue = Integer.valueOf(maxUv.get(0).get(0));
        }
        //生成四川省地域分布图
        String areaMap = ChartUtils.genMapChart(res.get("portal.visitor"), dataList, maxValue);
        result.put("chartOption", areaMap);
        result.put("areaData", areaData);

        return result;
    }

    /**
     * 生成web端终端详情的json数据
     *
     * @param dateType  日期类型：day,month
     * @param condition 查询条件
     * @return terminal json
     */
    public static JSONObject terminal(String dateType, String condition) {

        //定义终端操作系统以及浏览器的集合用来接收数据
        List<List<String>> terminalOsData = new ArrayList<>();
        List<List<String>> terminalBrowserData = new ArrayList<>();
        List<List<String>> terminalOsChart = new ArrayList<>();
        List<List<String>> terminalBrowserChart = new ArrayList<>();

        if (dateType != null) {
            if (dateType.equals("day")) {
                // 执行查询
                terminalOsData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_terminal_os_day, condition),
                        false);
                terminalBrowserData = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_terminal_browser_day, condition), false);
                terminalOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_terminal_os_day_chart, condition));
                terminalBrowserChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_terminal_browser_day_chart, condition));
            }

            if (dateType.equals("month")) {
                // 执行查询
                terminalOsData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_terminal_os_month, condition),
                        false);
                terminalBrowserData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_terminal_browser_month, condition), false);

                terminalOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_terminal_os_month_chart, condition));
                terminalBrowserChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_terminal_browser_month_chart, condition));
            }
        }

        // 返回结果
        List<Object> osChartList = new ArrayList<>();
        List<Object> browserChartList = new ArrayList<>();
        for (List<String> list : terminalOsChart) {
            Data d = new Data(list.get(0), InceptorUtil.getDouble(list.get(1)));
            if (!list.get(1).equals("999")) {
                osChartList.add(d);
            }
        }
        for (List<String> list : terminalBrowserChart) {
            Data d = new Data(list.get(0), InceptorUtil.getDouble(list.get(1)));
            if (!list.get(1).equals("999")) {
                browserChartList.add(d);
            }
        }
        // 返回结果
        JSONObject result = new JSONObject();
        String osChartOption = ChartUtils.genPie(res.get("portal.visitorCount"), osChartList);
        String browserChartOption = ChartUtils.genPie(res.get("portal.visitorCount"), browserChartList);
        // 生成图
        result.put("osChartOption", osChartOption);
        result.put("browserChartOption", browserChartOption);

        result.put("terminalOsData", terminalOsData);
        result.put("terminalBrowserData", terminalBrowserData);

        return result;
    }

    /**
     * 生成web端会员访问分析的json数据
     *
     * @param dateType  日期类型：day,month
     * @param condition 查询条件
     * @return userVisitPage json
     */
    public static JSONObject userVisitPage(String dateType, String condition) {

        List<List<String>> dataPage = new ArrayList<>();

        if (dateType != null) {
            if (dateType.equals("day")) {
                // 执行查询
                dataPage = InceptorUtil
                        .query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_userPage_query_day, condition));
            }

            if (dateType.equals("month")) {
                // 执行查询
                dataPage = InceptorUtil
                        .query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_userPage_query_month, condition));
            }
        }
        // 返回结果
        JSONObject result = new JSONObject();
        result.put("dataPage", dataPage);

        return result;
    }

    /**
     * 生成web端流失用户分析的json数据
     *
     * @param dateType  日期类型：day,week,month,quarter,year
     * @param condition 查询条件
     * @return userLoss json
     */
    public static JSONObject userLoss(String dateType, String condition) {

        List<List<String>> dataLoss = new ArrayList<>();
        // 返回结果
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                // 执行查询
                dataLoss = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_userLoss_query_day, condition), false);
            }
            if (dateType.equals("week")) {
                // 执行查询
                dataLoss = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_userLoss_query_week, condition), false);
            }

            if (dateType.equals("month")) {
                // 执行查询
                dataLoss = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_userLoss_query_month, condition), false);
            }

            if (dateType.equals("quarter")) {
                // 执行查询
                dataLoss = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_userLoss_query_quarter, condition), false);
            }

            if (dateType.equals("year")) {
                // 执行查询
                dataLoss = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_userLoss_query_year, condition), false);
            }
        }

        //返回会员流失生成的折线图图表数据
        result.put("chartOption", genMemberRunOffCharts(dateType, ChartType.LINE, dataLoss));
        //返回用户流失的数据
        result.put("dataLoss", dataLoss);

        return result;
    }

    /**
     * 生成web端唯一用户分析的json数据
     *
     * @param dateType  日期类型：day,week,month,quarter,year
     * @param condition 查询条件
     * @return userOnly json
     */
    @SuppressWarnings("unchecked")
    public static JSONObject userOnly(String dateType, String condition) {

        List<List<String>> dataUserOnly = new ArrayList<>();
        // 返回结果
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                // 执行查询
                dataUserOnly = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_userAnalysis_userOnly_day, condition), false);
            }
            if (dateType.equals("week")) {
                // 执行查询
                dataUserOnly = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_userAnalysis_userOnly_week, condition), false);
            }

            if (dateType.equals("month")) {
                // 执行查询
                dataUserOnly = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_userAnalysis_userOnly_month, condition), false);
            }

            if (dateType.equals("quarter")) {
                // 执行查询
                dataUserOnly = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_userAnalysis_userOnly_quarter, condition), false);
            }

            if (dateType.equals("year")) {
                // 执行查询
                dataUserOnly = InceptorUtil
                        .queryCache(SqlKit.propSQL(SQLConfig.portal_userAnalysis_userOnly_year, condition), false);
            }
        }

        List<Object> xAxisList = new ArrayList<>();
        List<Object> guestCntList = new ArrayList<>();
        List<Object> loginCntList = new ArrayList<>();
        List<Object> ipCntList = new ArrayList<>();
        Object[] nameList = new Object[]{res.get("portal.visitorUser"), res.get("portal.LoginUser"), res.get("portal.IPCount")};

        for (List<String> list : dataUserOnly) {

            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
            }
            guestCntList.add(list.get(1));
            loginCntList.add(list.get(2));
            ipCntList.add(list.get(3));

        }
        //生成会员流失的折线图数据,此处传入list数据的顺序必须按照nameList中的顺序传入，否则会造成数据对应错误
        String genUserLossChart = ChartUtils.genAppMultiLineCharts(dateType, ChartType.LINE, xAxisList, nameList, guestCntList, loginCntList, ipCntList);
        result.put("chartOption", genUserLossChart);

        result.put("dataUserOnly", dataUserOnly);

        return result;
    }
}
