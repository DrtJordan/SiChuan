package io.transwarp.scrcu.service.portal;

import com.alibaba.fastjson.JSONObject;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

import java.util.ArrayList;
import java.util.List;

import static io.transwarp.scrcu.base.util.GeneratePortalChartsUtils.genSearchEngineCharts;

/**
 * @author hang_xiao
 * @date 2017/4/10
 */
public class VisitSourceService {

    /**
     * 搜索引擎
     *
     * @param dateType  日期类型，day:天, month:月
     * @param condition 查询条件
     * @return searchEngine json
     */
    public static JSONObject searchEngine(String dateType, String condition) {

        //定义集合接收搜索引擎数据
        List<List<String>> searchEngineData = new ArrayList<>();
        List<List<String>> searchEngineChart = new ArrayList<>();
        //接收返回的结果数据
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                // 执行查询
                searchEngineData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_searchEngine_day, condition),
                        false);
                searchEngineChart = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_searchEngine_day_chart, condition),
                        false);
            }

            if (dateType.equals("month")) {
                // 执行查询
                searchEngineData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_searchEngine_month, condition),
                        false);
                searchEngineChart = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_searchEngine_month_chart, condition),
                        false);
            }
        }

        //生成饼图数据并返回
        result.put("chartOption", genSearchEngineCharts(searchEngineChart));

        result.put("data", searchEngineData);

        return result;
    }

    /**
     * 来源分析
     *
     * @param dateType  日期类型，day:天, month:月
     * @param condition 查询条件
     * @return source json
     */
    public static JSONObject source(String dateType, String condition) {
        // 执行查询
        List<List<String>> dataSource = new ArrayList<>();

        if (dateType != null) {
            if (dateType.equals("day")) {
                // 执行查询
                dataSource = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_source_day, condition),
                        false);
            }

            if (dateType.equals("month")) {
                // 执行查询
                dataSource = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_source_month, condition),
                        false);
            }
        }
        // 返回结果
        JSONObject result = new JSONObject();
        result.put("dataSource", dataSource);
        return result;
    }

    /**
     * 入口页面分析
     *
     * @param dateType 日期类型，day:天, month:月
     * @param condition 查询条件
     * @return entryPage json
     */
    public static JSONObject entryPage(String dateType, String condition) {

        List<List<String>> dataPage = new ArrayList<>();

        if (dateType != null) {
            if (dateType.equals("day")) {
                // 执行查询,不使用缓存
                dataPage = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_entryPage_query_day, condition),
                        false);
            }

            if (dateType.equals("month")) {
                // 执行查询,不使用缓存
                dataPage = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_entryPage_query_month, condition),
                        false);
            }
        }

        // 返回结果
        JSONObject result = new JSONObject();
        result.put("dataPage", dataPage);

        return result;
    }
}
