package io.transwarp.scrcu.service.app;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.ChartType;
import io.transwarp.scrcu.base.util.ChartUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

import java.util.ArrayList;
import java.util.List;

/**
 * App渠道逻辑层
 *
 * @author hang_xiao
 * @date 2017/4/10
 */
public class AppChannelService {

    private static Res res = I18n.use("i18n", "zh_CN");

    /**
     * App渠道列表
     *
     * @param dateType  日期类型
     * @param condition 查询条件
     * @return channel list json
     */
    @SuppressWarnings("unchecked")
    public static JSONObject channelList(String dateType, String condition) {

        List<List<String>> data = new ArrayList<>();
        JSONObject result = new JSONObject();

        if (dateType != null) {
            if (dateType.equals("day")) {
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_channel_list_day, condition), false);
            }
            if (dateType.equals("week")) {
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_channel_list_week, condition), false);
            }
            if (dateType.equals("month")) {
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_channel_list_month, condition), false);
            }
            if (dateType.equals("quarter")) {
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_channel_list_quarter, condition), false);
            }
            if (dateType.equals("year")) {
                data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_channel_list_year, condition), false);
            }
        }

        // 返回结果
        List<Object> xDataList = new ArrayList<>();
        List<Object> newUserList = new ArrayList<>();
        List<Object> startUserList = new ArrayList<>();
        List<Object> sumCntList = new ArrayList<>();
        List<Object> startCntList = new ArrayList<>();
        Object[] nameList = new Object[]{res.get("app.newAddUser"), res.get("app.startUser"), res.get("app.accumulativeUser"), res.get("app.startTimes")};
        for (List<String> list : data) {
            if (!xDataList.contains(list.get(0))) {
                xDataList.add(list.get(0));
            }
            newUserList.add(list.get(2));
            startUserList.add(list.get(3));
            sumCntList.add(list.get(4));
            startCntList.add(list.get(6));
        }

        //生成渠道列表的数据
        String genDetailChannel = ChartUtils.genAppMultiLineCharts(dateType, ChartType.LINE, xDataList, nameList, newUserList, startUserList, sumCntList, startCntList);
        result.put("chartOption", genDetailChannel);
        // 返回结果
        result.put("data", data);

        return result;
    }

    /**
     * App渠道详情
     *
     * @param condition 查询条件
     * @return channel detail json
     */
    @SuppressWarnings("unchecked")
    public static JSONObject channelDetail(String condition) {

        // 执行查询
        List<List<String>> dataChannel = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_channel_detail_day, condition), false);

        JSONObject result = new JSONObject();
        // 返回结果
        List<Object> xDataList = new ArrayList<>();
        List<Object> newUserList = new ArrayList<>();
        List<Object> startUserList = new ArrayList<>();
        List<Object> startCntList = new ArrayList<>();
        Object[] nameList = new Object[]{res.get("app.newAddUser"), res.get("app.startUser"), res.get("app.startTimes")};
        for (List<String> list : dataChannel) {
            if (!xDataList.contains(list.get(0))) {
                xDataList.add(list.get(0));
            }
            newUserList.add(list.get(2));
            startUserList.add(list.get(3));
            startCntList.add(list.get(4));
        }

        //生成渠道详情的数据
        String genDetailChannel = ChartUtils.genAppMultiLineCharts("", ChartType.LINE, xDataList, nameList, newUserList, startUserList, startCntList);
        result.put("chartOption", genDetailChannel);
        result.put("data", dataChannel);

        return result;
    }
}
