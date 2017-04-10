package io.transwarp.scrcu.service.app;

import com.alibaba.fastjson.JSONObject;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.ChartType;
import io.transwarp.scrcu.base.util.GenerateAppChartsUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

import java.util.ArrayList;
import java.util.List;

/**
 * App会员统计逻辑层
 *
 * @author hang_xiao
 * @date 2017/4/10
 */
public class AppMemberService {

    /**
     * 会员页面分析
     *
     * @param dateType 日期类型
     * @param condition 查询条件
     * @return memberPage json
     */
    public static JSONObject memberPage(String dateType, String condition) {

        List<List<String>> data = new ArrayList<>();
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_memberPage_day, condition), false);
            }
            if (dateType.equals("month")) {
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_memberPage_month, condition), false);
            }
        }
        // 返回结果
        result.put("data", data);

        return result;
    }

    /**
     * 会员流失分析
     *
     * @param dateType 日期类型
     * @param condition 查询条件
     * @return memberRunOff json
     */
    public static JSONObject memberRunOff(String dateType, String condition) {

        //执行查询
        List<List<String>> data = new ArrayList<>();
        //返回结果
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_member_runoff_day, condition), false);
            }
            if (dateType.equals("week")) {
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_member_runoff_week, condition), false);
            }
        }

        //返回会员流失生成的折线图图表数据
        result.put("memberLostCharts", GenerateAppChartsUtils.genMemberRunOffCharts(dateType, ChartType.LINE,  data));

        result.put("data", data);

        return result;
    }
}
