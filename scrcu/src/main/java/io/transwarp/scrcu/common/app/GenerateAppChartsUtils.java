package io.transwarp.scrcu.common.app;

import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.scrcu.base.util.ChartUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hang_xiao
 * @date 2017/3/9
 */
public class GenerateAppChartsUtils {

    public static final Res res = I18n.use("i18n", "zh_CN");

    /**
     * 生成app统计-登陆用户日期分布的折线图数据
     *
     * @param dateType 日期类型：day,week,month,quarter,year
     * @param dataTime 登陆用户日期数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genLoginUserTimeCharts(String dateType, List<List<String>> dataTime) {

        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        List<Object> startCntList = new ArrayList<>();
        List<Object> loginCntList = new ArrayList<>();
        for (List<String> list : dataTime) {
            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
            }
            startCntList.add(list.get(1));
            loginCntList.add(list.get(2));
        }

        //图例名称
        Object[] nameList = new Object[]{res.get("app.startUser"), res.get("app.loginUser")};

        return ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList, startCntList, loginCntList);

    }

    /**
     * 生成渠道的Echarts图表数据
     *
     * @param dateType 日期类型：day,week,month,quarter,year
     * @param dataChannel 渠道数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genChannelCharts(String dateType, List<List<String>> dataChannel, Object[] names) {
        // 返回结果
        List<Object> chlXAxisList = new ArrayList<>();
        List<Object> chlStartCntList = new ArrayList<>();
        for (List<String> list : dataChannel) {
            if (!chlXAxisList.contains(list.get(0))) {
                chlXAxisList.add(list.get(0));
            }
            if (list.get(1).equals("07")) {
                chlStartCntList.add(list.get(2));
            }
        }

        return ChartUtils.genAppMultiLineCharts(dateType, chlXAxisList, names, chlStartCntList);
    }

    /**
     * 生成os的Echarts图表数据
     *
     * @param dateType 日期类型：day,week,month,quarter,year
     * @param dataOs 手机OS数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genOsCharts(String dateType, List<List<String>> dataOs) {
        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        List<Object> androidList = new ArrayList<>();
        List<Object> iosList = new ArrayList<>();
        for (List<String> list : dataOs) {
            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
            }
            if (list.get(1).toUpperCase().equals("ANDROID")) {
                androidList.add(list.get(2));
            }
            if (list.get(1).toUpperCase().equals("IOS")) {
                iosList.add(list.get(2));
            }
        }
        Object[] osNameList = new Object[]{"Android", "IOS"};

        return ChartUtils.genAppMultiLineCharts(dateType, xAxisList, osNameList, androidList, iosList);
    }

    /**
     * 生成app统计-用户根据日期分布的折线图数据
     *
     * @param dateType 日期类型：day,week,month,quarter,year
     * @param dataTime 数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genUserTimeCharts(String dateType, List<List<String>> dataTime, Object[] nameList) {

        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        List<Object> newUserCntList = new ArrayList<>();
        for (List<String> list : dataTime) {
            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
            }
            newUserCntList.add(list.get(1));
        }
        return ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList, newUserCntList);

    }

    /**
     * 生成会员流失分布的折线图数据
     *
     * @param dateType 日期类型：day,week,month,quarter,year
     * @param data 数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genMemberRunOffCharts(String dateType, List<List<String>> data) {

        //定义X轴接收数据
        List<Object> xAxisList = new ArrayList<>();
        //会员流失数
        List<Object> memberRunOffCntList = new ArrayList<>();
        //休眠会员数
        List<Object> memberSleepCntList = new ArrayList<>();
        //回访用户数
        List<Object> memberCallbackCntList = new ArrayList<>();

        Object[] nameList = new Object[]{"会员流失数", "休眠会员数", "回访用户数"};
        for (List<String> list : data) {
            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
            }
            memberRunOffCntList.add(list.get(1));
            memberSleepCntList.add(list.get(3));
            memberCallbackCntList.add(list.get(5));
        }
        //生成会员流失的折线图数据并返回,此处传入list数据的顺序必须按照nameList中的顺序传入，否则会造成数据对应错误
        return ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList, memberRunOffCntList, memberSleepCntList, memberCallbackCntList);

    }
}
