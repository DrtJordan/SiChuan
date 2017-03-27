package io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.echarts.data.Data;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.ChartUtils;
import io.transwarp.scrcu.base.util.GenerateAppChartsUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.ArrayList;
import java.util.List;

import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.*;

@RequiresAuthentication
public class AppBehaviorController extends BaseController {

    Res res = I18n.use("i18n", "zh_CN");

    /**
     * 获取使用时长
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/behavior/useTime")
    public void useTime() {
        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> dataTime = new ArrayList<>();
            List<List<String>> dataPhone = new ArrayList<>();
            List<List<String>> dataChannel = new ArrayList<>();
            List<List<String>> dataOs = new ArrayList<>();
            JSONObject result = new JSONObject();

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

            if (dateType != null) {
                if (dateType.equals("day")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useTime_day, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_day, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_day, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_day, condition));
                }
                if (dateType.equals("week")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useTime_week, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_week, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_week, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_week, condition));
                }
                if (dateType.equals("month")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useTime_month, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_month, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_month, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_month, condition));
                }
                if (dateType.equals("quarter")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useTime_quarter, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_quarter, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_quarter, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_quarter, condition));
                }
                if (dateType.equals("year")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useTime_year, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_year, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_year, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_year, condition));
                }
            }

            //生成app使用时长的折线图
            result.put("chartOption", GenerateAppChartsUtils.genAppUseTimeCharts(dateType, dataTime));

            result.put("dataTime", dataTime);
            result.put("dataPhone", dataPhone);
            result.put("dataChannel", dataChannel);
            result.put("dataOs", dataOs);
            renderJson(result);
        }

    }

    /**
     * 获取使用频率
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/behavior/useRate")
    public void useRate() {
        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> dataTime = new ArrayList<>();
            List<List<String>> dataPhone = new ArrayList<>();
            List<List<String>> dataChannel = new ArrayList<>();
            List<List<String>> dataOs = new ArrayList<>();
            JSONObject result = new JSONObject();

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

            if (dateType != null) {
                if (dateType.equals("day")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useRate_day, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_day, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_day, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_day, condition));
                }
                if (dateType.equals("week")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useRate_week, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_week, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_week, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_week, condition));
                }
                if (dateType.equals("month")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useRate_month, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_month, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_month, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_month, condition));
                }
                if (dateType.equals("quarter")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useRate_quarter, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_quarter, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_quarter, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_quarter, condition));
                }
                if (dateType.equals("year")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useRate_year, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_year, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_year, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_year, condition));
                }
            }

            //生成使用频率折线图数据
            result.put("chartOption", genAppUseRateCharts(dateType, dataTime));

            result.put("dataTime", dataTime);
            result.put("dataPhone", dataPhone);
            result.put("dataChannel", dataChannel);
            result.put("dataOs", dataOs);
            renderJson(result);
        }

    }

    /**
     * 获取访问深度
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/behavior/depth")
    public void depth() {
        if (BaseUtils.isAjax(getRequest())) {
            List<List<String>> dataTime = new ArrayList<>();
            List<List<String>> dataPhone = new ArrayList<>();
            List<List<String>> dataChannel = new ArrayList<>();
            List<List<String>> dataOs = new ArrayList<>();
            JSONObject result = new JSONObject();
            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());
            if (dateType != null) {
                if (dateType.equals("day")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_day, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_day, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_day, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_day, condition));
                }
                if (dateType.equals("week")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_week, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_week, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_week, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_week, condition));
                }
                if (dateType.equals("month")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_month, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_month, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_month, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_month, condition));
                }
                if (dateType.equals("quarter")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_quarter, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_quarter, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_quarter, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_quarter, condition));
                }
                if (dateType.equals("year")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_year, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_year, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_year, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_year, condition));
                }
            }

            //app访问深度的折线图数据
            result.put("chartOption", genAppDepthCharts(dateType, dataTime));

            result.put("dataTime", dataTime);
            result.put("dataPhone", dataPhone);
            result.put("dataChannel", dataChannel);
            result.put("dataOs", dataOs);
            renderJson(result);
        }

    }

    /**
     * 获取访问间隔
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/behavior/interval")
    public void interval() {

        if (BaseUtils.isAjax(getRequest())) {
            List<List<String>> dataTime = new ArrayList<>();
            List<List<String>> dataPhone = new ArrayList<>();
            List<List<String>> dataChannel = new ArrayList<>();
            List<List<String>> dataOs = new ArrayList<>();
            JSONObject result = new JSONObject();

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

            if (dateType != null) {
                if (dateType.equals("day")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_interval_day, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_day, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_day, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_day, condition));
                }
                if (dateType.equals("week")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_interval_week, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_week, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_week, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_week, condition));
                }
                if (dateType.equals("month")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_interval_month, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_month, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_month, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_month, condition));
                }
                if (dateType.equals("quarter")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_interval_quarter, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_quarter, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_quarter, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_quarter, condition));
                }
                if (dateType.equals("year")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_interval_year, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_year, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_year, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_year, condition));
                }
            }

            //app访问间隔折线图数据
            result.put("chartOption", genAppIntervalCharts(dateType, dataTime));

            result.put("dataTime", dataTime);
            result.put("dataPhone", dataPhone);
            result.put("dataChannel", dataChannel);
            result.put("dataOs", dataOs);
            renderJson(result);
        }

    }

    /**
     * 获取地域分布
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/behavior/area")
    public void area() {
        if (BaseUtils.isAjax(getRequest())) {
            List<List<String>> dataCharts = new ArrayList<>();
            List<List<String>> dataArea = new ArrayList<>();
            List<List<String>> dataAreaPhone = new ArrayList<>();
            List<List<String>> dataAreaChannel = new ArrayList<>();
            List<List<String>> dataAreaOs = new ArrayList<>();
            JSONObject result = new JSONObject();
            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());
            if (dateType != null) {
                if (dateType.equals("day")) {
                    dataCharts = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_day_chart, condition), false);
                    dataArea = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_query_day, condition), false);
                    dataAreaPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_phone_day, condition));
                    dataAreaChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_chanel_day, condition));
                    dataAreaOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_os_day, condition));
                }
                if (dateType.equals("week")) {
                    dataCharts = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_week_chart, condition), false);
                    dataArea = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_query_week, condition), false);
                    dataAreaPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_phone_week, condition));
                    dataAreaChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_chanel_week, condition));
                    dataAreaOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_os_week, condition));
                }
                if (dateType.equals("month")) {
                    dataCharts = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_month_chart, condition), false);
                    dataArea = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_query_month, condition), false);
                    dataAreaPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_phone_month, condition));
                    dataAreaChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_chanel_month, condition));
                    dataAreaOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_os_month, condition));
                }
                if (dateType.equals("quarter")) {
                    dataCharts = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_quarter_chart, condition), false);
                    dataArea = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_query_quarter, condition), false);
                    dataAreaPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_phone_quarter, condition));
                    dataAreaChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_chanel_quarter, condition));
                    dataAreaOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_os_quarter, condition));
                }
                if (dateType.equals("year")) {
                    dataCharts = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_year_chart, condition), false);
                    dataArea = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_query_year, condition), false);
                    dataAreaPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_phone_year, condition));
                    dataAreaChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_chanel_year, condition));
                    dataAreaOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_os_year, condition));
                }
            }

            // 返回结果
            List<Object> dataList = new ArrayList<Object>();
            for (List<String> list : dataCharts) {

                String name = list.get(0).replace("甘孜州", "甘孜藏族自治州").replace("阿坝州", "阿坝藏族羌族自治州").replace("凉山州", "凉山彝族自治州");
                    //获取新增用户数
                    Data newUserCount = new Data(name, list.get(1));
                    dataList.add(newUserCount);
            }

            Integer maxValue = 0;
            if (dataCharts.size() != 0) {
                maxValue = Integer.valueOf(dataCharts.get(0).get(1));
            }
            //生成手机移动地域分布数据图
            String areaMap = ChartUtils.genMapChart(res.get("app.newAddUser"), dataList, maxValue);
            result.put("chartOption", areaMap);
            result.put("data", dataArea);
            result.put("dataAreaChannel", dataAreaChannel);
            result.put("dataAreaPhone", dataAreaPhone);
            result.put("dataAreaOs", dataAreaOs);
            renderJson(result);
        }
    }

}
