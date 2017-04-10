package io.transwarp.scrcu.portrait;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import com.jfinal.log.Logger;
import com.jfinal.plugin.ehcache.CacheKit;
import io.transwarp.echarts.data.Data;
import io.transwarp.echarts.style.ItemStyle;
import io.transwarp.echarts.style.itemstyle.Normal;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.ChartUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@RequiresAuthentication
public class PortraitController extends BaseController {

    Logger log = Logger.getLogger(getClass());
    Res res = I18n.use("i18n", "zh_CN");
    public static Map<String, Map<String, Object>> allUserTagMap = new HashMap<String, Map<String, Object>>();
    public static Map<String, Map<String, Object>> allTagMap = new HashMap<>();

    /**
     * 用户画像标签首页
     */
    @SuppressWarnings("unchecked")
    public void home() {

        if (BaseUtils.isAjax(getRequest())) {
            List<Object> xAxisList = new ArrayList<Object>();
            //获取具有标签用户的数量
            List<Map<String, Object>> count = InceptorUtil
                    .mapQuery(SqlKit.propSQL(SQLConfig.label_colony_users) + getLevelCondition(), true);
            int allCount = 0;
            //TODO 手动增加了if else 判断，如果此处逻辑错误，则使用上面注释掉的原代码
            if (count.size() > 0) {
                allCount = Integer.valueOf(count.get(0).get("allusers").toString());
            } else {
                allCount = 3200;
            }
            JSONObject result = new JSONObject();
            //获取用户标签列表数据
            List<Map<String, Object>> tagList = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.label_label_grouping)
                    + getLevelCondition() + " group by topic,label_code order by total desc", true);
            final Map<String, List<Map<String, Object>>> tagMap = new HashMap<String, List<Map<String, Object>>>();
            for (Map<String, Object> map : tagList) {
                String key = ((String) map.get("topic")).toLowerCase();
                allUserTagMap.put((String) map.get("label_only"), map);
                if (tagMap.get(key) == null) {
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    tagMap.put(key, list);
                }
                tagMap.get(key).add(map);
            }

            // 职业
            List<Map<String, Object>> jobs = tagMap.get("job");
            List<Object> jobList = new ArrayList<Object>();
            if (jobs != null) {
                for (Map<String, Object> m : jobs) {
                    Data data = new Data(m.get("label_desc").toString().split("（")[0], m.get("total"));
                    jobList.add(data);
                }
            }
            result.put("job", ChartUtils.genPie(res.get("portrait.career"), jobList));

            // 交易类型
            List<Map<String, Object>> trades = tagMap.get("trade_prefer");
            List<Object> tradeList = new ArrayList<Object>();
            if (trades != null) {
                for (Map<String, Object> m : trades) {
                    Data data = new Data(m.get("label_desc").toString(), m.get("total"));
                    tradeList.add(data);
                }
            }
            result.put("trade", ChartUtils.genPie(res.get("portrait.transactionType"), tradeList));

            // 群体类型
            List<Map<String, Object>> colony = tagMap.get("colony");
            List<Object> colonyList = new ArrayList<Object>();
            if (colony != null) {
                for (Map<String, Object> m : colony) {
                    Data data = new Data(m.get("label_desc").toString(), m.get("total"));
                    colonyList.add(data);
                }
            }
            result.put("colony", ChartUtils.genPie(res.get("portrait.groupType"), colonyList));

            // 注册年限
            List<Map<String, Object>> regYears = tagMap.get("reg_year");
            List<String> keyList = new ArrayList<String>();
            List<Integer> regYearList = new ArrayList<Integer>();
            if (regYears != null) {
                for (Map<String, Object> m : regYears) {
                    String[][] s = new String[][]{m.get("label_desc").toString().split("（")};
                    if (s[0].length > 1) {
                        keyList.add(s[0][0] + "\n" + "（" + s[0][1]);
                    } else {
                        keyList.add(s[0][0]);
                    }
                    regYearList.add(InceptorUtil.getInt("total", m));
                }
            }
            result.put("regYear", ChartUtils.genRadar(res.get("portrait.registrationPeriod"), keyList, regYearList));

            // 使用时段
            List<Map<String, Object>> useTimes = tagMap.get("web_use_time");
            List<Object> timeList = new ArrayList<Object>();
            xAxisList = new ArrayList<Object>();
            if (useTimes != null) {
                for (Map<String, Object> m : useTimes) {
                    xAxisList.add(m.get("label_desc").toString());
                    timeList.add(InceptorUtil.getInt("total", m));
                }
            }
            result.put("useTime", ChartUtils.genBar(res.get("portrait.usePeriod"), res.get("portrait.usePeriod"), xAxisList, timeList));

            // 搜索引擎
            List<Map<String, Object>> search = tagMap.get("searchengine");
            List<Object> searchList = new ArrayList<Object>();
            if (search != null) {
                for (Map<String, Object> m : search) {
                    Data data = new Data(m.get("label_desc").toString(), m.get("total"));
                    searchList.add(data);
                }
            }
            result.put("search", ChartUtils.genPie(res.get("portrait.searchEngine"), searchList));

            // 用户卡类别
            List<Map<String, Object>> cards = tagMap.get("card_type");
            List<Object> cardList = new ArrayList<Object>();
            if (cards != null) {
                for (Map<String, Object> m : cards) {
                    Data data = new Data(m.get("label_desc").toString(), m.get("total"));
                    cardList.add(data);
                }
            }
            result.put("cardType", ChartUtils.genPie(res.get("portrait.userCardCategory"), cardList));

            // 年代
            List<Map<String, Object>> generations = tagMap.get("generation");
            xAxisList = new ArrayList<Object>();
            List<Object> generationList = new ArrayList<Object>();
            if (generations != null) {
                for (Map<String, Object> m : generations) {
                    if (m.get("label_desc") != null) {
                        xAxisList.add(m.get("label_desc").toString());
                        generationList.add(InceptorUtil.getInt("total", m));
                    }
                }
            }
            result.put("generation", ChartUtils.genBar(res.get("portrait.decade"), res.get("portrait.decade"), xAxisList, generationList));

            // 教育程度
            List<Map<String, Object>> eduBgs = tagMap.get("edu_bg");
            List<Object> eduList = new ArrayList<Object>();
            if (eduBgs != null) {
                for (Map<String, Object> m : eduBgs) {
                    Data data = new Data(m.get("label_desc").toString(), m.get("total"));
                    eduList.add(data);
                }
            }
            result.put("eduBg", ChartUtils.genPie(res.get("portrait.educationLevel"), eduList));

            // 性别
            List<Map<String, Object>> sex = tagMap.get("sex");
            List<Object> sexList = new ArrayList<Object>();
            if (sex != null) {
                for (Map<String, Object> m : sex) {
                    Data data = new Data(m.get("label_desc").toString(), m.get("total"));
                    sexList.add(data);
                }
            }
            result.put("sex", ChartUtils.genPie(res.get("portrait.gender"), sexList));

            // 终端
            List<Map<String, Object>> terminal = tagMap.get("terminal");
            List<Object> terminalList = new ArrayList<Object>();
            if (terminal != null) {
                for (Map<String, Object> m : terminal) {
                    Data data = new Data(m.get("label_desc").toString(), m.get("total"));
                    terminalList.add(data);
                }
            }
            result.put("terminal", ChartUtils.genPie(res.get("portrait.terminal"), terminalList));

            //关键字
//            List<Map<String, Object>> keyWord = tagMap.get("keyWord");
            List<Map<String, Object>> keyWords = new ArrayList<>();
            Map<String, Object> maps = new HashMap<>();
            String[][] kv = new String[][]{
                    {"total", "4234"},{"topic", "keyWord"},{"label_desc", "个人登录"},{"label_only", "keyWord_k1"},{"topic_desc", "关键字"},{"label_code", "k1"},
                    {"total", "3234"},{"topic", "keyWord"},{"label_desc", "货币兑换"},{"label_only", "keyWord_k2"},{"topic_desc", "关键字"},{"label_code", "k2"},
                    {"total", "2234"},{"topic", "keyWord"},{"label_desc", "跨行转账"},{"label_only", "keyWord_k3"},{"topic_desc", "关键字"},{"label_code", "k3"},
                    {"total", "1532"},{"topic", "keyWord"},{"label_desc", "网银激活"},{"label_only", "keyWord_k4"},{"topic_desc", "关键字"},{"label_code", "k4"},
                    {"total", "1234"},{"topic", "keyWord"},{"label_desc", "K宝"},{"label_only", "keyWord_k5"},{"topic_desc", "关键字"},{"label_code", "k5"},
                    {"total", "956"},{"topic", "keyWord"},{"label_desc", "手续费"},{"label_only", "keyWord_k6"},{"topic_desc", "关键字"},{"label_code", "k6"},
                    {"total", "898"},{"topic", "keyWord"},{"label_desc", "安全"},{"label_only", "keyWord_k7"},{"topic_desc", "关键字"},{"label_code", "k7"},
                    {"total", "654"},{"topic", "keyWord"},{"label_desc", "开户申请"},{"label_only", "keyWord_k8"},{"topic_desc", "关键字"},{"label_code", "k8"},
                    {"total", "562"},{"topic", "keyWord"},{"label_desc", "挂失"},{"label_only", "keyWord_k9"},{"topic_desc", "关键字"},{"label_code", "k9"},
                    {"total", "354"},{"topic", "keyWord"},{"label_desc", "补卡"},{"label_only", "keyWord_k10"},{"topic_desc", "关键字"},{"label_code", "k10"},
                    {"total", "221"},{"topic", "keyWord"},{"label_desc", "销户"},{"label_only", "keyWord_k11"},{"topic_desc", "关键字"},{"label_code", "k11"},
            };
            for (int i = 0; i < kv.length; i++){
                maps.put(kv[i][0], kv[i][1]);
                if ((i+1)%6 == 0){
                    keyWords.add(maps);
                    maps = new HashMap<>();
                    maps.clear();
                }
            }

            List<Object> keyWordList = new ArrayList<>();
            if (keyWords != null) {
                for (Map<String, Object> m : keyWords) {
                    ItemStyle itemStyle = new ItemStyle();
                    Normal normal = new Normal();
                    String color = "rgb(" + Math.round(Math.random() * 160) + "," + Math.round(Math.random() * 160) + "," + Math.round(Math.random() * 160) + ")";
                    normal.setColor(color);
                    itemStyle.normal(normal);
                    Data data = new Data(m.get("label_desc").toString(), m.get("total"), itemStyle);
                    keyWordList.add(data);
                }
            }
            result.put("keyWord", ChartUtils.genWordCloud(res.get("portrait.keyWord"), keyWordList));

            Map<String, List<Map<String, Object>>> rateMap = new HashMap<String, List<Map<String, Object>>>();
            List<String> types = new ArrayList<String>();
            types.add("inn_org_zone_cd");
            types.add("pay_transfer_channel");
            types.add("security_type");
            types.add("user_cert_level");
            types.add("loan_purpose");
            types.add("ass_mode");
            for (Map<String, Object> map : tagList) {
                String s = (map.get("topic").toString()).toLowerCase();
                if (types.contains(s)) {
                    String key = map.get("topic_desc").toString();
                    if (rateMap.get(key) == null) {
                        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                        rateMap.put(key, list);
                    }
                    if (rateMap.get(key).size() < 3) {
                        double c = Double.valueOf(map.get("total").toString());
                        double d = c / allCount * 100;
                        BigDecimal b = new BigDecimal(d);
                        double r = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        map.put("rate", r);
                        rateMap.get(key).add(map);
                    }
                }
            }
            result.put("rateMap", rateMap);
            renderJson(result);
        }
    }

    /**
     * 用户明细
     */
    public void groupUserList() {
        keepPara("code", "total_assets_from", "total_assets_to", "total_debt_from", "total_debt_to");

        String code = getPara("code");
        String total_assets_from = getPara("total_assets_from");
        String total_assets_to = getPara("total_assets_to");
        String total_debt_from = getPara("total_debt_from");
        String total_debt_to = getPara("total_debt_to");

        if (StringUtils.isNotBlank(code)) {
            String[] codes = code.substring(0, code.length() - 1).split(",");
            List<Map<String, Object>> tagList = new ArrayList<Map<String, Object>>();
            for (String c : codes) {
                tagList.add(allUserTagMap.get(c));
            }
            setAttr("tagList", tagList);
        }
        setAttr("total_assets_from", total_assets_from);
        setAttr("total_assets_to", total_assets_to);
        setAttr("total_debt_from", total_debt_from);
        setAttr("total_debt_to", total_debt_to);

    }

    /**
     * 根据条件检索用户
     */
    public void userSearch() {
        if (BaseUtils.isAjax(getRequest())) {
            StringBuffer condition = new StringBuffer();
            // 条件
            String query = getRequest().getParameter("data");
            if (StringUtils.isNotBlank(query)) {
                condition.append(" and " + query);
            }
            condition.append(" limit 1000");
            // 执行查询
            List<List<String>> data = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.users) + getLevelCondition() + condition, 20, false);

            for (List<String> list : data) {
                list.add("<a class='btn btn-rounded btn-sm btn-icon btn-primary' href='/portrait/userPortrait?userid="
                        + list.get(0) + "'><i class='fa fa-user'></i></a>");
            }
            // 返回结果
            JSONObject result = new JSONObject();
            result.put("data", data);
            renderJson(result);
        }
    }

    /**
     * 获取用户等级
     *
     * @return
     */
    public String getLevelCondition() {
        return " where inn_org_lvl_cd >= " + getSessionAttr("banklevel");
    }


    /**
     * 获取标签墙列表
     */
    @RequiresPermissions("/portrait/tags")
    public void tags() throws Exception {
        if (BaseUtils.isAjax(getRequest())) {
            List<Map<String, Object>> allTagList = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.label_label_wall)
                    + getLevelCondition() + " group by topic,label_code order by total desc ", true);
            final Map<String, List<Map<String, Object>>> tagMap = new TreeMap<String, List<Map<String, Object>>>();
            for (Map<String, Object> map : allTagList) {
                String key = map.get("topic_desc").toString();
                allTagMap.put((String) map.get("label_only"), map);
                if (key != null) {
                    if (tagMap.get(key) == null) {
                        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                        tagMap.put(key, list);
                    }
                    tagMap.get(key).add(map);
                }
            }
            Map<String, List<Map<String, Object>>> m = new TreeMap<String, List<Map<String, Object>>>(
                    new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            if (tagMap.get(o1).size() > tagMap.get(o2).size()) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                    });
            for (String key : tagMap.keySet()) {
                m.put(key, tagMap.get(key));
            }
            renderJson("tagMap", m);
        }
    }

    /**
     * 获取检索标签的列表
     */
    @RequiresPermissions("/portrait/groupTagList")
    public void groupTagList() {
        List<List<Tag>> groupList = CacheKit.get("inceptor", "groupList");
        if (groupList == null) {
            groupList = new ArrayList<List<Tag>>();
            CacheKit.put("inceptor", "groupList", groupList);
        }
        /* 标签检索列表历史去重
        for (int i = 0; i < groupList.size() - 1; i++) {
            for (int j = groupList.size() - 1; j > i; j--) {
                if (groupList.get(j).equals(groupList.get(i))) {
                    groupList.remove(j);
                }
            }
        }
        */
        setAttr("groupList", groupList);
    }

    /**
     * 根据标签检索用户信息
     */
    @RequiresPermissions("/portrait/groupTagList")
    public void groupTags() {
        if (BaseUtils.isAjax(getRequest())) {
            List<Map<String, Object>> allTagList = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.label_label_wall)
                    + " group by topic,label_code order by topic_desc desc ", true);
            List<Map<String, Object>> tagList = new ArrayList<Map<String, Object>>();
            String[] codes = new String[]{};
            List<Object> keys = new LinkedList<Object>();
            List<Object> values = new LinkedList<Object>();
            StringBuffer condition = new StringBuffer(getPara("condition"));
            String code = getPara("code");
            if (StringUtils.isNotBlank(code)) {
                codes = code.substring(0, code.length() - 1).split(",");
                for (String c : codes) {
                    if (allUserTagMap.get(c) != null) {
                        tagList.add(allUserTagMap.get(c));
                    }else {
                        for (Map<String, Object> map : allTagList){
                            if (map.get("label_only").equals(c)){
                                keys.add(map.get("label_desc"));
                            }
                        }
                    }
                }
            }

            // #################是否具有标签占比######################
            List<Map<String, Object>> count = InceptorUtil.mapQuery(
                    SqlKit.propSQL(SQLConfig.label_colony_users) + getLevelCondition() + condition.toString(), false);
            int have = 0;
            if (count.size() > 0) {
                have = Integer.valueOf(count.get(0).get("allusers").toString());
            }

            count = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.label_colony_users) + getLevelCondition(), false);
            int allcount = 0;
            Double val = null;
            if (count.size() > 0) {
                allcount = Integer.valueOf(count.get(0).get("allusers").toString());
                val = (double) have / allcount;
            }

            JSONObject result = new JSONObject();
            result.put("val", new BigDecimal(val * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            // 是否具有标签占比
            // 总数
            if (keys.size() != 0){
                for (int i = 0; i < keys.size(); i++){
                    values.add("0");
                }
            }
            for (Map map : tagList) {
                if (map.get("topic").equals("job")) {
                    keys.add(map.get("label_desc").toString().split("（")[0]);
                } else {
                    keys.add(map.get("label_desc"));
                }
                float v = (Float.valueOf(map.get("total").toString()) / allcount) * 100;
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                values.add(decimalFormat.format(v));
            }
            result.put("percent", keys.size());
            result.put("tagsRankOption", ChartUtils.genPercentBar(res.get("portrait.groupLabelAccounting"), res.get("portrait.groupLabelAccounting"), keys, values));

            // 标签占比
            String sql = "select max(topic) as topic,max(topic_desc) as topic_desc,max(label_only) as label_only,"
                    + "min(label_desc) as label_desc,count(*) as total "
                    + "from(select a1.user_id,a2.topic,a2.topic_desc,a2.label_code,a2.label_desc,a2.label_only "
                    + "from (select user_id from bdlbl.tl_label_user_result " + getLevelCondition() + condition.toString()
                    + " ) as a1 "
                    + "left join bdlbl.tl_label_all_summary as a2 on a1.user_id=a2.user_id) group by label_only,label_desc having label_desc<>'其它' and label_desc<>'未知' order by total desc;";
            List<Map<String, Object>> tags = InceptorUtil.mapQuery(sql, true);
            result.put("pie", tags.size());
            if (tags != null && !tags.isEmpty()) {
                result.put("word", genString(tags, codes, have, allcount));

                // 群体主要标签start
                // 标签比例
                Map<String, List<Map<String, Object>>> tagMap = new HashMap<String, List<Map<String, Object>>>();
                List<String> types = new ArrayList<String>();
                types.add("colony");
                types.add("pay_transfer_channel");
                types.add("security_type");
                types.add("user_cert_level");
                types.add("loan_purpose");
                types.add("ass_mode");
                for (Map<String, Object> map : tags) {
                    String s = (map.get("topic").toString()).toLowerCase();
                    if (types.contains(s)) {
                        String key = map.get("topic_desc").toString();
                        if (tagMap.get(key) == null) {
                            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                            tagMap.put(key, list);
                        }
                        if (tagMap.get(key).size() < 3) {
                            double c = Double.valueOf(map.get("total").toString());
                            double d = c / allcount * 100;
                            BigDecimal b = new BigDecimal(d);
                            double r = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            map.put("rate", r);
                            tagMap.get(key).add(map);
                        }
                    }
                }
                result.put("groupTagsRate", tagMap);
            }
            renderJson(result);

        } else {
            StringBuffer condition = new StringBuffer("");
            String code = getPara("code");
            String total_assets_from = getPara("total_assets_from");
            String total_assets_to = getPara("total_assets_to");
            String total_debt_from = getPara("total_debt_from");
            String total_debt_to = getPara("total_debt_to");
            if (StringUtils.isNotBlank(total_assets_from) && NumberUtils.isNumber(total_assets_from)) {
                condition.append(" and cast((trim(cast(total_assets as string))) AS decimal(17,2)) >= " + total_assets_from);
            }
            if (StringUtils.isNotBlank(total_assets_to) && NumberUtils.isNumber(total_assets_from)) {
                condition.append(" and cast((trim(cast(total_assets as string))) AS decimal(17,2)) <= " + total_assets_to);
            }
            if (StringUtils.isNotBlank(total_debt_from) && NumberUtils.isNumber(total_assets_from)) {
                condition.append(" and cast((trim(cast(total_debt as string))) AS decimal(17,2)) >= " + total_debt_from);
            }
            if (StringUtils.isNotBlank(total_debt_to) && NumberUtils.isNumber(total_assets_from)) {
                condition.append(" and cast((trim(cast(total_debt as string))) AS decimal(17,2)) <= " + total_debt_to);
            }
            keepPara("code", "total_assets_from", "total_assets_to", "total_debt_from", "total_debt_to");
            if (StringUtils.isNotBlank(code)) {
                String[] codes = code.substring(0, code.length() - 1).split(",");
                List<Map<String, Object>> tagList = new ArrayList<Map<String, Object>>();
                for (String c : codes) {
                    tagList.add(allUserTagMap.get(c));
                    condition.append(" and " + c + " = 1");
                }
                List<List<Map<String, Object>>> groupList = CacheKit.get("inceptor", "groupList");
                if (groupList == null) {
                    groupList = new ArrayList<List<Map<String, Object>>>();
                    CacheKit.put("inceptor", "groupList", groupList);
                }

                groupList.add(tagList);
                setAttr("tagList", tagList);
            }
            setAttr("condition", condition.toString());
        }
    }

    /**
     * 返回检索标签结果的简单描述
     *
     * @param tags
     * @param codes
     * @param have
     * @param count
     * @return
     */
    public String genString(List<Map<String, Object>> tags, String[] codes, int have, int count) {

        Map<String, Map<String, Object>> tagMap = new HashMap<String, Map<String, Object>>();
        List<String> types = new ArrayList<String>();
        types.add("prod_func_consum_prefer");   //产品功能消费偏好
        types.add("generation");        //年代
        types.add("sex");               //性别
        types.add("inn_org_zone_desc");   //注册地区
        types.add("reg_year");           //开户时间
        types.add("industry");          //单位所属行业
        types.add("edu_bg");            //教育程度
        types.add("card_type");          //用户卡类别
        types.add("route_desc");         //汇路时效
        StringBuffer sb = new StringBuffer();
        sb.append("同时具有");
        String s = "";
        for (Map<String, Object> map : tags) {
            String key = (map.get("topic").toString()).toLowerCase();
            if (types.contains(key)) {
                if (tagMap.get(key) == null) {
                    tagMap.put(key, map);
                } else {
                    int t = Integer.valueOf(tagMap.get(key).get("total").toString());
                    int t2 = Integer.valueOf(map.get("total").toString());
                    if (t < t2) {
                        tagMap.put(key, map);
                    }
                }
                tagMap.get(key).put("label_desc", "<font class='font-bold text-success'>" + tagMap.get(key).get("label_desc") + "</font>");
            }

            for (String str : codes) {
                if (map.get("label_only").equals(str)) {
                    s = s + map.get("label_desc") + "、";
                }
            }

        }
        if (StringUtils.isNotBlank(s)) {
            sb.append("<font class='font-bold text-success'>" + s.substring(0, s.lastIndexOf("、")) + "</font>");
        }
        sb.append("标签的用户共").append(have).append("人，占所有用户数的").append(new BigDecimal((double) have / count * 100)
                .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()).append("%，");

        sb.append("这群人主要以").append("<font class='font-bold text-success'>" + tags.get(0).get("label_desc") + "</font>").append("为主，占比")
                .append(new BigDecimal((Double.valueOf(tags.get(0).get("total").toString())) / count * 100)
                        .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())
                .append("%，");
        if (tagMap.get("prod_func_consum_prefer") != null)
            sb.append("产品功能消费偏好为").append(tagMap.get("prod_func_consum_prefer").get("label_desc") + ",");

        if (tagMap.get("generation") != null) {
            sb.append("从用户构成来看，主要以").append(tagMap.get("generation").get("label_desc"));
            if (tagMap.get("sex") != null) {
                sb.append("的").append(tagMap.get("sex").get("label_desc"));
            }
            sb.append("性为主，");
        }

        if (tagMap.get("inn_org_zone_cd") != null) {
            sb.append("集中在").append(tagMap.get("inn_org_zone_cd").get("label_desc")).append("一带，");
        }

        if (tagMap.get("reg_year") != null) {
            sb.append("大部分人的注册年限偏向于").append(tagMap.get("reg_year").get("label_desc"));
        }

        if (tagMap.get("industry") != null) {
            sb.append("，这些人主要").append(tagMap.get("industry").get("label_desc")).append("，");
        }

        if (tagMap.get("edu_bg") != null) {
            sb.append("且具有").append(tagMap.get("edu_bg").get("label_desc")).append("的学历，");
        }

        if (tagMap.get("card_type") != null) {
            sb.append("用户卡类别").append(tagMap.get("card_type").get("label_desc")).append("。");
        }
        if (tagMap.get("route_desc") != null) {
            sb.append("他们具有").append(tagMap.get("route_desc").get("label_desc")).append("的偏好。");
            return sb.toString();
        } else {
            String str = sb.toString();
            str = str.substring(0, str.length() - 1) + "。";
            return str;
        }

    }

    /**
     * 个人画像查询
     */
    @RequiresPermissions("/portrait/userPortrait")
    public void userPortrait() {
        JSONObject result = new JSONObject();
        String userid = getPara("userid");
        Map<String, Object> user = null;
        if (StringUtils.isBlank(userid)) {
            redirect("/portrait/groupUserList");
        } else {
            List<Map<String, Object>> userLable = InceptorUtil
                    .mapQuery(SqlKit.propSQL(SQLConfig.portal_userLabel.toString()) + getLevelCondition()
                            + " and  user_id ='" + userid + "'", false);
            if (userLable != null && userLable.size() > 0) {
                user = userLable.get(0);
            } else {
                redirect("/portrait/groupUserList");
            }
        }
        if (user != null && BaseUtils.isAjax(getRequest())) {
            result.put("userTags", user);
            // 调用交易偏好
            tradepreference(userid, result);
            // 调用人际关系
            relation(userid, result);
            renderJson(result);

        }

    }

    /**
     * 交易偏好方法
     */
    public void tradepreference(String userid, JSONObject result) {
        // 查询交易历史表,获取本月交易次数/交易金额总和
        List<Map<String, Object>> data = InceptorUtil.mapQuery(
                SqlKit.propSQL(SQLConfig.portrait_trading.toString()) + " where user_id= '" + userid + "'", true);
        List<Object> xAxisListCount = new ArrayList<Object>();
        List<Object> xAxisList = new ArrayList<Object>();
        // 雷达图计算各类型次数占比
        // 获取总次数与各个类型次数
        Map<String, String> focusmap = new HashMap<String, String>();
        focusmap.put("trans_dept", res.get("portrait.deposit"));
        focusmap.put("trans_fin", res.get("portrait.financialManagement"));
        focusmap.put("trans_loan", res.get("portrait.loan"));
        focusmap.put("trans_addservice", res.get("portrait.valueAddService"));
        focusmap.put("trans_transfer", res.get("portrait.transfer"));
        Map<String, Integer> radarMap = new HashMap<String, Integer>();
        radarMap.put(res.get("portrait.deposit"), 0);
        radarMap.put(res.get("portrait.financialManagement"), 0);
        radarMap.put(res.get("portrait.loan"), 0);
        radarMap.put(res.get("portrait.valueAddService"), 0);
        radarMap.put(res.get("portrait.transfer"), 0);
        Map<String, Integer> moneyMap = new HashMap<String, Integer>();
        moneyMap.put(res.get("portrait.deposit"), 0);
        moneyMap.put(res.get("portrait.financialManagement"), 0);
        moneyMap.put(res.get("portrait.loan"), 0);
        moneyMap.put(res.get("portrait.valueAddService"), 0);
        moneyMap.put(res.get("portrait.transfer"), 0);
        int tradeCount = 0;
        int tradeMoney = 0;
        int maxCountType = 0;
        String maxCountTypeDesc = "";
        int maxMoneyType = 0;
        String maxMoneyTypeDesc = "";

        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                Map ration = data.get(i);
                String value = ration.get("trade_type").toString();
                if (focusmap.containsValue(value)) {
                    int num = Double.valueOf(ration.get("trade_thirty_count").toString()).intValue();
                    int m = Double.valueOf(ration.get("trade_thirty_money").toString()).intValue();
                    xAxisListCount.add(num);
                    xAxisList.add(value);
                    radarMap.put(value, num);
                    moneyMap.put(value, m);
                    tradeCount += num;
                    tradeMoney += m;
                    if (num > maxCountType) {
                        maxCountType = num;
                        maxCountTypeDesc = value;
                    }
                    if (m > maxMoneyType) {
                        maxMoneyType = m;
                        maxMoneyTypeDesc = value;
                    }

                }
            }
        }
        // 月交易次数图
        result.put("chartOption", ChartUtils.genBar(res.get("portrait.monthlyTransactions"), res.get("portrait.monthlyTransactions"), xAxisList, xAxisListCount));
        // 用户关注度图
        result.put("radar1", ChartUtils.genRadar(res.get("portrait.userAttention"), radarMap));
        // 月交易金额图
        result.put("moneyData", ChartUtils.genPortraitPie(res.get("portrait.userTransactionAmountDistribution"), moneyMap));
        // 交易总额和交易总次数
        result.put("tradeMoney", tradeMoney);
        result.put("tradeCount", tradeCount);
        // 金额和次数倾向
        result.put("maxCountTypeDesc", maxCountTypeDesc);
        result.put("maxMoneyTypeDesc", maxMoneyTypeDesc);

    }

    /**
     * 人际关系
     */
    public void relation(String userid, JSONObject result) {
        // 查询人员支出
        List<Map<String, Object>> dataPay = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.portrait_relation.toString())
                + " where user_id= '" + userid + "' or receive_id= '" + userid + "'", true);
        // 查询人员收入
        List<Object> pay = new ArrayList<Object>();
        List<Object> receive = new ArrayList<Object>();
        String name = "";
        if (dataPay != null) {
            for (int i = 0; i < dataPay.size(); i++) {
                Map para = dataPay.get(i);
                if (userid.equals(para.get("user_id").toString())) {
                    pay.add(para.get("receive_name"));
                    name = para.get("pay_name").toString();
                } else if (userid.equals(para.get("receive_id").toString())) {
                    receive.add(para.get("pay_name"));
                }
            }
        }
        result.put("relation", ChartUtils.getForce(name, pay, receive));
        // 类别信息
        List<Map<String, Object>> datatransfer = InceptorUtil.mapQuery(
                SqlKit.propSQL(SQLConfig.portrait_relation_group.toString()) + " where user_id= '" + userid + "'",
                true);
        if (datatransfer != null) {
            for (int i = 0; i < datatransfer.size(); i++) {
                Map para = datatransfer.get(i);
                result.put("hotName", para.get("closed_name"));
                result.put("relationname", para.get("recent_trans_name"));
                result.put("relationtime", para.get("recent_trans_time"));
            }
        }
    }

}
