package io.transwarp.scrcu.portal;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.ActionKey;
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
public class UserAnalysisController extends Controller {

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
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> data = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_area_query, condition), 20);
            //查询访客数最大值
            List<List<String>> maxUv = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_max_uv));
            // 返回结果
            JSONObject result = new JSONObject();
            List<Object> dataList = new ArrayList<Object>();
            for (List<String> list : data) {
                String name = list.get(0).replace("甘孜州", "甘孜藏族自治州").replace("阿坝州", "阿坝藏族羌族自治州").replace("凉山州", "凉山彝族自治州");
                //获取访客数量
                Data uvValue = new Data(name, list.get(1));
                dataList.add(uvValue);
            }
            //生成四川省地域分布图
            String areaMap = ChartUtils.genMapChart(res.get("portal.visitor"), dataList, maxUv);
            result.put("chartOption", areaMap);
            result.put("data", data);
            renderJson(result);

        }

    }

    /**
     * 访问次数
     */
    @RequiresPermissions("/portal/userAnalysis/terminal")
    public void terminal() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> terminalos = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_terminal_os, condition),
                    5);
            List<List<String>> terminalbrowser = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_terminal_browserDiv, condition), 5);

            // 返回结果
            List<Object> dataList2 = new ArrayList<Object>();
            List<Object> dataList3 = new ArrayList<Object>();
            for (List<String> list : terminalos) {
                Data d = new Data(list.get(0), InceptorUtil.getDouble(list.get(1)));
                dataList2.add(d);
            }
            for (List<String> list : terminalbrowser) {
                Data d = new Data(list.get(0), InceptorUtil.getDouble(list.get(1)));
                dataList3.add(d);
            }
            // 返回结果
            JSONObject result = new JSONObject();
            String str2 = ChartUtils.genPie(res.get("portal.visitorCount"), dataList2);
            String str3 = ChartUtils.genPie(res.get("portal.visitorCount"), dataList3);
            // 生成图
            result.put("chartOption2", str2);
            result.put("chartOption3", str3);

            result.put("data_terminalos", terminalos);
            result.put("data_terminalbrowser", terminalbrowser);
            renderJson(result);
        }

    }

    @RequiresPermissions("/portal/userAnalysis/userLevel")
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

    }

    // @RequiresPermissions("/portal/useranalysis/userVisitPage")
    @Deprecated
    public void userVisitPage() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> data = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_userVisitPage_query.toString()) + condition);
            // 返回结果
            JSONObject result = new JSONObject();
            result.put("data", data);
            renderJson(result);
        }

    }

    /**
     * 流失用户分析
     */
    @RequiresPermissions("/portal/userAnalysis/userLoss")
    public void userLoss() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> data = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_userLoss_query.toString()) + condition);
            // 返回结果
            JSONObject result = new JSONObject();
            result.put("data", data);
            renderJson(result);
        }

    }

    /**
     * 唯一用户分析
     */
    @RequiresPermissions("/portal/userAnalysis/userOnly")
    public void userOnly() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到时间查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> data = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_userAnalysis_userOnly.toString()) + condition);
            // 返回结果
            JSONObject result = new JSONObject();
            result.put("data", data);
            renderJson(result);
        }
    }

}
