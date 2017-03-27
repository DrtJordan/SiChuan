package io.transwarp.scrcu.tag;

import com.alibaba.fastjson.JSONObject;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.ConditionUtil;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by TANG_0722 on 2017-02-23.
 */
@RequiresAuthentication
public class TagController extends BaseController {

    //调用SQL，循环执行插入语句。
    public void rangToRangCommon(Object config) {
        String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms").format(new Date());
        String operUser = getPara("oper_user");
        String[] keys = getParaValues("key");
        for (int i = 0; i < keys.length; i++) {
            InceptorUtil.mapQuery(SqlKit.propSQL(config, ConditionUtil.rangToRang(keys[i], getPara("name" + i), getPara("start" + i), getPara("end" + i), operTime, operUser).toString()), false);
        }
    }

    public void checkCommon(Object config) {
        String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms").format(new Date());
        String operUser = getPara("oper_user");
        String[] names = getParaValues("names");
        StringBuffer contentName = new StringBuffer("");
        for (int i = 0; i < names.length; i++) {
            String[] checked = getParaValues(names[i]);
            if (checked != null) {
                for (int j = 0; j < checked.length; j++) {
                    contentName.append(checked[j]).append("、");
                }
                contentName.delete(contentName.length() - 1, contentName.length());
            }
            InceptorUtil.mapQuery(SqlKit.propSQL(config, ConditionUtil.check(getPara("key" + i), names[i], contentName.toString(), operTime, operUser).toString()), false);
            contentName.delete(0, contentName.length());
        }
    }

    public void selectCommon(Object config) {
        String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms").format(new Date());
        String operUser = getPara("oper_user");
        String[] contents = getParaValues("content_name");
        StringBuffer contentName = new StringBuffer("");
        for (int i = 0; i < contents.length; i++) {
            contentName.append(contents[i]).append("、");
        }
        contentName.delete(contentName.length() - 1, contentName.length());
        InceptorUtil.mapQuery(SqlKit.propSQL(config, ConditionUtil.select(getPara("key"), getPara("rank"), contentName.toString(), operTime, operUser).toString()), false);
    }

    //可配置标签列表页----start
    @RequiresPermissions("/tag")
    public void index() {
        List<String[][]> tradFeas = new ArrayList<>();
        List<String[][]> peoAttrs = new ArrayList<>();
        List<String[][]> creAttrs = new ArrayList<>();
        List<String[][]> opeAttrs = new ArrayList<>();
        String[][] tradFea = {{"客户参与活动", "partActive"}, {"月交易次数", "tranNum"}, {"时间偏好", "timeHobby"}, {"支付偏好", "payHobby"}, {"行内外转账", "bankTran"}, {"转账类型偏好", "tranType"}, {"用户群体类型", "groupType"}, {"持有产品服务", "prodServer"}, {"产品功能消费偏好", "customHobby"}, {"月交易金额", "tranMoney"}, {"安全认证方式", "secAuth"}, {"汇路时效", "sendEff"}, {"交易偏好", "tranHobby"}, {"存款产品偏好", "prodHobby"}};
        String[][] peoAttr = {{"年龄", "age"}, {"职业", "career"}, {"社交关系", "socRelation"}};
        String[][] creAttr = {{"蜀信e注册年限", "regYear"}, {"资产负债", "proDebt"}};
        String[][] opeAttr = {{"网页搜索操作行为", "searBehaviour"}, {"搜索引擎", "searchEng"}, {"活跃变化特征", "chaFeature"}, {"网银搜索关键字", "searchKey"}, {"网页投诉操作行为", "compBehaviour"}, {"网页留言操作行为", "noteBehaviour"}, {"网页浏览操作行为", "eventBehaviour"}, {"使用时段", "useTime"}, {"终端", "terminal"}, {"活跃功能特征", "funcFeature"}};
        tradFeas.add(tradFea);
        peoAttrs.add(peoAttr);
        creAttrs.add(creAttr);
        opeAttrs.add(opeAttr);
        setAttr("tradFeas", tradFeas.get(0));
        setAttr("peoAttrs", peoAttrs.get(0));
        setAttr("creAttrs", creAttrs.get(0));
        setAttr("opeAttrs", opeAttrs.get(0));
    }
    //可配置标签列表页----end

    //年龄----start
    @RequiresPermissions("/tag/age")
    public void age() {
        List<Map<String, Object>> ages = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.age_label), false);
        setAttr("ages", ages);
    }

    @RequiresPermissions("/tag/ageConfig")
    public void ageConfig() {
        rangToRangCommon(SQLConfig.age_label_config);
        redirect("/tag");
    }
    //年龄----end

    //职业----start
    @RequiresPermissions("/tag/career")
    public void career() {
        List<Map<String, Object>> jobs = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.job_label), false);
        List<String> contents = new ArrayList<>();
        String[] checked = {"军人", "其他", "未知", "国家机关", "党群组织", "企业事业单位", "专业技术人员", "商业服务业人员", "办事人员和有关人员", "农林牧渔水利业生产人员", "生产运输设备操作人员及有关人员"};
        for (int i = 0; i < checked.length; i++) {
            contents.add(checked[i]);
        }
        setAttr("contents", contents);
        setAttr("jobs", jobs);
    }

    @RequiresPermissions("/tag/careerConfig")
    public void careerConfig() {
        checkCommon(SQLConfig.job_label_config);
        redirect("/tag");
    }
    //职业----end

    //蜀信e注册年限----start
    @RequiresPermissions("/tag/regYear")
    public void regYear() {
        List<Map<String, Object>> regYears = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.reg_year_label), false);
        setAttr("regYears", regYears);
    }

    @RequiresPermissions("/tag/regYearConfig")
    public void regYearConfig() {
        rangToRangCommon(SQLConfig.reg_year_label_config);
        redirect("/tag");
    }
    //蜀信e注册年限----end

    //资产负债----start
    @RequiresPermissions("/tag/proDebt")
    public void proDebt() {
        List<Map<String, Object>> proDebts = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.pro_debt_label), false);
        setAttr("proDebts", proDebts);
    }

    @RequiresPermissions("/tag/proDebtConfig")
    public void proDebtConfig() {
        rangToRangCommon(SQLConfig.pro_debt_label_config);
        redirect("/tag");
    }
    //资产负债----end

    //持有产品服务----start
    @RequiresPermissions("/tag/prodServer")
    public void prodServer() {
        List<Map<String, Object>> prodServers = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.prod_server_label), false);
        setAttr("prodServers", prodServers);
    }

    @RequiresPermissions("/tag/prodServerConfig")
    public void prodServerConfig() {
        rangToRangCommon(SQLConfig.prod_server_label_config);
        redirect("/tag");
    }
    //持有产品服务----end

    //客户参与活动----start
    @RequiresPermissions("/tag/partActive")
    public void partActive() {
        List<Map<String, Object>> partActives = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.part_active_label), false);
        setAttr("partActives", partActives);
    }

    @RequiresPermissions("/tag/partActiveConfig")
    public void partActiveConfig() {
        rangToRangCommon(SQLConfig.part_active_label_config);
        redirect("/tag");
    }
    //客户参与活动----end

    //产品功能消费偏好----start
    @RequiresPermissions("/tag/customHobby")
    public void customHobby() {
        List<Map<String, Object>> customHobbys = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.custom_hobby_label), false);
        String[] select = {"红包", "AA", "游戏爱好者", "彩票达人", "车票达人", "机票达人", "电信缴费", "电力缴费", "水费缴费", "燃气缴费", "签到达人", "收藏达人"};
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < select.length; i++) {
            contents.add(select[i]);
        }
        setAttr("contents", contents);
        setAttr("customHobbys", customHobbys);
    }

    @RequiresPermissions("/tag/customHobbyConfig")
    public void customHobbyConfig() {
        selectCommon(SQLConfig.custom_hobby_label_config);
        redirect("/tag");
    }
    //产品功能消费偏好----end

    //月交易次数----start
    @RequiresPermissions("/tag/tranNum")
    public void tranNum() {
        List<Map<String, Object>> tranNums = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.tran_num_label), false);
        setAttr("tranNums", tranNums);
    }

    @RequiresPermissions("/tag/tranNumConfig")
    public void tranNumConfig() {
        rangToRangCommon(SQLConfig.tran_num_label_config);
        redirect("/tag");
    }
    //月交易次数----end

    //月交易金额----start
    @RequiresPermissions("/tag/tranMoney")
    public void tranMoney() {
        List<Map<String, Object>> tranMoneys = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.tran_money_label), false);
        setAttr("tranMoneys", tranMoneys);
    }

    @RequiresPermissions("/tag/tranMoneyConfig")
    public void tranMoneyConfig() {
        rangToRangCommon(SQLConfig.tran_money_label_config);
        redirect("/tag");
    }
    //月交易金额----end

    //时间偏好----start
    @RequiresPermissions("/tag/timeHobby")
    public void timeHobby() {
        List<Map<String, Object>> timeHobbys = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.time_hobby_label), false);
        setAttr("timeHobbys", timeHobbys);
    }

    @RequiresPermissions("/tag/timeHobbyConfig")
    public void timeHobbyConfig() {
        rangToRangCommon(SQLConfig.time_hobby_label_config);
        redirect("/tag");
    }
    //时间偏好----end

    //安全认证方式----start
    @RequiresPermissions("/tag/secAuth")
    public void secAuth() {
        List<Map<String, Object>> secAuths = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.sec_auth_label), false);
        List<String> contents = new ArrayList<>();
        String[] checked = {"UK一代认证", "UK二代认证", "UK三代认证", "SE证书认证", "刮刮卡认证", "邮箱认证", "合作平台认证", "安全问题认证", "手势密码认证", "二维码认证", "登录密码认证", "预留信息认证", "手机短信认证", "支付密码认证", "文件证书认证", "设备绑定认证", "软令牌认证", "硬令牌认证"};
        for (int i = 0; i < checked.length; i++) {
            contents.add(checked[i]);
        }
        setAttr("contents", contents);
        setAttr("secAuths", secAuths);
    }

    @RequiresPermissions("/tag/secAuthConfig")
    public void secAuthConfig() {
        checkCommon(SQLConfig.sec_auth_label_config);
        redirect("/tag");
    }
    //安全认证方式----end

    //支付偏好----start
    @RequiresPermissions("/tag/payHobby")
    public void payHobby() {
        List<Map<String, Object>> payHobbys = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.pay_hobby_label), false);
        String[] select = {"财付通", "京东支付", "银联在线支付", "支付宝快捷支付", "蜀信e转账支付"};
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < select.length; i++) {
            contents.add(select[i]);
        }
        setAttr("contents", contents);
        setAttr("payHobbys", payHobbys);
    }

    @RequiresPermissions("/tag/payHobbyConfig")
    public void payHobbyConfig() {
        selectCommon(SQLConfig.pay_hobby_label_config);
        redirect("/tag");
    }
    //支付偏好----start

    //汇路时效----start
    @RequiresPermissions("/tag/sendEff")
    public void sendEff() {
        List<Map<String, Object>> sendEffs = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.send_eff_label), false);
        String[] select = {"普通汇款", "快速汇款", "实时汇款"};
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < select.length; i++) {
            contents.add(select[i]);
        }
        setAttr("contents", contents);
        setAttr("sendEffs", sendEffs);
    }

    @RequiresPermissions("/tag/sendEffConfig")
    public void sendEffConfig() {
        selectCommon(SQLConfig.send_eff_label_config);
        redirect("/tag");
    }
    //汇路时效----start

    //行内外转账----start
    @RequiresPermissions("/tag/bankTran")
    public void bankTran() {
        List<Map<String, Object>> bankTrans = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.bank_tran_label), false);
        String[] select = {"行内转账", "跨行转账"};
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < select.length; i++) {
            contents.add(select[i]);
        }
        setAttr("contents", contents);
        setAttr("bankTrans", bankTrans);
    }

    @RequiresPermissions("/tag/bankTranConfig")
    public void bankTranConfig() {
        selectCommon(SQLConfig.bank_tran_label_config);
        redirect("/tag");
    }
    //行内外转账----end

    //交易偏好----start
    @RequiresPermissions("/tag/tranHobby")
    public void tranHobby() {
        List<Map<String, Object>> tranHobbys = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.tran_hobby_label), false);
        String[] select = {"存款", "取款", "转账", "消费", "贷款", "费用套餐", "理财", "支付"};
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < select.length; i++) {
            contents.add(select[i]);
        }
        setAttr("contents", contents);
        setAttr("tranHobbys", tranHobbys);
    }

    @RequiresPermissions("/tag/tranHobbyConfig")
    public void tranHobbyConfig() {
        selectCommon(SQLConfig.tran_hobby_label_config);
        redirect("/tag");
    }
    //交易偏好----end

    //转账类型偏好----start
    @RequiresPermissions("/tag/tranType")
    public void tranType() {
        List<Map<String, Object>> tranTypes = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.tran_type_label), false);
        String[] select = {"随E转", "好友转账", "普通转账", "电子回单", "转帐通讯录", "资金归集"};
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < select.length; i++) {
            contents.add(select[i]);
        }
        setAttr("contents", contents);
        setAttr("tranTypes", tranTypes);
    }

    @RequiresPermissions("/tag/tranTypeConfig")
    public void tranTypeConfig() {
        selectCommon(SQLConfig.tran_type_label_config);
        redirect("/tag");
    }
    //转账类型偏好----end

    //存款产品偏好----start
    @RequiresPermissions("/tag/prodHobby")
    public void prodHobby() {
        List<Map<String, Object>> prodHobbys = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.prod_hobby_label), false);
        String[] select = {"双整", "零整", "定活两便", "整零", "通知存款", "智能通知存款", "约期定期", "定活宝", "定存宝"};
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < select.length; i++) {
            contents.add(select[i]);
        }
        setAttr("contents", contents);
        setAttr("prodHobbys", prodHobbys);
    }

    @RequiresPermissions("/tag/prodHobbyConfig")
    public void prodHobbyConfig() {
        selectCommon(SQLConfig.prod_hobby_label_config);
        redirect("/tag");
    }
    //存款产品偏好----end

    //用户群体类型----start
    @RequiresPermissions("/tag/groupType")
    public void groupType() {
        List<Map<String, Object>> groupTypes = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.group_type_label), false);
        setAttr("groupTypes", groupTypes);
    }

    @RequiresPermissions("/tag/useTime")
    public void groupTypeConfig() {
        rangToRangCommon(SQLConfig.group_type_label_config);
        redirect("/tag");
    }
    //用户群体类型----end

    //社交关系----start
    @RequiresPermissions("/tag/socRelation")
    public void socRelation() {
        List<Map<String, Object>> socRelations = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.soc_relation_label), false);
        setAttr("socRelations", socRelations);
    }

    @RequiresPermissions("/tag/socRelationConfig")
    public void socRelationConfig() {
        rangToRangCommon(SQLConfig.soc_relation_label_config);
        redirect("/tag");
    }
    //社交关系----end

    //网页搜索操作行为----start
    @RequiresPermissions("/tag/searBehaviour")
    public void searBehaviour() {
        List<Map<String, Object>> searBehaviours = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.sear_behaviour_label), false);
        setAttr("searBehaviours", searBehaviours);
    }

    @RequiresPermissions("/tag/searBehaviourConfig")
    public void searBehaviourConfig() {
        rangToRangCommon(SQLConfig.sear_behaviour_label_config);
        redirect("/tag");
    }
    //网页搜索操作行为----end

    //网页投诉操作行为----start
    @RequiresPermissions("/tag/compBehaviour")
    public void compBehaviour() {
        List<Map<String, Object>> compBehaviours = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.comp_behaviour_label), false);
        setAttr("compBehaviours", compBehaviours);
    }

    @RequiresPermissions("/tag/compBehaviourConfig")
    public void compBehaviourConfig() {
        rangToRangCommon(SQLConfig.comp_behaviour_label_config);
        redirect("/tag");
    }
    //网页投诉操作行为----end

    //网页留言操作行为----start
    @RequiresPermissions("/tag/noteBehaviour")
    public void noteBehaviour() {
        List<Map<String, Object>> noteBehaviours = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.note_behaviour_label), false);
        setAttr("noteBehaviours", noteBehaviours);
    }

    @RequiresPermissions("/tag/noteBehaviourConfig")
    public void noteBehaviourConfig() {
        rangToRangCommon(SQLConfig.note_behaviour_label_config);
        redirect("/tag");
    }
    //网页留言操作行为----end

    //网页浏览操作行为----start
    @RequiresPermissions("/tag/eventBehaviour")
    public void eventBehaviour() {
        List<Map<String, Object>> eventBehaviours = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.event_behaviour_label), false);
        setAttr("eventBehaviours", eventBehaviours);
    }

    @RequiresPermissions("/tag/eventBehaviourConfig")
    public void eventBehaviourConfig() {
        rangToRangCommon(SQLConfig.event_behaviour_label_config);
        redirect("/tag");
    }
    //网页浏览操作行为----end

    //使用时段----start
    @RequiresPermissions("/tag/useTime")
    public void useTime() {
        List<Map<String, Object>> useTimes = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.use_time_label), false);
        setAttr("useTimes", useTimes);
    }

    @RequiresPermissions("/tag/useTimeConfig")
    public void useTimeConfig() {
        rangToRangCommon(SQLConfig.use_time_label_config);
        redirect("/tag");
    }
    //使用时段----end

    //搜索引擎----start
    @RequiresPermissions("/tag/searchEng")
    public void searchEng() {
        List<Map<String, Object>> searchEngs = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.search_eng_label), false);
        String[] select = {"百度", "360", "谷歌", "搜狗", "Bing"};
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < select.length; i++) {
            contents.add(select[i]);
        }
        setAttr("contents", contents);
        setAttr("searchEngs", searchEngs);
    }

    @RequiresPermissions("/tag/searchEngConfig")
    public void searchEngConfig() {
        selectCommon(SQLConfig.search_eng_label_config);
        redirect("/tag");
    }
    //搜索引擎----end

    //终端----start
    @RequiresPermissions("/tag/terminal")
    public void terminal() {
        List<Map<String, Object>> terminals = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.terminal_label), false);
        String[] select = {"PC", "平板电脑", "安卓手机", "苹果手机"};
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < select.length; i++) {
            contents.add(select[i]);
        }
        setAttr("contents", contents);
        setAttr("terminals", terminals);
    }

    @RequiresPermissions("/tag/terminalConfig")
    public void terminalConfig() {
        selectCommon(SQLConfig.terminal_label_config);
        redirect("/tag");
    }
    //终端----end

    //活跃变化特征----start
    @RequiresPermissions("/tag/chaFeature")
    public void chaFeature() {
        List<Map<String, Object>> chaFeatures = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.cha_feature_label), false);
        setAttr("chaFeatures", chaFeatures);
    }

    @RequiresPermissions("/tag/chaFeatureConfig")
    public void chaFeatureConfig() {
        rangToRangCommon(SQLConfig.cha_feature_label_config);
        redirect("/tag");
    }
    //活跃变化特征----end

    //活跃功能特征----start
    @RequiresPermissions("/tag/funcFeature")
    public void funcFeature() {
        List<Map<String, Object>> funcFeatures = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.func_feature_label), false);
        String[] select = {"登录", "任务活跃", "业务活跃", "交易活跃", "社交活跃", "活动活跃评论", "产品信息分享", "收藏", "营销活动"};
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < select.length; i++) {
            contents.add(select[i]);
        }
        setAttr("contents", contents);
        setAttr("funcFeatures", funcFeatures);
    }

    @RequiresPermissions("/tag/funcFeatureConfig")
    public void funcFeatureConfig() {
        selectCommon(SQLConfig.func_feature_label_config);
        redirect("/tag");
    }
    //活跃功能特征----end

    //网银搜索关键字----start----未完成
    @RequiresPermissions("/tag/searchKey")
    public void searchKey() {

    }
    //网银搜索关键字----end

    @RequiresPermissions("/tag/activeLevel")
    public void activeLevel(){
        if (BaseUtils.isAjax(getRequest())) {
            // 得到时间查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String param = getPara("param");
            if (param != null && !param.isEmpty()){
                condition = condition + "where user_id = '" + param + "'";
            }
            // 执行查询
            List<List<String>> activeLevelData = InceptorUtil.query(SqlKit.propSQL(SQLConfig.active_level_day, condition));
            JSONObject listResult = new JSONObject();
            listResult.put("activeLevelData", activeLevelData);
            renderJson(listResult);
        }
    }
}
