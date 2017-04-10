package io.transwarp.scrcu.service.app;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.ChartType;
import io.transwarp.scrcu.base.util.GenerateAppChartsUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

import java.util.ArrayList;
import java.util.List;

import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.genChannelCharts;
import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.genOsCharts;
import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.genUserTimeCharts;

/**
 * App用户统计逻辑层
 *
 * @author hang_xiao
 * @date 2017/4/10
 */
public class AppUserService {

    /**
     * 国际化配置
     */
    private static Res res = I18n.use("i18n", "zh_CN");

    /**
     * APP活跃用户
     *
     * @param dateType 日期类型:day,week,month,quarter,year
     * @param condition 查询条件
     * @return activeUser json
     */
    public static JSONObject activeUser(String dateType, String condition) {

        List<List<String>> dataTime = new ArrayList<>();
        List<List<String>> dataPhone = new ArrayList<>();
        List<List<String>> dataChannel = new ArrayList<>();
        List<List<String>> dataChartByChl = new ArrayList<>();
        // 定义json类型结果
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_activeUser_day, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_phone_day, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_channel_day, condition));
                dataChartByChl = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_day_chart, condition));
            }
            if (dateType.equals("week")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_activeUser_week, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_phone_week, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_channel_week, condition));
                dataChartByChl = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_week_chart, condition));
            }
            if (dateType.equals("month")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_activeUser_month, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_phone_month, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_channel_month, condition));
                dataChartByChl = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_month_chart, condition));
            }
            if (dateType.equals("quarter")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_activeUser_quarter, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_phone_quarter, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_channel_quarter, condition));
                dataChartByChl = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_quarter_chart, condition));
            }
            if (dateType.equals("year")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_activeUser_year, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_phone_year, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_channel_year, condition));
                dataChartByChl = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_year_chart, condition));
            }
        }
        //定义图例名称
        Object[] nameList = new Object[]{res.get("app.newAddUser")};

        //返回根据日期生成折线图的图表数据
        result.put("timeCharts", genUserTimeCharts(dateType, ChartType.LINE, dataTime, nameList));
        //返回根据手机OS生成折线图的图表数据
        result.put("osCharts", GenerateAppChartsUtils.genOsCharts(dateType, ChartType.LINE, dataPhone));
        //返回根据渠道生成折线图的图表数据
        result.put("chlCharts", GenerateAppChartsUtils.genChannelCharts(dateType, dataChartByChl, res.get("app.startUser")));

        result.put("dataTime", dataTime);
        result.put("dataPhone", dataPhone);
        result.put("dataChannel", dataChannel);
        return result;
    }

    /**
     * APP留存用户
     *
     * @param condition 查询条件
     * @return retainUser json
     */
    public static JSONObject retainUser(String condition) {

        // 定义json类型结果
        JSONObject result = new JSONObject();
        //定义集合并获取对应数据
        List<List<String>> dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_retainUser_day, condition), false);
        List<List<String>> dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_retainUser_phone_day, condition));
        List<List<String>> dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_retainUser_channel_day, condition));
        List<List<String>> dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_retainUser_channel_day_chart, condition));

        //生成留存用户按照日期分布的折线图
        Object[] nameList = new Object[]{res.get("app.newAddUser")};
        result.put("timeCharts", genUserTimeCharts("day", ChartType.LINE, dataTime, nameList));
        //生成留存用户按照手机OS分布的折线图
        result.put("osCharts", genOsCharts("day", ChartType.LINE, dataPhone));
        //生成留存用户按照渠道分布的折线图
        result.put("chlCharts", genChannelCharts("day", dataChart, res.get("app.newAddUser")));

        result.put("dataTime", dataTime);
        result.put("dataPhone", dataPhone);
        result.put("dataChannel", dataChannel);

        return result;
    }
    /**
     * APP注册用户
     *
     * @param dateType 日期类型:day,week,month,quarter,year
     * @param condition 查询条件
     * @return registerUser json
     */

    public static JSONObject registerUser(String dateType, String condition) {

        List<List<String>> dataPhone = new ArrayList<>();
        List<List<String>> dataChannel = new ArrayList<>();
        List<List<String>> dataChart = new ArrayList<>();
        // 定义json类型结果
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                dataChannel = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_regUser_channel_day, condition), false);
                dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_regUser_channel_day_chart, condition));
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_regUser_os_day, condition));
            }
            if (dateType.equals("month")) {
                dataChannel = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_regUser_channel_month, condition), false);
                dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_regUser_channel_month_chart, condition));
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_regUser_os_month, condition));
            }
            if (dateType.equals("quarter")) {
                dataChannel = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_regUser_channel_quarter, condition), false);
                dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_regUser_channel_quarter_chart, condition));
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_regUser_os_quarter, condition));
            }
            if (dateType.equals("year")) {
                dataChannel = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_regUser_channel_year, condition), false);
                dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_regUser_channel_year_chart, condition));
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_regUser_os_year, condition));
            }
        }

        //返回根据手机os生成折线图的图表数据
        result.put("osCharts", GenerateAppChartsUtils.genOsCharts(dateType, ChartType.LINE, dataPhone));

        result.put("chlCharts", GenerateAppChartsUtils.genChannelCharts(dateType, dataChart, res.get("app.newAddUser")));

        result.put("dataPhone", dataPhone);
        result.put("dataChannel", dataChannel);

        return result;
    }

    /**
     * APP登陆用户
     *
     * @param dateType 日期类型:day,week,month,quarter,year
     * @param condition 查询条件
     * @return loginUser json
     */
    public static JSONObject loginUser(String dateType, String condition) {

        List<List<String>> dataTime = new ArrayList<>();
        List<List<String>> dataPhone = new ArrayList<>();
        List<List<String>> dataChannel = new ArrayList<>();
        List<List<String>> dataChart = new ArrayList<>();
        // 定义json类型结果
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_loginUser_day, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_os_day, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_channel_day, condition));
                dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_channel_day_chart, condition));
            }
            if (dateType.equals("month")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_loginUser_month, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_os_month, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_channel_month, condition));
                dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_channel_month_chart, condition));
            }
            if (dateType.equals("quarter")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_loginUser_quarter, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_os_quarter, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_channel_quarter, condition));
                dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_channel_quarter_chart, condition));
            }
            if (dateType.equals("year")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_loginUser_year, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_os_year, condition));
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_channel_year, condition));
                dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_channel_year_chart, condition));
            }
        }

        result.put("timeCharts", GenerateAppChartsUtils.genLoginUserTimeCharts(dateType, ChartType.LINE, dataTime));

        result.put("chlCharts", GenerateAppChartsUtils.genChannelCharts(dateType, dataChart, res.get("app.loginUser")));

        result.put("osCharts", GenerateAppChartsUtils.genOsCharts(dateType, ChartType.LINE, dataPhone));

        result.put("dataTime", dataTime);
        result.put("dataPhone", dataPhone);
        result.put("dataChannel", dataChannel);

        return result;
    }

    /**
     * APP新增用户
     *
     * @param dateType 日期类型:day,week,month,quarter,year
     * @param condition 查询条件
     * @return newUser json
     */
    public static JSONObject newUser(String dateType, String condition) {

        List<List<String>> dataTime = new ArrayList<>();
        List<List<String>> dataPhone = new ArrayList<>();
        // 定义json类型结果
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_newUser_day, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_newUser_phone_day, condition));
            }
            if (dateType.equals("week")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_newUser_week, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_newUser_phone_week, condition));
            }
            if (dateType.equals("month")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_newUser_month, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_newUser_phone_month, condition));
            }
            if (dateType.equals("quarter")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_newUser_quarter, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_newUser_phone_quarter, condition));
            }
            if (dateType.equals("year")) {
                dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_newUser_year, condition), false);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_newUser_phone_year, condition));
            }
        }

        //定义折线图图例的名称
        Object[] nameList = new Object[]{res.get("app.newAddUser")};
        result.put("timeCharts", genUserTimeCharts(dateType, ChartType.LINE, dataTime, nameList));
        result.put("osCharts", GenerateAppChartsUtils.genOsCharts(dateType, ChartType.LINE, dataPhone));

        result.put("dataTime", dataTime);
        result.put("dataPhone", dataPhone);

        return result;
    }
}
