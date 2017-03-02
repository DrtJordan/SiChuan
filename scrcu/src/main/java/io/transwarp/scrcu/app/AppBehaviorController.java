package io.transwarp.scrcu.app;

import java.util.*;

import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import io.transwarp.echarts.data.Data;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.ChartUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

@RequiresAuthentication
public class AppBehaviorController extends Controller {

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
            // 返回结果
            List<Object> xAxisList = new ArrayList<>();
            //定义时长对应的集合用来接收对应的数据
            List<Object> fourToTenSecondsList = new ArrayList<Object>();
            List<Object> threeToTenMinList = new ArrayList<Object>();
            List<Object> thirtyMinList = new ArrayList<Object>();
            List<Object> elevenToThirtySecondsList = new ArrayList<Object>();
            List<Object> tenToThirtyMinList = new ArrayList<Object>();
            List<Object> oneToThreeMinList = new ArrayList<Object>();
            List<Object> thirtyFirstToSixtySecList = new ArrayList<Object>();
            List<Object> oneToThreeSecList = new ArrayList<Object>();
            Object[] nameList = new Object[]{"4-10秒", "3-10分", "30分以上", "11-30秒", "10-30分", "1-3分", "31-60秒", "1-3秒"};

            for (List<String> list : dataTime) {

                if (!xAxisList.contains(list.get(0))) {
                    xAxisList.add(list.get(0));
                }
                if (list.get(1).equals("4-10秒")) {
                    fourToTenSecondsList.add(list.get(2));
                }
                if (list.get(1).equals("3-10分")) {
                    threeToTenMinList.add(list.get(2));
                }
                if (list.get(1).equals("30分以上")) {
                    thirtyMinList.add(list.get(2));
                }
                if (list.get(1).equals("11-30秒")) {
                    elevenToThirtySecondsList.add(list.get(2));
                }
                if (list.get(1).equals("10-30分")) {
                    tenToThirtyMinList.add(list.get(2));
                }
                if (list.get(1).equals("1-3分")) {
                    oneToThreeMinList.add(list.get(2));
                }
                if (list.get(1).equals("31-60秒")) {
                    thirtyFirstToSixtySecList.add(list.get(2));
                }
                if (list.get(1).equals("1-3秒")) {
                    oneToThreeSecList.add(list.get(2));
                }
            }
            String genBar = ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList, fourToTenSecondsList, threeToTenMinList,
                    thirtyMinList, elevenToThirtySecondsList, tenToThirtyMinList, oneToThreeMinList, thirtyFirstToSixtySecList, oneToThreeSecList);
            result.put("chartOption", genBar);
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

            // 返回结果
            List<Object> xAxisList = new ArrayList<Object>();
            //定义时长对应的集合用来接收对应的数据
            List<Object> oneToTwqTimes = new ArrayList<Object>();
            List<Object> threeToFiveTimes = new ArrayList<Object>();
            List<Object> sixToNineTimes = new ArrayList<Object>();
            List<Object> tenToNineteenTimes = new ArrayList<Object>();
            List<Object> twentyToFourtyNineTimes = new ArrayList<Object>();
            List<Object> fiftyOverTimes = new ArrayList<Object>();
            //定义图例的name名称
            Object[] nameList = new Object[]{"1-2次", "3-5次", "6-9次", "10-19次", "20-49次", "50次及以上"};

            for (List<String> list : dataTime) {
                if (!xAxisList.contains(list.get(0))) {
                    xAxisList.add(list.get(0));
                }
                if (list.get(1).equals("1-2次")) {
                    oneToTwqTimes.add(list.get(2));
                }
                if (list.get(1).equals("3-5次")) {
                    threeToFiveTimes.add(list.get(2));
                }
                if (list.get(1).equals("6-9次")) {
                    sixToNineTimes.add(list.get(2));
                }
                if (list.get(1).equals("10-19次")) {
                    tenToNineteenTimes.add(list.get(2));
                }
                if (list.get(1).equals("20-49次")) {
                    twentyToFourtyNineTimes.add(list.get(2));
                }
                if (list.get(1).equals("50次及以上")) {
                    fiftyOverTimes.add(list.get(2));
                }
            }
            //生成使用频率的折线图数据
            String useRateChart = ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList,
                    oneToTwqTimes, threeToFiveTimes, sixToNineTimes, tenToNineteenTimes, twentyToFourtyNineTimes, fiftyOverTimes);
            //生成柱状图数据
            result.put("chartOption", useRateChart);
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
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_day, condition),false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_day, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_week, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_week, condition));
                }
                if (dateType.equals("week")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_week, condition),false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_week, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_week, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_week, condition));
                }
                if (dateType.equals("month")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_month, condition),false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_month, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_month, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_month, condition));
                }
                if (dateType.equals("quarter")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_quarter, condition),false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_quarter, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_quarter, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_quarter, condition));
                }
                if (dateType.equals("year")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_depth_year, condition),false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone_year, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel_year, condition));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os_year, condition));
                }
            }
            // 返回结果
            List<Object> xAxisList = new ArrayList<>();
            //定义时长对应的集合用来接收对应的数据
            List<Object> OneToTwoPageList = new ArrayList<Object>();
            List<Object> ThreeToFivePageList = new ArrayList<Object>();
            List<Object> SixToNinePageList = new ArrayList<Object>();
            List<Object> TenToTwNinePageList = new ArrayList<Object>();
            List<Object> ThirtyToMorePageList = new ArrayList<Object>();
            List<Object> OtherPageList = new ArrayList<Object>();
            Object[] nameList = new Object[]{"1-2页面", "3-5页面", "6-9页面", "10-29页面", "30个页面及以上", "other"};

            for (List<String> list : dataTime) {

                if (!xAxisList.contains(list.get(0))) {
                    xAxisList.add(list.get(0));
                }
                if (list.get(1).equals("1-2页面")) {
                    OneToTwoPageList.add(list.get(2));
                }
                if (list.get(1).equals("3-5页面")) {
                    ThreeToFivePageList.add(list.get(2));
                }
                if (list.get(1).equals("6-9页面")) {
                    SixToNinePageList.add(list.get(2));
                }
                if (list.get(1).equals("10-29页面")) {
                    TenToTwNinePageList.add(list.get(2));
                }
                if (list.get(1).equals("30个页面及以上")) {
                    ThirtyToMorePageList.add(list.get(2));
                }
                if (list.get(1).equals("other")) {
                    OtherPageList.add(list.get(2));
                }
            }
            String genBar = ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList, OneToTwoPageList, ThreeToFivePageList,
                    SixToNinePageList, TenToTwNinePageList, ThirtyToMorePageList, OtherPageList);
            result.put("chartOption", genBar);
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

            // 返回结果
            List<Object> xAxisList = new ArrayList<Object>();
            //定义时长对应的集合用来接收对应的数据
            List<Object> firstList = new ArrayList<Object>();
            List<Object> zeroToTwentyFourHours = new ArrayList<Object>();
            List<Object> oneToTwoDays = new ArrayList<Object>();
            List<Object> twoToFourDays = new ArrayList<Object>();
            List<Object> fiveToSevenDay = new ArrayList<Object>();
            List<Object> eightToFourteenDays = new ArrayList<Object>();
            List<Object> fifteenToThirtyDays = new ArrayList<Object>();
            //定义图例的name名称
            Object[] nameList = new Object[]{"首次", "0-24h", "1-2天", "2-4天", "5-7天", "8-14天", "15-30天"};

            for (List<String> list : dataTime) {
                if (!xAxisList.contains(list.get(0))) {
                    xAxisList.add(list.get(0));
                }
                if (list.get(1).equals("首次")) {
                    firstList.add(list.get(2));
                }
                if (list.get(1).equals("0-24h")) {
                    zeroToTwentyFourHours.add(list.get(2));
                }
                if (list.get(1).equals("1-2天")) {
                    oneToTwoDays.add(list.get(2));
                }
                if (list.get(1).equals("2-4天")) {
                    twoToFourDays.add(list.get(2));
                }
                if (list.get(1).equals("5-7天")) {
                    fiveToSevenDay.add(list.get(2));
                }
                if (list.get(1).equals("8-14天")) {
                    eightToFourteenDays.add(list.get(2));
                }
                if (list.get(1).equals("15-30天")) {
                    fifteenToThirtyDays.add(list.get(2));
                }
            }

            String intervalBar = ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList, firstList, zeroToTwentyFourHours,
                    oneToTwoDays, twoToFourDays, fiveToSevenDay, eightToFourteenDays, fifteenToThirtyDays);
            result.put("chartOption", intervalBar);
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
                    dataArea = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_query_day, condition),false);
                    dataAreaPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_phone_day, condition));
                    dataAreaChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_chanel_day, condition));
                    dataAreaOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_os_day, condition));
                }
                if (dateType.equals("week")) {
                    dataArea = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_query_week, condition),false);
                    dataAreaPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_phone_week, condition));
                    dataAreaChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_chanel_week, condition));
                    dataAreaOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_os_week, condition));
                }
                if (dateType.equals("month")) {
                    dataArea = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_query_month, condition),false);
                    dataAreaPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_phone_month, condition));
                    dataAreaChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_chanel_month, condition));
                    dataAreaOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_os_month, condition));
                }
                if (dateType.equals("quarter")) {
                    dataArea = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_query_quarter, condition),false);
                    dataAreaPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_phone_quarter, condition));
                    dataAreaChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_chanel_quarter, condition));
                    dataAreaOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_os_quarter, condition));
                }
                if (dateType.equals("year")) {
                    dataArea = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_area_query_year, condition),false);
                    dataAreaPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_phone_year, condition));
                    dataAreaChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_chanel_year, condition));
                    dataAreaOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_os_year, condition));
                }
            }
            //查询新增用户数的最大值
            List<List<String>> MaxUvValue = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_max_uv));
            // 返回结果
            List<Object> dataList = new ArrayList<Object>();
            for (List<String> list : dataArea) {
                String name = list.get(0).replace("甘孜州", "甘孜藏族自治州").replace("阿坝州", "阿坝藏族羌族自治州").replace("凉山州", "凉山彝族自治州");
                //获取新增用户数
                Data newUserCount = new Data(name, list.get(2));
                dataList.add(newUserCount);
            }
            //生成手机移动地域分布数据图
            String areaMap = ChartUtils.genMapChart(res.get("app.newAddUser"), dataList, MaxUvValue);
            result.put("chartOption", areaMap);
            result.put("data", dataArea);
            result.put("dataAreaChannel", dataAreaChannel);
            result.put("dataAreaPhone", dataAreaPhone);
            result.put("dataAreaOs", dataAreaOs);
            renderJson(result);
        }
    }

}
