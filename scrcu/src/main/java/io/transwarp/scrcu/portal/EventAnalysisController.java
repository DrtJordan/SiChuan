package io.transwarp.scrcu.portal;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/1/10.
 */
@RequiresAuthentication
public class EventAnalysisController extends Controller {

    @ActionKey("/portal/eventAnalysis")
    public void index(){}

    /**
     * 事件列表
     */
    @RequiresPermissions("/portal/eventAnalysis/list")
    public void list(){
        if (BaseUtils.isAjax(getRequest())) {

            List<List<String>> eventData = new ArrayList<>();
            //获取查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

            if (dateType != null) {
                if (dateType.equals("day")) {
                    // 执行查询
                    eventData = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_eventAnalysis_list_day, condition), false);
                }
                if (dateType.equals("week")) {
                    // 执行查询
                    eventData = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_eventAnalysis_list_week, condition), false);
                }
                if (dateType.equals("month")) {
                    // 执行查询
                    eventData = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_eventAnalysis_list_month, condition), false);
                }
                if (dateType.equals("quarter")) {
                    // 执行查询
                    eventData = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_eventAnalysis_list_quarter, condition), false);
                }
                if (dateType.equals("year")) {
                    // 执行查询
                    eventData = InceptorUtil.query(SqlKit.propSQL(SQLConfig.portal_eventAnalysis_list_year, condition), false);
                }
            }

            // 返回结果
            JSONObject result = new JSONObject();
            result.put("eventData", eventData);
            renderJson(result);
        }
    }

    @RequiresPermissions("/portal/eventAnalysis/detail")
    public void detail(){
        if (BaseUtils.isAjax(getRequest())) {

            String condition = InceptorUtil.getQueryCondition(getRequest());

            // 执行查询
            List<List<String>> detailData = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_eventAnalysis_detail_day, condition));

            // 返回结果
            JSONObject result = new JSONObject();
            result.put("detailData", detailData);
            renderJson(result);
        }
    }

    @RequiresPermissions("/portal/eventAnalysis/tendency")
    public void tendency(){
        if (BaseUtils.isAjax(getRequest())) {

            String condition = InceptorUtil.getQueryCondition(getRequest());
            // 执行查询
            List<List<String>> tendencyData = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_eventAnalysis_tendency_day, condition));

            // 返回结果
            JSONObject result = new JSONObject();
            result.put("tendencyData", tendencyData);
            renderJson(result);
        }
    }
}
