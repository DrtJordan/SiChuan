package io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.base.util.GenerateAppChartsUtils;
import io.transwarp.scrcu.sqlinxml.SqlKit;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.ArrayList;
import java.util.List;

import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.genChannelCharts;
import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.genOsCharts;
import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.genUserTimeCharts;

@RequiresAuthentication
public class AppUserController extends BaseController {

    Res res = I18n.use("i18n", "zh_CN");

    /**
     * app活跃用户数据
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/userAnalysis/activeUser")
    public void activeUser() {

        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> dataTime = new ArrayList<>();
            List<List<String>> dataPhone = new ArrayList<>();
            List<List<String>> dataChannel = new ArrayList<>();
            // 定义json类型结果
            JSONObject result = new JSONObject();
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String type = getPara("dateType");
            if (type != null) {
                if (type.equals("day")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_activeUser_day, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_phone_day, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_channel_day, condition));
                }
                if (type.equals("week")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_activeUser_week, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_phone_week, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_channel_week, condition));
                }
                if (type.equals("month")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_activeUser_month, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_phone_month, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_channel_month, condition));
                }
                if (type.equals("quarter")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_activeUser_quarter, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_phone_quarter, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_channel_quarter, condition));
                }
                if (type.equals("year")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_activeUser_year, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_phone_year, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_activeUser_channel_year, condition));
                }
            }
            //定义图例名称
            Object[] nameList = new Object[]{res.get("app.startUser")};

            //返回根据日期生成折线图的图表数据
            result.put("timeCharts", genUserTimeCharts(type, dataTime, nameList));
            //返回根据手机OS生成折线图的图表数据
            result.put("osCharts", GenerateAppChartsUtils.genOsCharts(type, dataPhone));
            //返回根据渠道生成折线图的图表数据
            result.put("chlCharts", GenerateAppChartsUtils.genChannelCharts(type, dataChannel, nameList));

            result.put("dataTime", dataTime);
            result.put("dataPhone", dataPhone);
            result.put("dataChannel", dataChannel);
            renderJson(result);
        }

    }

    /**
     * app留存用户数据
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/userAnalysis/retainUser")
    public void retainUser() {
        if (BaseUtils.isAjax(getRequest())) {

            // 定义json类型结果
            JSONObject result = new JSONObject();
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());

            //定义集合并获取对应数据
            List<List<String>> dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_retainUser_day, condition), false);
            List<List<String>> dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_retainUser_phone_day, condition));
            List<List<String>> dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_retainUser_channel_day, condition));

            //生成留存用户按照日期分布的折线图
            Object[] nameList = new Object[]{res.get("app.newAddUser")};
            result.put("timeCharts", genUserTimeCharts("day", dataTime, nameList));
            //生成留存用户按照手机OS分布的折线图
            result.put("osCharts", genOsCharts("day", dataPhone));
            //生成留存用户按照渠道分布的折线图
            result.put("chlCharts", genChannelCharts("day", dataChannel, nameList));

            result.put("dataTime", dataTime);
            result.put("dataPhone", dataPhone);
            result.put("dataChannel", dataChannel);
            renderJson(result);
        }
    }

    /**
     * app注册用户数据
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/userAnalysis/regUser")
    public void regUser() {
        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> dataPhone = new ArrayList<>();
            List<List<String>> dataChannel = new ArrayList<>();
            // 定义json类型结果
            JSONObject result = new JSONObject();
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");
            if (dateType != null) {
                if (dateType.equals("day")) {
                    dataChannel = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_regUser_channel_day, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_regUser_os_day, condition));
                }
                if (dateType.equals("month")) {
                    dataChannel = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_regUser_channel_month, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_regUser_os_month, condition));
                }
                if (dateType.equals("quarter")) {
                    dataChannel = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_regUser_channel_quarter, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_regUser_os_quarter, condition));
                }
                if (dateType.equals("year")) {
                    dataChannel = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_regUser_channel_year, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_regUser_os_year, condition));
                }
            }

            //返回根据手机os生成折线图的图表数据
            result.put("osCharts", GenerateAppChartsUtils.genOsCharts(dateType, dataPhone));

            Object[] chlNameList = new Object[]{res.get("app.newAddUser")};
            result.put("chlCharts", GenerateAppChartsUtils.genChannelCharts(dateType, dataChannel, chlNameList));

            result.put("dataPhone", dataPhone);
            result.put("dataChannel", dataChannel);
            renderJson(result);

        }
    }

    /**
     * app登录用户数据
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/userAnalysis/loginUser")
    public void loginUser() {
        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> dataTime = new ArrayList<>();
            List<List<String>> dataPhone = new ArrayList<>();
            List<List<String>> dataChannel = new ArrayList<>();
            // 定义json类型结果
            JSONObject result = new JSONObject();
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String type = getPara("dateType");
            if (type != null) {
                if (type.equals("day")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_loginUser_day, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_os_day, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_channel_day, condition));
                }
                if (type.equals("month")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_loginUser_month, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_os_month, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_channel_month, condition));
                }
                if (type.equals("quarter")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_loginUser_quarter, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_os_quarter, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_channel_quarter, condition));
                }
                if (type.equals("year")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_loginUser_year, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_os_year, condition));
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_loginUser_channel_year, condition));
                }
            }

            result.put("timeCharts", GenerateAppChartsUtils.genLoginUserTimeCharts(type, dataTime));

            Object[] chlNameList = new Object[]{res.get("app.startUser"), res.get("app.loginUser")};
            result.put("chlCharts", GenerateAppChartsUtils.genMultiLineChannelCharts(type, dataChannel, chlNameList));

            result.put("osCharts", GenerateAppChartsUtils.genOsCharts(type, dataPhone));

            result.put("dataTime", dataTime);
            result.put("dataPhone", dataPhone);
            result.put("dataChannel", dataChannel);
            renderJson(result);
        }
    }

    /**
     * app新增用户数据
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/userAnalysis/newUser")
    public void newUser() {
        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> dataTime = new ArrayList<>();
            List<List<String>> dataPhone = new ArrayList<>();
            // 定义json类型结果
            JSONObject result = new JSONObject();
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String type = getPara("dateType");
            if (type != null) {
                if (type.equals("day")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_newUser_day, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_newUser_phone_day, condition));
                }
                if (type.equals("week")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_newUser_week, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_newUser_phone_week, condition));
                }
                if (type.equals("month")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_newUser_month, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_newUser_phone_month, condition));
                }
                if (type.equals("quarter")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_newUser_quarter, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_newUser_phone_quarter, condition));
                }
                if (type.equals("year")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_newUser_year, condition), false);
                    dataPhone = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_newUser_phone_year, condition));
                }
            }

            //定义折线图图例的名称
            Object[] nameList = new Object[]{res.get("app.newAddUser")};
            result.put("timeCharts", genUserTimeCharts(type, dataTime, nameList));
            result.put("osCharts", GenerateAppChartsUtils.genOsCharts(type, dataPhone));

            result.put("dataTime", dataTime);
            result.put("dataPhone", dataPhone);
            renderJson(result);
        }
    }

}

