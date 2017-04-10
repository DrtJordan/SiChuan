package io.transwarp.scrcu.service.portal;

import com.alibaba.fastjson.JSONObject;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

import java.util.ArrayList;
import java.util.List;

/**
 * Web端事件分析的service层
 *
 * @author hang_xiao
 * @date 2017/4/10
 */
public class EventAnalysisService {

    /**
     * Web统计事件列表
     *
     * @param dateType 日期类型
     * @param condition 查询条件
     * @return Event list json
     */
    public static JSONObject eventList(String dateType, String condition) {

        List<List<String>> eventData = new ArrayList<>();

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

        return result;
    }

    /**
     * Web统计事件详情
     *
     * @param condition 查询条件
     * @return Event detail json
     */
    public static JSONObject eventDetail(String condition) {

        // 执行查询
        List<List<String>> detailData = InceptorUtil
                .query(SqlKit.propSQL(SQLConfig.portal_eventAnalysis_detail_day, condition));

        // 返回结果
        JSONObject result = new JSONObject();
        result.put("detailData", detailData);

        return result;
    }

    /**
     * Web统计事件趋势
     *
     * @param condition 查询条件
     * @return Event tendency json
     */
    public static JSONObject eventTendency(String condition) {
        // 执行查询
        List<List<String>> tendencyData = InceptorUtil
                .query(SqlKit.propSQL(SQLConfig.portal_eventAnalysis_tendency_day, condition));

        // 返回结果
        JSONObject result = new JSONObject();
        result.put("tendencyData", tendencyData);
        return result;
    }
}
