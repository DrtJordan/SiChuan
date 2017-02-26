package io.transwarp.scrcu.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @RequiresPermissions("/app/behavior/useTime")
    public void useTime() {
        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> dataTime = new ArrayList<>();
            List<List<String>> dataPhone = new ArrayList<>();
            List<List<String>> dataChannel = new ArrayList<>();
            List<List<String>> dataOs = new ArrayList<>();
            JSONObject result = new JSONObject();

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("type");
            if (dateType != null){
                if (dateType.equals("day")) {
                    dataTime = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_day));
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_day));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_day));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_day));
                }
                if (dateType.equals("week")) {
                    dataTime = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_week));
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_week));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_week));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_week));
                }
                if (dateType.equals("month")) {
                    dataTime = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_month));
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_month));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_month));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_month));
                }
                if (dateType.equals("quarter")) {
                    dataTime = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_quarter));
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_quarter));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_quarter));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_quarter));
                }
                if (dateType.equals("year")){
                    dataTime = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_year));
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone_year));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_channel_year));
                    dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_os_year));
                }
            } else {
                // 执行查询
                dataTime = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime, condition), 35);
                dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useTime_phone, condition),
                        35);
                dataChannel = InceptorUtil
                        .query(SqlKit.propSQL(SQLConfig.app_useTime_channel, condition), 35);
                dataOs = InceptorUtil
                        .query(SqlKit.propSQL(SQLConfig.app_useTime_os, condition), 35);
            }
            // 返回结果
            Set<Object> xAxisList = new HashSet<>();
            List<Object> dataList = new ArrayList<Object>();
//            Object[] nameList = new Object[]{"4-10秒","3-10分","30分以上","other","11-30秒","10-30分","1-3分","31-60秒","1-3秒"};
            Object[] nameList = new Object[9];
            Set<Object> names = new HashSet<>();
            int i = 0;
            for (List<String> list : dataTime) {
                xAxisList.add(list.get(0));
                nameList[i] = list.get(1);
//                names.add(list.get(1));
                dataList.add(list.get(2));
                i++;
            }
            String genBar = ChartUtils.genUseTimeLineChart(xAxisList, nameList, dataList);
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
    @RequiresPermissions("/app/behavior/useRate")
    public void useRate() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest()) + " and statt_prd_cd = 1 ";
            // 执行查询
            List<List<String>> dataTime = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate, condition), 35);
            List<List<String>> dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_useRate_phone, condition),
                    35);
            List<List<String>> dataChannel = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.app_useRate_channel, condition), 35);
            List<List<String>> dataOs = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.app_useRate_os, condition), 35);

            JSONObject result = new JSONObject();
            // 返回结果
            List<Object> xAxisList = new ArrayList<Object>();
            List<Object> dataList1 = new ArrayList<Object>();
//            List<Object> dataList2 = new ArrayList<Object>();
            for (List<String> list : dataTime) {
                xAxisList.add(list.get(0));
                dataList1.add(list.get(1));
//                dataList2.add(list.get(2));
            }
//            Object[] nameList = new Object[]{res.get("app.startTimes"), res.get("app.userCount")};
//            String str = ChartUtils.genMultiLineChart(xAxisList, nameList,
//                    dataList1, dataList2);
            //生成柱状图数据
            String str = ChartUtils.genBar(res.get("app.startTimes"), res.get("app.userCount"), xAxisList, dataList1);
            result.put("chartOption", str);
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
    @RequiresPermissions("/app/behavior/depth")
    public void depth() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> dataTime = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth, condition), 35);
            List<List<String>> dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_phone, condition),
                    35);
            List<List<String>> dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_channel, condition),
                    35);
            List<List<String>> dataOs = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_depth_os, condition),
                    35);

            JSONObject result = new JSONObject();
            // 返回结果
            List<Object> xAxisList = new ArrayList<Object>();
            List<Object> dataList = new ArrayList<Object>();
            for (List<String> list : dataTime) {
                xAxisList.add(list.get(0));
                dataList.add(list.get(1));
            }
            // String str = ChartUtils.genLineChart(res.get("app.startTimes"), xAxisList,
            // dataList);
            String genBar = ChartUtils.genBar(res.get("app.accessDepth"), res.get("app.startTimes"), xAxisList, dataList);
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
    @RequiresPermissions("/app/behavior/interval")
    public void interval() {

        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> dataTime = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval, condition), 35);
            List<List<String>> dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_interval_phone, condition),
                    35);
            List<List<String>> dataChannel = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.app_interval_channel, condition), 35);
            List<List<String>> dataOs = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.app_interval_os, condition), 35);

            JSONObject result = new JSONObject();
            // 返回结果
            List<Object> xAxisList = new ArrayList<Object>();
            List<Object> dataList1 = new ArrayList<Object>();
//            List<Object> dataList2 = new ArrayList<Object>();
            for (List<String> list : dataTime) {
                xAxisList.add(list.get(0));
                dataList1.add(list.get(1));
//                dataList2.add(list.get(2));
            }
            /*Object[] nameList = new Object[]{res.get("app.useInterval"), res.get("app.startTimes")};
            String str = ChartUtils.genMultiLineChart(xAxisList, nameList,
            dataList1, dataList2);*/
            String intervalBar = ChartUtils.genBar(res.get("app.useInterval"), res.get("app.startTimes"), xAxisList, dataList1);
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
    @RequiresPermissions("/app/behavior/area")
    public void area() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 根据日期查询
            List<List<String>> dataArea = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.app_area_query.toString(), condition));
            //根据渠道查询
            List<List<String>> dataAreaChannel = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.app_area_channel.toString(), condition));
            //根据版本查询
            List<List<String>> dataAreaPhone = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.app_area_phone.toString(), condition));
            //根据手机os查询
            List<List<String>> dataAreaOs = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.app_area_os.toString(), condition));
            //查询新增用户数的最大值
            List<List<String>> MaxUvValue = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_area_max_uv));
            // 返回结果
            JSONObject result = new JSONObject();
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
