package io.transwarp.scrcu.portal;

import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.ChartUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

@RequiresAuthentication
public class SiteAnalysisController extends Controller {

    Res res = I18n.use("i18n", "zh_CN");

    /**
     * 访问分析
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/portal/siteAnalysis/visitAnalysis")
    public void visitAnalysis() {
        if (BaseUtils.isAjax(getRequest())) {

            // 返回结果
            JSONObject result = new JSONObject();
            //页面时长
            List<List<String>> timeData = new ArrayList<>();
            //页面深度
            List<List<String>> depthData = new ArrayList<>();

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            //获取日期类型，dateType：day,week,month,quarter,year
            String dateType = getPara("dateType");

            if (dateType != null) {
                if (dateType.equals("day")) {
                    // 执行查询
                    timeData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_visitAnalysis_time_day, condition), false);
                    depthData = InceptorUtil
                            .queryCache(SqlKit.propSQL(SQLConfig.portal_visitAnalysis_depth_day.toString(), condition), false);
                }

                if (dateType.equals("month")) {
                    // 执行查询
                    timeData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_visitAnalysis_time_month, condition), false);
                    depthData = InceptorUtil
                            .queryCache(SqlKit.propSQL(SQLConfig.portal_visitAnalysis_depth_month.toString(), condition), false);
                }

                if (dateType.equals("quarter")) {
                    // 执行查询
                    timeData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_visitAnalysis_time_quarter, condition), false);
                    depthData = InceptorUtil
                            .queryCache(SqlKit.propSQL(SQLConfig.portal_visitAnalysis_depth_quarter.toString(), condition), false);
                }

                if (dateType.equals("year")) {
                    // 执行查询
                    timeData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_visitAnalysis_time_year, condition), false);
                    depthData = InceptorUtil
                            .queryCache(SqlKit.propSQL(SQLConfig.portal_visitAnalysis_depth_year.toString(), condition), false);
                }
            }
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
            String genUseTime = ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList, lessThanTenSecList, elevenToSixtySecList,
                    oneToTenMinList, tenToThirtyMinList, thirtyOverMinList);
            result.put("useTimeChart", genUseTime);

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
            String genUseDepth = ChartUtils.genAppMultiLineCharts(dateType, depthXAxisList, depthNames, lessThanTwoList, twoToFourList,
                    fourToTenList, tenOverList);
            result.put("useDepthChart", genUseDepth);

            result.put("timeData", timeData);
            result.put("depthData", depthData);
            renderJson(result);
        }
    }

    /**
     * 页面分析 现在功能暂时没有用到该方法
     */
    // @RequiresPermissions("/portal/siteAnalysis/pageAnalysis")
    @Deprecated
    public void pageAnalysis() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> data = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_pageAnalysis.toString()) + condition);
            // 返回结果
            JSONObject result = new JSONObject();
            result.put("data", data);
            renderJson(result);
        }

    }

    /**
     * 页面排行
     */
    @RequiresPermissions("/portal/siteAnalysis/pageRank")
    public void pageRank() {

        if (BaseUtils.isAjax(getRequest())) {
            // 返回结果
            JSONObject result = new JSONObject();
            List<List<String>> guestData = new ArrayList<>();

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            //获取日期类型，dateType：day,week,month,quarter,year
            String dateType = getPara("dateType");

            if (dateType != null) {
                if (dateType.equals("day")) {
                    // 执行查询
                    guestData = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_pageRank_day, condition));
                }

                if (dateType.equals("month")) {
                    // 执行查询
                    guestData = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_pageRank_month, condition));
                }
            }

            result.put("guestData", guestData);
            renderJson(result);
        }
    }

    /**
     * 流量趋势
     */
    @RequiresPermissions("/portal/siteAnalysis/flowTrend")
    public void flowTrend() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> data = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_flowTrend.toString()) + condition, 24);

            List<List<String>> charData = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_flowTrend_chart.toString(), condition));
            // 返回结果
            JSONObject result = new JSONObject();
            List<Object> xAxisList = new ArrayList<Object>();
            List<Object> pvList = new ArrayList<Object>();
            List<Object> uvList = new ArrayList<Object>();
            List<Object> ipList = new ArrayList<Object>();
            List<Object> loginCntList = new ArrayList<Object>();
            for (List<String> list : charData) {
                xAxisList.add(list.get(0));
                pvList.add(list.get(1));
                uvList.add(list.get(2));
                ipList.add(list.get(3));
                loginCntList.add(list.get(4));
            }
            Object[] nameList = new Object[]{res.get("portal.pageViewPV"), res.get("portal.visitorUV"), res.get("portal.IPCount"), res.get("portal.loginUser")};
            //获取折线图数据
            String flowTrendChart = ChartUtils.genMultiLineChart(xAxisList, nameList, pvList, uvList, ipList, loginCntList);
            result.put("chartOption", flowTrendChart);
            result.put("data", data);
            renderJson(result);
        }

    }

    /**
     * 广告趋势
     */
    @RequiresPermissions("/portal/siteAnalysis/adTrend")
    public void adTrend() {
        if (BaseUtils.isAjax(getRequest())) {
            // 返回结果
            JSONObject result = new JSONObject();
            List<List<String>> adData = new ArrayList<>();
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            //获取日期类型，dateType：day,week,month,quarter,year
            String dateType = getPara("dateType");

            if (dateType != null) {
                if (dateType.equals("day")) {
                    // 执行查询
                    adData = InceptorUtil
                            .query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_adTrend_query_day.toString(), condition));
                }

                if (dateType.equals("month")) {
                    // 执行查询
                    adData = InceptorUtil
                            .query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_adTrend_query_month.toString(), condition));
                }
            }

            result.put("adData", adData);
            renderJson(result);
        }

    }

    /**
     * 实时数据
     */
    @RequiresPermissions("/portal/siteAnalysis/real")
    public void real() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> data = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_real.toString(), condition), 24);
            // 返回结果
            JSONObject result = new JSONObject();
            result.put("data", data);
            renderJson(result);
        }
    }

}
