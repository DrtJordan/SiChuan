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

/**
 * @author hang_xiao
 * @date 2017/4/10
 */
public class AppService {

    private static Res res = I18n.use("i18n", "zh_CN");

    /**
     * App启动次数
     *
     * @param dateType  日期类型
     * @param condition 查询条件
     * @return app startCount json
     */
    public static JSONObject startCount(String dateType, String condition) {

        List<List<String>> dataPhone = new ArrayList<>();
        List<List<String>> dataChannel = new ArrayList<>();
        List<List<String>> dataChart = new ArrayList<>();
        // 定义json类型结果
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                dataPhone = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_startCount_phone_day, condition), false);
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_startCount_channel_day, condition));
                dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_startCount_channel_day_chart, condition));
            }
            if (dateType.equals("month")) {
                dataPhone = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_startCount_phone_month, condition), false);
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_startCount_channel_month, condition));
                dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_startCount_channel_month_chart, condition));
            }
            if (dateType.equals("quarter")) {
                dataPhone = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_startCount_phone_quarter, condition), false);
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_startCount_channel_quarter, condition));
                dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_startCount_channel_quarter_chart, condition));
            }
            if (dateType.equals("year")) {
                dataPhone = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_startCount_phone_year, condition), false);
                dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_startCount_channel_year, condition));
                dataChart = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_startCount_channel_year_chart, condition));
            }
        }

        //生成按手机OS分布的折线图图表数据
        result.put("osCharts", GenerateAppChartsUtils.genOsCharts(dateType, ChartType.LINE, dataPhone));

        //生成按渠道分布的折线图图表数据
        result.put("chlCharts", GenerateAppChartsUtils.genChannelCharts(dateType, dataChart, res.get("app.startTimes")));

        result.put("dataPhone", dataPhone);
        result.put("dataChannel", dataChannel);

        return result;
    }

    /**
     * App版本
     *
     * @param dateType  日期类型
     * @param condition 查询条件
     * @return app appVersion json
     */
    public static JSONObject appVersion(String dateType, String condition) {

        List<List<String>> data = new ArrayList<>();
        List<List<String>> dataCharts = new ArrayList<>();
        // 定义json类型结果
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                dataCharts = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_day_chart, condition), false);
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_day, condition), false);
            }
            if (dateType.equals("month")) {
                dataCharts = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_month_chart, condition), false);
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_month, condition), false);
            }
            if (dateType.equals("quarter")) {
                dataCharts = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_quarter_chart, condition), false);
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_quarter, condition), false);
            }
            if (dateType.equals("year")) {
                dataCharts = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_year_chart, condition), false);
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_year, condition), false);
            }
        }

        //生成版本的数据
        String genAppVersion = GenerateAppChartsUtils.genAppVersionCharts(dateType, ChartType.LINE, dataCharts);
        result.put("appVersionCharts", genAppVersion);

        //返回结果
        result.put("data", data);

        return result;
    }
}
