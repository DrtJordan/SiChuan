package io.transwarp.scrcu.app;

import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.ChartUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

@RequiresAuthentication
public class AppController extends Controller {

    Res res = I18n.use("i18n", "zh_CN");

    /**
     * 获取启动次数
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/appAnalysis/startCount")
    public void startCount() {
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
                    dataPhone = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_startCount_phone_day, condition), false);
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_startCount_channel_day, condition));
                }
                if (dateType.equals("month")) {
                    dataPhone = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_startCount_phone_month, condition), false);
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_startCount_channel_month, condition));
                }
                if (dateType.equals("quarter")) {
                    dataPhone = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_startCount_phone_quarter, condition),false);
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_startCount_channel_quarter, condition));
                }
                if (dateType.equals("year")) {
                    dataPhone = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_startCount_phone_year, condition),false);
                    dataChannel = InceptorUtil.query(SqlKit.propSQL(SQLConfig.app_startCount_channel_year, condition));
                }
            }
            // 返回结果
            List<Object> xAxisList = new ArrayList<>();
            List<Object> androidList = new ArrayList<>();
            List<Object> iosList = new ArrayList<>();
            for (List<String> list : dataPhone) {
                if (!xAxisList.contains(list.get(0))) {
                    xAxisList.add(list.get(0));
                }
                String os = list.get(1);
                if (os.contains("Android")) {
                    androidList.add(list.get(2));
                }
                if (os.toUpperCase().contains("IOS")) {
                    iosList.add(list.get(2));
                }
            }

            //定义折线图图例的名称
            Object[] nameList = new Object[]{"android", "ios"};
            // 生成启动次数折线图数据
            String genLineChart = ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList, androidList, iosList);
            result.put("chartOption", genLineChart);
            result.put("dataPhone", dataPhone);
            result.put("dataChannel", dataChannel);
            renderJson(result);
        }
    }

    /**
     * 获取app版本
     */
    @RequiresPermissions("/app/appAnalysis/version")
    public void version() {
        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> data = new ArrayList<>();
            // 定义json类型结果
            JSONObject result = new JSONObject();
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String type = getPara("dateType");
            if (type != null) {
                if (type.equals("day")) {
                    data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_day, condition), false);
                }
                if (type.equals("month")) {
                    data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_month, condition), false);
                }
                if (type.equals("quarter")) {
                    data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_quarter, condition), false);
                }
                if (type.equals("year")) {
                    data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_year, condition), false);
                }
            }
            //返回结果
            result.put("data", data);
            renderJson(result);
        }
    }

}
