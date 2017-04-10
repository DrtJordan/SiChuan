package io.transwarp.scrcu.portal;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.echarts.data.Data;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.ChartType;
import io.transwarp.scrcu.base.util.ChartUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.ArrayList;
import java.util.List;

import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.genMemberRunOffCharts;

@RequiresAuthentication
public class UserAnalysisController extends BaseController {

    Res res = I18n.use("i18n", "zh_CN");

    @ActionKey("/portal/userAnalysis")
    public void index() {
    }

    /**
     * 地域分布
     */
    @RequiresPermissions("/portal/userAnalysis/area")
    public void area() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

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
            List<Object> dataList = new ArrayList<Object>();
            for (List<String> list : areaData) {
                //修改过长自治州名称，更换成简称。
                String name = list.get(0).replace(res.get("portrait.ganZiZhou"), res.get("portrait.ganZiZhouAll")).replace(res.get("portrait.aBaZhou"), res.get("portrait.aBaZhouAll")).replace(res.get("portrait.liangShanZhou"), res.get("portrait.liangShanZhou"));
                //获取访客数量
                Data uvValue = new Data(name, list.get(1));
                dataList.add(uvValue);
            }

            Integer maxValue = 0;
            if (maxUv.size() != 0) {
                maxValue = Integer.valueOf(maxUv.get(0).get(0));
            }
            //生成四川省地域分布图
            String areaMap = ChartUtils.genMapChart(res.get("portal.visitor"), dataList, maxValue);
            result.put("chartOption", areaMap);
            result.put("areaData", areaData);
            renderJson(result);

        }

    }

    /**
     * 终端统计
     */
    @RequiresPermissions("/portal/userAnalysis/terminal")
    public void terminal() {
        if (BaseUtils.isAjax(getRequest())) {
            //定义终端操作系统以及浏览器的集合用来接收数据
            List<List<String>> terminalOsData = new ArrayList<>();
            List<List<String>> terminalBrowserData = new ArrayList<>();
            List<List<String>> terminalOsChart = new ArrayList<>();
            List<List<String>> terminalBrowserChart = new ArrayList<>();

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

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
            List<Object> osChartList = new ArrayList<Object>();
            List<Object> browserChartList = new ArrayList<Object>();
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
            renderJson(result);
        }

    }

    /**
     * 用户登记 最新需求没有此功能
     */
    /*@RequiresPermissions("/portal/userAnalysis/userLevel")
    public void userLevel() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> data = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_userAnalysis_userLevel, condition), 5);

            List<Object> dataList = new ArrayList<Object>();
            for (List<String> list : data) {
                Data d = new Data(list.get(0), Integer.valueOf(list.get(1)));
                dataList.add(d);
            }
            // 返回结果
            JSONObject result = new JSONObject();
            result.put("data", data);
            String str = ChartUtils.genPie(res.get("portal.memberCount"), dataList);
            // 生成图
            result.put("chartOption", str);
            renderJson(result);
        }

    }*/

    /**
     * 会员页面分析
     */
    @RequiresPermissions("/portal/userAnalysis/userVisitPage")
    @Deprecated
    public void userVisitPage() {
        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> dataPage = new ArrayList<>();
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

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
            renderJson(result);
        }
    }

    /**
     * 流失用户分析
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/portal/userAnalysis/userLoss")
    public void userLoss() {
        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> dataLoss = new ArrayList<>();
            // 返回结果
            JSONObject result = new JSONObject();

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

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

            result.put("dataLoss", dataLoss);
            renderJson(result);
        }

    }

    /**
     * 唯一用户分析
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/portal/userAnalysis/userOnly")
    public void userOnly() {
        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> dataUserOnly = new ArrayList<>();
            // 返回结果
            JSONObject result = new JSONObject();

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

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
            Object[] nameList = new Object[]{res.get("portal.visitorUser"), res.get("portal.LoginUser"), res.get("portal.IP")};

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
            renderJson(result);
        }
    }

}
