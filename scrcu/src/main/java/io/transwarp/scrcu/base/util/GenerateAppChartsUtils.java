package io.transwarp.scrcu.base.util;

import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.scrcu.base.util.ChartUtils;

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
     * @param dateType    日期类型：day,week,month,quarter,year
     * @param dataChannel 渠道数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genChannelCharts(String dateType, List<List<String>> dataChannel, Object[] names) {
        // 返回结果
        List<Object> chlXAxisList = new ArrayList<>();
        List<Object> chlCntList = new ArrayList<>();
        for (List<String> list : dataChannel) {
            if (!chlXAxisList.contains(list.get(0))) {
                chlXAxisList.add(list.get(0));
            }
//            if (list.get(1).equals("07")) {
            chlCntList.add(list.get(2));
//            }
        }

        return ChartUtils.genAppMultiLineCharts(dateType, chlXAxisList, names, chlCntList);
    }

    /**
     * 生成多条渠道的Echarts图表数据
     *
     * @param dateType    日期类型：day,week,month,quarter,year
     * @param dataChannel 渠道数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genMultiLineChannelCharts(String dateType, List<List<String>> dataChannel, Object[] names) {
        // 返回结果
        List<Object> chlXAxisList = new ArrayList<>();
        List<Object> chlUserCntList1 = new ArrayList<>();
        List<Object> chlUserCntList2 = new ArrayList<>();
        for (List<String> list : dataChannel) {
            if (!chlXAxisList.contains(list.get(0))) {
                chlXAxisList.add(list.get(0));
            }
            chlUserCntList1.add(list.get(2));
            chlUserCntList2.add(list.get(3));
        }

        return ChartUtils.genAppMultiLineCharts(dateType, chlXAxisList, names, chlUserCntList1, chlUserCntList2);
    }

    /**
     * 生成os的Echarts图表数据
     *
     * @param dateType 日期类型：day,week,month,quarter,year
     * @param dataOs   手机OS数据
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
     * 生成app版本根据日期分布的折线图数据
     *
     * @param dateType 日期类型：day,week,month,quarter,year
     * @param data     数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genAppVersionCharts(String dateType, List<List<String>> data) {

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
        return ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList, userCntList, newUserCntList, startCntList);

    }

    /**
     * 生成会员流失分布的折线图数据
     *
     * @param dateType 日期类型：day,week,month,quarter,year
     * @param data     数据
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

    /**
     * 生成APP使用时长的折线图数据
     *
     * @param dateType 日期类型：day,week,month,quarter,year
     * @param dataTime 数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genAppUseTimeCharts(String dateType, List<List<String>> dataTime) {

        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        //定义时长对应的集合用来接收对应的数据
        List<Object> fourToTenSecondsList = new ArrayList<Object>();
        List<Object> threeToTenMinList = new ArrayList<Object>();
        List<Object> thirtyMinList = new ArrayList<Object>();
        List<Object> elevenToThirtySecondsList = new ArrayList<Object>();
        List<Object> tenToThirtyMinList = new ArrayList<Object>();
        List<Object> oneToThreeMinList = new ArrayList<Object>();
        List<Object> thirtyFirstToSixtySecList = new ArrayList<Object>();
        List<Object> oneToThreeSecList = new ArrayList<Object>();
        //图例名称
        Object[] nameList = new Object[]{"4-10秒", "3-10分", "30分以上", "11-30秒", "10-30分", "1-3分", "31-60秒", "1-3秒"};

        for (List<String> list : dataTime) {

            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
                int max = max(fourToTenSecondsList.size(), threeToTenMinList.size(), thirtyMinList.size(), elevenToThirtySecondsList.size(),
                        tenToThirtyMinList.size(), oneToThreeMinList.size(), thirtyFirstToSixtySecList.size(), oneToThreeSecList.size());
                if (fourToTenSecondsList.size() < max) {
                    fourToTenSecondsList.add(0);
                }
                if (threeToTenMinList.size() < max) {
                    threeToTenMinList.add(0);
                }
                if (thirtyMinList.size() < max) {
                    thirtyMinList.add(0);
                }
                if (elevenToThirtySecondsList.size() < max) {
                    elevenToThirtySecondsList.add(0);
                }
                if (tenToThirtyMinList.size() < max) {
                    tenToThirtyMinList.add(0);
                }
                if (oneToThreeMinList.size() < max) {
                    oneToThreeMinList.add(0);
                }
                if (thirtyFirstToSixtySecList.size() < max) {
                    thirtyFirstToSixtySecList.add(0);
                }
                if (oneToThreeSecList.size() < max) {
                    oneToThreeSecList.add(0);
                }
            }
            if (list.get(1).equals("4-10秒")) {
                fourToTenSecondsList.add(list.get(2));
            }
            if (list.get(1).equals("3-10分")) {
                threeToTenMinList.add(list.get(2));
            }
            if (list.get(1).equals("30分以上")) {
                thirtyMinList.add(list.get(2));
            }
            if (list.get(1).equals("11-30秒")) {
                elevenToThirtySecondsList.add(list.get(2));
            }
            if (list.get(1).equals("10-30分")) {
                tenToThirtyMinList.add(list.get(2));
            }
            if (list.get(1).equals("1-3分")) {
                oneToThreeMinList.add(list.get(2));
            }
            if (list.get(1).equals("31-60秒")) {
                thirtyFirstToSixtySecList.add(list.get(2));
            }
            if (list.get(1).equals("1-3秒")) {
                oneToThreeSecList.add(list.get(2));
            }
        }
        //生成APP使用时长的折线图数据并返回,此处传入list数据的顺序必须按照nameList中的顺序传入，否则会造成数据对应错误
        return ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList, fourToTenSecondsList, threeToTenMinList,
                thirtyMinList, elevenToThirtySecondsList, tenToThirtyMinList, oneToThreeMinList, thirtyFirstToSixtySecList, oneToThreeSecList);
    }

    /**
     * 生成APP使用频率的折线图数据
     *
     * @param dateType 日期类型：day,week,month,quarter,year
     * @param dataTime 数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genAppUseRateCharts(String dateType, List<List<String>> dataTime) {

        // 返回结果
        List<Object> xAxisList = new ArrayList<Object>();
        //定义时长对应的集合用来接收对应的数据
        List<Object> oneToTwqTimes = new ArrayList<Object>();
        List<Object> threeToFiveTimes = new ArrayList<Object>();
        List<Object> sixToNineTimes = new ArrayList<Object>();
        List<Object> tenToNineteenTimes = new ArrayList<Object>();
        List<Object> twentyToFourtyNineTimes = new ArrayList<Object>();
        List<Object> fiftyOverTimes = new ArrayList<Object>();
        //定义图例的name名称
        Object[] nameList = new Object[]{"1-2次", "3-5次", "6-9次", "10-19次", "20-49次", "50次及以上"};

        for (List<String> list : dataTime) {
            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
                int max = max(oneToTwqTimes.size(), threeToFiveTimes.size(), sixToNineTimes.size(), tenToNineteenTimes.size(), twentyToFourtyNineTimes.size(), fiftyOverTimes.size());
                if (oneToTwqTimes.size() < max) {
                    oneToTwqTimes.add(0);
                }
                if (threeToFiveTimes.size() < max) {
                    threeToFiveTimes.add(0);
                }
                if (sixToNineTimes.size() < max) {
                    sixToNineTimes.add(0);
                }
                if (tenToNineteenTimes.size() < max) {
                    tenToNineteenTimes.add(0);
                }
                if (twentyToFourtyNineTimes.size() < max) {
                    twentyToFourtyNineTimes.add(0);
                }
                if (fiftyOverTimes.size() < max) {
                    fiftyOverTimes.add(0);
                }
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
        }
        //生成使用频率的折线图数据
        return ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList,
                oneToTwqTimes, threeToFiveTimes, sixToNineTimes, tenToNineteenTimes, twentyToFourtyNineTimes, fiftyOverTimes);
    }

    /**
     * 生成APP访问深度的折线图数据
     *
     * @param dateType 日期类型：day,week,month,quarter,year
     * @param dataTime 数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genAppDepthCharts(String dateType, List<List<String>> dataTime) {
        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        //定义时长对应的集合用来接收对应的数据
        List<Object> oneToTwoPageList = new ArrayList<Object>();
        List<Object> threeToFivePageList = new ArrayList<Object>();
        List<Object> sixToNinePageList = new ArrayList<Object>();
        List<Object> tenToTwNinePageList = new ArrayList<Object>();
        List<Object> thirtyToMorePageList = new ArrayList<Object>();
        Object[] nameList = new Object[]{"1-2页面", "3-5页面", "6-9页面", "10-29页面", "30个页面及以上"};

        for (List<String> list : dataTime) {

            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
                int max = max(oneToTwoPageList.size(), threeToFivePageList.size(), sixToNinePageList.size(), tenToTwNinePageList.size(), thirtyToMorePageList.size());
                if (oneToTwoPageList.size() < max) {
                    oneToTwoPageList.add(0);
                }
                if (threeToFivePageList.size() < max) {
                    threeToFivePageList.add(0);
                }
                if (sixToNinePageList.size() < max) {
                    sixToNinePageList.add(0);
                }
                if (tenToTwNinePageList.size() < max) {
                    tenToTwNinePageList.add(0);
                }
                if (thirtyToMorePageList.size() < max) {
                    thirtyToMorePageList.add(0);
                }
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

        }
        return ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList, oneToTwoPageList, threeToFivePageList,
                sixToNinePageList, tenToTwNinePageList, thirtyToMorePageList);
    }

    /**
     * 生成APP访问间隔的折线图数据
     *
     * @param dateType 日期类型：day,week,month,quarter,year
     * @param dataTime 数据
     * @return 折线图数据
     */
    @SuppressWarnings("unchecked")
    public static String genAppIntervalCharts(String dateType, List<List<String>> dataTime) {
        // 返回结果
        List<Object> xAxisList = new ArrayList<>();
        List<Object> timeList = new ArrayList<>();
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
        for (List<String> list : dataTime) {
            if (!xAxisList.contains(list.get(0))) {
                xAxisList.add(list.get(0));
                int max = max(firstList.size(), zeroToTwentyFourHours.size(), oneToTwoDays.size(), twoToFourDays.size(), fiveToSevenDay.size(), eightToFourteenDays.size(), fifteenToThirtyDays.size());
                if (firstList.size() < max) {
                    firstList.add(0);
                }
                if (zeroToTwentyFourHours.size() < max) {
                    zeroToTwentyFourHours.add(0);
                }
                if (oneToTwoDays.size() < max) {
                    oneToTwoDays.add(0);
                }
                if (twoToFourDays.size() < max) {
                    twoToFourDays.add(0);
                }
                if (fiveToSevenDay.size() < max) {
                    fiveToSevenDay.add(0);
                }
                if (eightToFourteenDays.size() < max) {
                    eightToFourteenDays.add(0);
                }
                if (fifteenToThirtyDays.size() < max) {
                    fifteenToThirtyDays.add(0);
                }
            }
            if (list.get(1).contains("首次")) {
                firstList.add(list.get(2));
                continue;
            }
            if (list.get(1).contains("0-24h")) {
                zeroToTwentyFourHours.add(list.get(2));
                continue;
            }
            if (list.get(1).contains("1-2天")) {
                oneToTwoDays.add(list.get(2));
                continue;
            }
            if (list.get(1).contains("2-4天")) {
                twoToFourDays.add(list.get(2));
                continue;
            }
            if (list.get(1).contains("5-7天")) {
                fiveToSevenDay.add(list.get(2));
                continue;
            }
            if (list.get(1).contains("8-14天")) {
                eightToFourteenDays.add(list.get(2));
                continue;
            }
            if (list.get(1).contains("15-30天")) {
                fifteenToThirtyDays.add(list.get(2));
            }
        }

        return ChartUtils.genAppMultiLineCharts(dateType, xAxisList, nameList, firstList, zeroToTwentyFourHours,
                oneToTwoDays, twoToFourDays, fiveToSevenDay, eightToFourteenDays, fifteenToThirtyDays);
    }
}
