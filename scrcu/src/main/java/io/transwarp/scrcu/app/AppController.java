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
    @RequiresPermissions("/app/appAnalysis/startCount")
    public void startCount() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> dataPhone = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.app_startCount_phone) + condition, 35);
            List<List<String>> dataChannel = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.app_startCount_channel) + condition, 35);

            JSONObject result = new JSONObject();
            // 返回结果
            List<Object> xAxisList = new ArrayList<Object>();
            List<Object> dataList = new ArrayList<Object>();
            for (List<String> list : dataPhone) {
                xAxisList.add(list.get(0));
                dataList.add(Integer.valueOf(list.get(3)));
            }

            // 生成启动次数折线图数据
            String genLineChart = ChartUtils.genLineChart(res.get("app.startTimes"), xAxisList, dataList);
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

            List<List<String>> dataTime = new ArrayList<>();
            // 定义json类型结果
            JSONObject result = new JSONObject();
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String type = getPara("dateType");
            if (type != null) { 
                if (type.equals("day")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_day, condition), false);
                }
                if (type.equals("month")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_mouth, condition), false);
                }
                if (type.equals("quarter")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_quarter, condition), false);
                }
                if (type.equals("year")) {
                    dataTime = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_version_year, condition), false);
                }
            }
            //返回结果
            result.put("dataTime", dataTime);
            renderJson(result);
        }
    }

}
