package io.transwarp.scrcu.base.util;

import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.echarts.data.Data;

import java.util.ArrayList;
import java.util.List;

import static sun.swing.MenuItemLayoutHelper.max;

/**
 * @author hang_xiao
 * @date 2017/3/9
 */
public class GenerateAppChartsUtils {

    public static final Res res = I18n.use("i18n", "zh_CN");

    /**
     * 生成app统计登陆用户-日期分布的折线图数据
     *
     * @param dateType  日期类型：day,week,month,quarter,year
     * @param chartType 图表类型： LINE--折线图, BAR--柱状图
     * @param dataTime  登陆用户日期数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genLoginUserTimeCharts(String dateType, ChartType chartType, List<List<String>> dataTime) {

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

        return ChartUtils.genAppMultiLineCharts(dateType, chartType, xAxisList, nameList, startCntList, loginCntList);

    }

    /**
     * 生成渠道的Echarts饼图数据
     *
     * @param dateType    日期类型：day,week,month,quarter,year
     * @param dataChannel 渠道数据
     * @param name        饼图名称
     * @return 饼图数据
     */
    @SuppressWarnings("unchecked")
    public static String genChannelCharts(String dateType, List<List<String>> dataChannel, String name) {
        // 返回结果
        List<Object> chlCntList = new ArrayList<>();
        for (List<String> list : dataChannel) {
            Data d = new Data(list.get(0), list.get(1));
            chlCntList.add(d);
        }

        return ChartUtils.genPie(dateType, name, chlCntList);
    }

    /**
     * 生成os的Echarts图表数据
     *
     * @param dateType  日期类型：day,week,month,quarter,year
     * @param chartType 图表类型： LINE--折线图, BAR--柱状图
     * @param dataOs    手机OS数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genOsCharts(String dateType, ChartType chartType, List<List<String>> dataOs) {
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

        return ChartUtils.genAppMultiLineCharts(dateType, chartType, xAxisList, osNameList, androidList, iosList);
    }

    /**
     * 生成app统计-用户根据日期分布的折线图数据
     * 功能：活跃用户，留存用户，新增用户
     *
     * @param dateType  日期类型：day,week,month,quarter,year
     * @param chartType 图表类型： LINE--折线图, BAR--柱状图
     * @param dataTime  数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genUserTimeCharts(String dateType, ChartType chartType, List<List<String>> dataTime, Object[] nameList) {

        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        List<Object> newUserCntList = new ArrayList<>();
        for (List<String> list : dataTime) {
            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
            }
            newUserCntList.add(list.get(1));
        }
        return ChartUtils.genAppMultiLineCharts(dateType, chartType, xAxisList, nameList, newUserCntList);

    }

    /**
     * 生成app版本根据日期分布的折线图数据
     *
     * @param dateType  日期类型：day,week,month,quarter,year
     * @param chartType 图表类型： LINE--折线图, BAR--柱状图
     * @param data      数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genAppVersionCharts(String dateType, ChartType chartType, List<List<String>> data) {

        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        List<Object> newUserCntList = new ArrayList<>();
        List<Object> userCntList = new ArrayList<>();
        List<Object> startCntList = new ArrayList<>();
        Object[] nameList = new Object[]{"用户数", "新增用户数", "启动用户数"};
        for (List<String> list : data) {
            xAxisList.add(list.get(0));
            userCntList.add(list.get(1));
            newUserCntList.add(list.get(2));
            startCntList.add(list.get(3));
        }
        return ChartUtils.genAppMultiLineCharts(dateType, chartType, xAxisList, nameList, userCntList, newUserCntList, startCntList);

    }

    /**
     * 生成会员流失分布的折线图数据
     *
     * @param dateType  日期类型：day,week,month,quarter,year
     * @param chartType 图表类型： LINE--折线图, BAR--柱状图
     * @param data      数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genMemberRunOffCharts(String dateType, ChartType chartType, List<List<String>> data) {

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
        return ChartUtils.genAppMultiLineCharts(dateType, chartType, xAxisList, nameList, memberRunOffCntList, memberSleepCntList, memberCallbackCntList);

    }

    /**
     * 生成APP使用时长-根据日期分布的折线图数据
     *
     * @param dateType  日期类型：day,week,month,quarter,year
     * @param chartType 图表类型： LINE--折线图, BAR--柱状图
     * @param data      数据
     * @return 图表数据
     */
    @SuppressWarnings("unchecked")
    public static String genAppBehaviorCharts(String dateType, ChartType chartType, List<List<String>> data) {

        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        //定义时长对应的集合用来接收对应的数据
        List<Object> fourToTenSecondsList = new ArrayList<>();
        List<Object> threeToTenMinList = new ArrayList<>();
        List<Object> thirtyMinList = new ArrayList<>();
        List<Object> elevenToThirtySecondsList = new ArrayList<>();
        List<Object> tenToThirtyMinList = new ArrayList<>();
        List<Object> oneToThreeMinList = new ArrayList<>();
        List<Object> thirtyFirstToSixtySecList = new ArrayList<>();
        List<Object> oneToThreeSecList = new ArrayList<>();

        //图例名称
        Object[] nameList = new Object[]{"1-3秒", "4-10秒", "11-30秒", "31-60秒", "1-3分", "3-10分", "10-30分", "30分以上"};

        for (int i = 0; i < data.size(); i++) {

            List<String> list = data.get(i);

            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
                int max = max(fourToTenSecondsList.size(), threeToTenMinList.size(), thirtyMinList.size(), elevenToThirtySecondsList.size(),
                        tenToThirtyMinList.size(), oneToThreeMinList.size(), thirtyFirstToSixtySecList.size(), oneToThreeSecList.size());
                addChartData(max, oneToThreeSecList, fourToTenSecondsList, elevenToThirtySecondsList,
                        thirtyFirstToSixtySecList, oneToThreeMinList, threeToTenMinList, tenToThirtyMinList, thirtyMinList);
            }

            if (list.get(1).equals("1-3秒")) {
                oneToThreeSecList.add(list.get(2));
            }
            if (list.get(1).equals("4-10秒")) {
                fourToTenSecondsList.add(list.get(2));
            }
            if (list.get(1).equals("11-30秒")) {
                elevenToThirtySecondsList.add(list.get(2));
            }
            if (list.get(1).equals("31-60秒")) {
                thirtyFirstToSixtySecList.add(list.get(2));
            }
            if (list.get(1).equals("1-3分")) {
                oneToThreeMinList.add(list.get(2));
            }
            if (list.get(1).equals("3-10分")) {
                threeToTenMinList.add(list.get(2));
            }
            if (list.get(1).equals("10-30分")) {
                tenToThirtyMinList.add(list.get(2));
            }
            if (list.get(1).equals("30分以上")) {
                thirtyMinList.add(list.get(2));
            }

            if (i == data.size() - 1) {
                int max = max(fourToTenSecondsList.size(), threeToTenMinList.size(), thirtyMinList.size(), elevenToThirtySecondsList.size(),
                        tenToThirtyMinList.size(), oneToThreeMinList.size(), thirtyFirstToSixtySecList.size(), oneToThreeSecList.size());
                addChartData(max, oneToThreeSecList, fourToTenSecondsList, elevenToThirtySecondsList,
                        thirtyFirstToSixtySecList, oneToThreeMinList, threeToTenMinList, tenToThirtyMinList, thirtyMinList);
            }

        }
        //生成APP使用时长的折线图数据并返回,此处传入list数据的顺序必须按照nameList中的顺序传入，否则会造成数据对应错误
        return ChartUtils.genAppMultiLineCharts(dateType, chartType, xAxisList, nameList, oneToThreeSecList, fourToTenSecondsList, elevenToThirtySecondsList
                , thirtyFirstToSixtySecList, oneToThreeMinList, threeToTenMinList, tenToThirtyMinList, thirtyMinList);
    }

    /**
     * 生成APP使用频率的折线图数据
     *
     * @param dateType  日期类型：day,week,month,quarter,year
     * @param chartType 图表类型： LINE--折线图, BAR--柱状图
     * @param data      数据
     * @return 图表数据
     */
    @SuppressWarnings("unchecked")
    public static String genAppUseRateCharts(String dateType, ChartType chartType, List<List<String>> data) {

        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        //定义时长对应的集合用来接收对应的数据
        List<Object> oneToTwqTimes = new ArrayList<>();
        List<Object> threeToFiveTimes = new ArrayList<>();
        List<Object> sixToNineTimes = new ArrayList<>();
        List<Object> tenToNineteenTimes = new ArrayList<>();
        List<Object> twentyToFourtyNineTimes = new ArrayList<>();
        List<Object> fiftyOverTimes = new ArrayList<>();
        //定义图例的name名称
        Object[] nameList = new Object[]{"1-2次", "3-5次", "6-9次", "10-19次", "20-49次", "50次及以上"};

        for (int i = 0; i < data.size(); i++) {

            List<String> list = data.get(i);

            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
                int max = max(oneToTwqTimes.size(), threeToFiveTimes.size(), sixToNineTimes.size(), tenToNineteenTimes.size(), twentyToFourtyNineTimes.size(), fiftyOverTimes.size());
                addChartData(max, oneToTwqTimes, threeToFiveTimes, sixToNineTimes, tenToNineteenTimes, twentyToFourtyNineTimes, fiftyOverTimes);
            }
            if (list.get(1).equals("1-2次")) {
                oneToTwqTimes.add(list.get(2));
            }
            if (list.get(1).equals("3-5次")) {
                threeToFiveTimes.add(list.get(2));
            }
            if (list.get(1).equals("6-9次")) {
                sixToNineTimes.add(list.get(2));
            }
            if (list.get(1).equals("10-19次")) {
                tenToNineteenTimes.add(list.get(2));
            }
            if (list.get(1).equals("20-49次")) {
                twentyToFourtyNineTimes.add(list.get(2));
            }
            if (list.get(1).equals("50次及以上")) {
                fiftyOverTimes.add(list.get(2));
            }

            if (i == data.size() - 1) {
                int max = max(oneToTwqTimes.size(), threeToFiveTimes.size(), sixToNineTimes.size(), tenToNineteenTimes.size(), twentyToFourtyNineTimes.size(), fiftyOverTimes.size());
                addChartData(max, oneToTwqTimes, threeToFiveTimes, sixToNineTimes, tenToNineteenTimes, twentyToFourtyNineTimes, fiftyOverTimes);
            }
        }
        //生成使用频率的折线图数据
        return ChartUtils.genAppMultiLineCharts(dateType, chartType, xAxisList, nameList,
                oneToTwqTimes, threeToFiveTimes, sixToNineTimes, tenToNineteenTimes, twentyToFourtyNineTimes, fiftyOverTimes);
    }

    /**
     * 生成APP访问深度的折线图数据
     *
     * @param dateType  日期类型：day,week,month,quarter,year
     * @param chartType 图表类型： LINE--折线图, BAR--柱状图
     * @param data      数据
     * @return 图表数据
     */
    @SuppressWarnings("unchecked")
    public static String genAppDepthCharts(String dateType, ChartType chartType, List<List<String>> data) {
        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        //定义时长对应的集合用来接收对应的数据
        List<Object> oneToTwoPageList = new ArrayList<>();
        List<Object> threeToFivePageList = new ArrayList<>();
        List<Object> sixToNinePageList = new ArrayList<>();
        List<Object> tenToTwNinePageList = new ArrayList<>();
        List<Object> thirtyToMorePageList = new ArrayList<>();
        Object[] nameList = new Object[]{"1-2页面", "3-5页面", "6-9页面", "10-29页面", "30个页面及以上"};

        for (int i = 0; i < data.size(); i++) {

            List<String> list = data.get(i);

            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
                int max = max(oneToTwoPageList.size(), threeToFivePageList.size(), sixToNinePageList.size(), tenToTwNinePageList.size(), thirtyToMorePageList.size());
                addChartData(max, oneToTwoPageList, threeToFivePageList, sixToNinePageList, tenToTwNinePageList, thirtyToMorePageList);
            }
            if (list.get(1).equals("1-2页面")) {
                oneToTwoPageList.add(list.get(2));
            }
            if (list.get(1).equals("3-5页面")) {
                threeToFivePageList.add(list.get(2));
            }
            if (list.get(1).equals("6-9页面")) {
                sixToNinePageList.add(list.get(2));
            }
            if (list.get(1).equals("10-29页面")) {
                tenToTwNinePageList.add(list.get(2));
            }
            if (list.get(1).equals("30个页面及以上")) {
                thirtyToMorePageList.add(list.get(2));
            }

            if (i == data.size() - 1) {
                int max = max(oneToTwoPageList.size(), threeToFivePageList.size(), sixToNinePageList.size(), tenToTwNinePageList.size(), thirtyToMorePageList.size());
                addChartData(max, oneToTwoPageList, threeToFivePageList, sixToNinePageList, tenToTwNinePageList, thirtyToMorePageList);
            }

        }
        return ChartUtils.genAppMultiLineCharts(dateType, chartType, xAxisList, nameList, oneToTwoPageList, threeToFivePageList,
                sixToNinePageList, tenToTwNinePageList, thirtyToMorePageList);
    }

    /**
     * 生成APP访问间隔的折线图数据
     *
     * @param dateType  日期类型：day,week,month,quarter,year
     * @param chartType 图表类型： LINE--折线图, BAR--柱状图
     * @param data      数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genAppIntervalCharts(String dateType, ChartType chartType, List<List<String>> data) {
        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        //定义时长对应的集合用来接收对应的数据
        List<Object> firstList = new ArrayList<>();
        List<Object> zeroToTwentyFourHours = new ArrayList<>();
        List<Object> oneToTwoDays = new ArrayList<>();
        List<Object> twoToFourDays = new ArrayList<>();
        List<Object> fiveToSevenDay = new ArrayList<>();
        List<Object> eightToFourteenDays = new ArrayList<>();
        List<Object> fifteenToThirtyDays = new ArrayList<>();

        //定义图例的name名称
        Object[] nameList = new Object[]{"首次", "0-24h", "1-2天", "2-4天", "5-7天", "8-14天", "15-30天"};
        for (int i = 0; i < data.size(); i++) {

            List<String> list = data.get(i);

            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
                int max = max(firstList.size(), zeroToTwentyFourHours.size(), oneToTwoDays.size(), twoToFourDays.size(), fiveToSevenDay.size(), eightToFourteenDays.size(), fifteenToThirtyDays.size());
                addChartData(max, firstList, zeroToTwentyFourHours,
                        oneToTwoDays, twoToFourDays, fiveToSevenDay, eightToFourteenDays, fifteenToThirtyDays);
            }

            if (list.get(1).contains("首次")) {
                firstList.add(list.get(2));
            }
            if (list.get(1).contains("0-24h")) {
                zeroToTwentyFourHours.add(list.get(2));
            }
            if (list.get(1).contains("1-2天")) {
                oneToTwoDays.add(list.get(2));
            }
            if (list.get(1).contains("2-4天")) {
                twoToFourDays.add(list.get(2));
            }
            if (list.get(1).contains("5-7天")) {
                fiveToSevenDay.add(list.get(2));
            }
            if (list.get(1).contains("8-14天")) {
                eightToFourteenDays.add(list.get(2));
            }
            if (list.get(1).contains("15-30天")) {
                fifteenToThirtyDays.add(list.get(2));
            }

            if (i == data.size() - 1) {
                int max = max(firstList.size(), zeroToTwentyFourHours.size(), oneToTwoDays.size(), twoToFourDays.size(), fiveToSevenDay.size(), eightToFourteenDays.size(), fifteenToThirtyDays.size());
                addChartData(max, firstList, zeroToTwentyFourHours,
                        oneToTwoDays, twoToFourDays, fiveToSevenDay, eightToFourteenDays, fifteenToThirtyDays);
            }

        }

        return ChartUtils.genAppMultiLineCharts(dateType, chartType, xAxisList, nameList, firstList, zeroToTwentyFourHours,
                oneToTwoDays, twoToFourDays, fiveToSevenDay, eightToFourteenDays, fifteenToThirtyDays);
    }

    /**
     * 增加Echarts数据
     *
     * @param max  集合size的最大值
     * @param data 各集合数据
     */
    @SafeVarargs
    private static void addChartData(int max, List<Object>... data) {
        for (List<Object> dataList : data) {
            if (dataList.size() < max) {
                dataList.add(0);
            }
        }
    }
}
