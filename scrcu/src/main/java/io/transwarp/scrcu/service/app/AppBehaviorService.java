package io.transwarp.scrcu.service.app;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.echarts.data.Data;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.ChartType;
import io.transwarp.scrcu.base.util.ChartUtils;
import io.transwarp.scrcu.base.util.GenerateAppChartsUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

import java.util.ArrayList;
import java.util.List;

import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.genAppDepthCharts;
import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.genAppIntervalCharts;
import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.genAppUseRateCharts;

/**
 * App行为逻辑层
 *
 * @author hang_xiao
 * @date 2017/4/10
 */
public class AppBehaviorService {

    /**
     * 国际化配置
     */
    private static Res res = I18n.use("i18n", "zh_CN");

    /**
     * 获取APP使用时长
     *
     * @param dateType 日期类型:day,week,month,quarter,year
     * @param condition 查询条件
     * @return useTime json
     */
    public static JSONObject useTime(String dateType, String condition) {

        List<List<String>> dataTime = new ArrayList<>();
        List<List<String>> dataPhone = new ArrayList<>();
        List<List<String>> dataChannel = new ArrayList<>();
        List<List<String>> dataOs = new ArrayList<>();
        List<List<String>> dataPhoneChart = new ArrayList<>();
        List<List<String>> dataChlChart = new ArrayList<>();
        List<List<String>> dataOsChart = new ArrayList<>();
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useTime_day, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_day, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_day, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_day, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_day_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_day_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_day_chart, condition));
            }
            if (dateType.equals("week")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useTime_week, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_week, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_week, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_week, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_week_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_week_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_week_chart, condition));
            }
            if (dateType.equals("month")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useTime_month, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_month, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_month, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_month, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_month_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_month_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_month_chart, condition));
            }
            if (dateType.equals("quarter")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useTime_quarter, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_quarter, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_quarter, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_quarter, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_quarter_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_quarter_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_quarter_chart, condition));
            }
            if (dateType.equals("year")) {

                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useTime_year, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_year, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_year, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_year, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_year_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_year_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_year_chart, condition));
            }
        }

        //生成app使用时长的折线图
        result.put("timeChart", GenerateAppChartsUtils.genAppBehaviorCharts(dateType, ChartType.LINE, dataTime));
        result.put("phoneChart", GenerateAppChartsUtils.genAppBehaviorCharts(dateType, ChartType.LINE, dataPhoneChart));
        result.put("osChart", GenerateAppChartsUtils.genAppBehaviorCharts(dateType, ChartType.BAR, dataOsChart));
        result.put("chlChart", GenerateAppChartsUtils.genAppBehaviorCharts(dateType, ChartType.BAR, dataChlChart));

        result.put("dataTime", dataTime);
        result.put("dataPhone", dataPhone);
        result.put("dataChannel", dataChannel);
        result.put("dataOs", dataOs);

        return result;
    }

    /**
     * 获取APP使用频率
     *
     * @param dateType 日期类型:day,week,month,quarter,year
     * @param condition 查询条件
     * @return useRate json
     */
    public static JSONObject useRate(String dateType, String condition) {

        List<List<String>> dataTime = new ArrayList<>();
        List<List<String>> dataPhone = new ArrayList<>();
        List<List<String>> dataChannel = new ArrayList<>();
        List<List<String>> dataOs = new ArrayList<>();
        List<List<String>> dataPhoneChart = new ArrayList<>();
        List<List<String>> dataChlChart = new ArrayList<>();
        List<List<String>> dataOsChart = new ArrayList<>();

        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useRate_day, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_day, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_day, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_day, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_day_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_day_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_day_chart, condition));
            }
            if (dateType.equals("week")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useRate_week, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_week, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_week, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_week, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_week_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_week_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_week_chart, condition));
            }
            if (dateType.equals("month")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useRate_month, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_month, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_month, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_month, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_month_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_month_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_month_chart, condition));
            }
            if (dateType.equals("quarter")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useRate_quarter, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_quarter, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_quarter, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_quarter, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_quarter_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_quarter_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_quarter_chart, condition));
            }
            if (dateType.equals("year")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_useRate_year, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_year, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_year, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_year, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone_year_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_channel_year_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_os_year_chart, condition));
            }
        }

        //生成使用频率折线图数据
        result.put("timeChart", genAppUseRateCharts(dateType, ChartType.LINE, dataTime));
        result.put("phoneChart", genAppUseRateCharts(dateType, ChartType.LINE, dataPhoneChart));
        result.put("osChart", genAppUseRateCharts(dateType, ChartType.BAR, dataOsChart));
        result.put("chlChart", genAppUseRateCharts(dateType, ChartType.BAR, dataChlChart));

        result.put("dataTime", dataTime);
        result.put("dataPhone", dataPhone);
        result.put("dataChannel", dataChannel);
        result.put("dataOs", dataOs);

        return result;
    }

    /**
     * 获取APP访问深度
     *
     * @param dateType 日期类型:day,week,month,quarter,year
     * @param condition 查询条件
     * @return visitDepth json
     */
    public static JSONObject visitDepth(String dateType, String condition) {

        List<List<String>> dataTime = new ArrayList<>();
        List<List<String>> dataPhone = new ArrayList<>();
        List<List<String>> dataChannel = new ArrayList<>();
        List<List<String>> dataOs = new ArrayList<>();
        List<List<String>> dataPhoneChart = new ArrayList<>();
        List<List<String>> dataChlChart = new ArrayList<>();
        List<List<String>> dataOsChart = new ArrayList<>();
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_day, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_day, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_day, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_day, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_day_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_day_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_day_chart, condition));
            }
            if (dateType.equals("week")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_week, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_week, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_week, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_week, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_week_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_week_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_week_chart, condition));
            }
            if (dateType.equals("month")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_month, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_month, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_month, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_month, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_month_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_month_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_month_chart, condition));
            }
            if (dateType.equals("quarter")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_quarter, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_quarter, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_quarter, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_quarter, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_quarter_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_quarter_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_quarter_chart, condition));
            }
            if (dateType.equals("year")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_year, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_year, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_year, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_year, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_year_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_year_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_year_chart, condition));
            }
        }

        //app访问深度的折线图数据
        result.put("timeChart", genAppDepthCharts(dateType, ChartType.LINE, dataTime));
        result.put("phoneChart", genAppDepthCharts(dateType, ChartType.LINE, dataPhoneChart));
        result.put("osChart", genAppDepthCharts(dateType, ChartType.BAR, dataOsChart));
        result.put("chlChart", genAppDepthCharts(dateType, ChartType.BAR, dataChlChart));

        result.put("dataTime", dataTime);
        result.put("dataPhone", dataPhone);
        result.put("dataChannel", dataChannel);
        result.put("dataOs", dataOs);

        return result;
    }

    /**
     * 获取APP访问间隔
     *
     * @param dateType 日期类型:day,week,month,quarter,year
     * @param condition 查询条件
     * @return interval json
     */
    public static JSONObject interval(String dateType, String condition) {

        List<List<String>> dataTime = new ArrayList<>();
        List<List<String>> dataPhone = new ArrayList<>();
        List<List<String>> dataChannel = new ArrayList<>();
        List<List<String>> dataOs = new ArrayList<>();
        List<List<String>> dataPhoneChart = new ArrayList<>();
        List<List<String>> dataChlChart = new ArrayList<>();
        List<List<String>> dataOsChart = new ArrayList<>();
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_interval_day, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_day, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_day, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_day, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_day_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_day_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_day_chart, condition));
            }
            if (dateType.equals("week")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_interval_week, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_week, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_week, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_week, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_week_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_week_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_week_chart, condition));
            }
            if (dateType.equals("month")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_interval_month, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_month, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_month, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_month, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_month_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_month_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_month_chart, condition));
            }
            if (dateType.equals("quarter")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_interval_quarter, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_quarter, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_quarter, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_quarter, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_quarter_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_quarter_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_quarter_chart, condition));
            }
            if (dateType.equals("year")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_interval_year, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_year, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_year, condition));
                dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_year, condition));

                dataPhoneChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone_year_chart, condition));
                dataChlChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_channel_year_chart, condition));
                dataOsChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_os_year_chart, condition));
            }
        }

        //app访问间隔折线图数据
        result.put("timeChart", genAppIntervalCharts(dateType, ChartType.LINE, dataTime));
        result.put("phoneChart", genAppIntervalCharts(dateType, ChartType.LINE, dataPhoneChart));
        result.put("osChart", genAppIntervalCharts(dateType, ChartType.BAR, dataOsChart));
        result.put("chlChart", genAppIntervalCharts(dateType, ChartType.BAR, dataChlChart));

        result.put("dataTime", dataTime);
        result.put("dataPhone", dataPhone);
        result.put("dataChannel", dataChannel);
        result.put("dataOs", dataOs);

        return result;
    }

    /**
     * 获取APP访问地域
     *
     * @param dateType 日期类型:day,week,month,quarter,year
     * @param condition 查询条件
     * @return area json
     */
    public static JSONObject area(String dateType, String mapType, String condition) {

        List<List<String>> dataCharts = new ArrayList<>();
        List<List<String>> dataArea = new ArrayList<>();
        List<List<String>> dataAreaPhone = new ArrayList<>();
        List<List<String>> dataAreaChannel = new ArrayList<>();
        List<List<String>> dataAreaOs = new ArrayList<>();
        List<List<String>> cityNames = new ArrayList<>();
        JSONObject result = new JSONObject();

        if (mapType == null || mapType.equals("china")) {
            condition = condition + " city_outer = '1' and ";
            cityNames = InceptorUtil.query(SqlKit.propSQL(SQLConfig.province_name_all));
        } else {
            condition = condition + " city_outer = '0' and ";
            cityNames = InceptorUtil.query(SqlKit.propSQL(SQLConfig.city_name_all));
        }

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
        List<Object> dataList = new ArrayList<>();
        for (List<String> list : dataCharts) {
            //修改过长自治州名称，更换成简称。
            String name = list.get(0).replace(res.get("area.ganZiZhou"), res.get("area.ganZiZhouAll")).replace(res.get("area.aBaZhou"), res.get("area.aBaZhouAll")).replace(res.get("area.liangShanZhou"), res.get("area.liangShanZhou"));
            //获取新增用户数
            Data newUserCount = new Data(name, list.get(1));
            dataList.add(newUserCount);
        }

        Integer maxValue = 0;
        if (dataCharts.size() != 0) {
            maxValue = Integer.valueOf(dataCharts.get(0).get(1));
        }
        //生成手机移动地域分布数据图
        String areaMap = ChartUtils.genMapChart(res.get("app.newAddUser"), mapType, dataList, maxValue);
        result.put("chartOption", areaMap);
        result.put("data", dataArea);
        result.put("dataAreaChannel", dataAreaChannel);
        result.put("dataAreaPhone", dataAreaPhone);
        result.put("dataAreaOs", dataAreaOs);
        result.put("cityNames", cityNames);

        return result;
    }
}
