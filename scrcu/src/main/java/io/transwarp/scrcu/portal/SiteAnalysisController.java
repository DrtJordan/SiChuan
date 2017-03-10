package io.transwarp.scrcu.portal;

import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import static io.transwarp.scrcu.base.util.GeneratePortalChartsUtils.*;

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

            //生成访问分析中访问时长的折线图图表数据
            result.put("useTimeChart", genUseTimeCharts(dateType, timeData));
            //生成访问分析中访问深度的折线图图表数据
            result.put("useDepthChart", genVisitDepthCharts(dateType, depthData));

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
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/portal/siteAnalysis/flowTrend")
    public void flowTrend() {
        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> dataFlowTrend = new ArrayList<>();
            List<List<String>> dataNewVisitor = new ArrayList<>();

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

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
            result.put("flowTrendCharts", genFlowTrendCharts(dateType, dataFlowTrend));
            //生成新访客的图表数据
            result.put("newVisitorCharts", genNewVisitorCharts(dateType, dataNewVisitor));

            result.put("dataFlowTrend", dataFlowTrend);
            result.put("dataNewVisitor", dataNewVisitor);
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
