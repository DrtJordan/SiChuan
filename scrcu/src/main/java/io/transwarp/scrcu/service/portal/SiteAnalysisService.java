package io.transwarp.scrcu.service.portal;

import com.alibaba.fastjson.JSONObject;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.ChartType;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

import java.util.ArrayList;
import java.util.List;

import static io.transwarp.scrcu.base.util.GeneratePortalChartsUtils.*;

/**
 * @author hang_xiao
 * @date 2017/4/10
 */
public class SiteAnalysisService {

    /**
     * 访问分析
     *
     * @param dateType  日期类型:day,month,quarter,year
     * @param condition 查询条件
     * @return visit json
     */
    public static JSONObject visitAnalysis(String dateType, String condition) {
        // 返回结果
        JSONObject result = new JSONObject();
        //页面时长
        List<List<String>> timeData = new ArrayList<>();
        //页面深度
        List<List<String>> depthData = new ArrayList<>();

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

        //生成访问分析中访问时长的折线图图表数据
        result.put("useTimeChart", genUseTimeCharts(dateType, ChartType.LINE, timeData));
        //生成访问分析中访问深度的折线图图表数据
        result.put("useDepthChart", genVisitDepthCharts(dateType, ChartType.LINE, depthData));

        result.put("timeData", timeData);
        result.put("depthData", depthData);
        return result;
    }

    /**
     * 页面排行
     *
     * @param dateType  日期类型:day,month,
     * @param condition 查询条件
     * @return page rank json
     */
    public static JSONObject pageRank(String dateType, String condition) {

        // 返回结果
        JSONObject result = new JSONObject();
        List<List<String>> guestData = new ArrayList<>();

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
        return result;
    }

    /**
     * 流量趋势
     *
     * @param dateType  日期类型:day,week,month,quarter,year
     * @param condition 查询条件
     * @return flowTrend json
     */
    public static JSONObject flowTrend(String dateType, String condition) {

        List<List<String>> dataFlowTrend = new ArrayList<>();
        List<List<String>> dataNewVisitor = new ArrayList<>();

        if (dateType != null) {
            if (dateType.equals("day")) {
                dataFlowTrend = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_flowTrend_day, condition), false);
                dataNewVisitor = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_flowTrend_newVisitor_day, condition));
            }
            if (dateType.equals("week")) {
                dataFlowTrend = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_flowTrend_week, condition), false);
                dataNewVisitor = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_flowTrend_newVisitor_week, condition));
            }
            if (dateType.equals("month")) {
                dataFlowTrend = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_flowTrend_month, condition), false);
                dataNewVisitor = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_flowTrend_newVisitor_month, condition));
            }
            if (dateType.equals("quarter")) {
                dataFlowTrend = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_flowTrend_quarter, condition), false);
                dataNewVisitor = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_flowTrend_newVisitor_quarter, condition));
            }
            if (dateType.equals("year")) {
                dataFlowTrend = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_flowTrend_year, condition), false);
                dataNewVisitor = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_flowTrend_newVisitor_year, condition));
            }
        }

        // 返回结果
        JSONObject result = new JSONObject();

        //生成流量趋势的图表数据
        result.put("flowTrendCharts", genFlowTrendCharts(dateType, ChartType.LINE, dataFlowTrend));
        //生成新访客的图表数据
        result.put("newVisitorCharts", genNewVisitorCharts(dateType, ChartType.LINE, dataNewVisitor));

        result.put("dataFlowTrend", dataFlowTrend);
        result.put("dataNewVisitor", dataNewVisitor);

        return result;
    }

    /**
     * @param dateType  日期类型:day,month
     * @param condition 查询条件
     * @return adTrend json
     */
    public static JSONObject adTrend(String dateType, String condition) {

        // 返回结果
        JSONObject result = new JSONObject();
        List<List<String>> adData = new ArrayList<>();

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

        return result;
    }
}
