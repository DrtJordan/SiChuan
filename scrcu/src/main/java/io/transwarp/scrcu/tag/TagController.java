package io.transwarp.scrcu.tag;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TANG_0722 on 2017-02-23.
 */
@RequiresAuthentication
public class TagController extends Controller {

    StringBuffer value = new StringBuffer(" ");

    public void index() {
        List<String[][]> tradFeas = new ArrayList<>();
        List<String[][]> peoAttrs = new ArrayList<>();
        List<String[][]> creAttrs = new ArrayList<>();
        List<String[][]> opeAttrs = new ArrayList<>();
        String[][] tradFea = {{"差旅人群", "peoTravel"}, {"客户参与活动", "partActive"}, {"月交易次数", "tranNum"}, {"时间偏好", "timeHobby"}, {"支付偏好", "payHobby"}, {"行内外转账", "bankTran"}, {"转账类型偏好", "tranType"}, {"用户群体类型", "groupType"}, {"持有产品服务", "prodServer"}, {"产品功能消费偏好", "customHobby"}, {"月交易金额", "tranMoney"}, {"安全认证方式", "secAuth"}, {"汇路时效", "sendEff"}, {"交易偏好", "tranHobby"}, {"存款产品偏好", "prodHobby"}};
        String[][] peoAttr = {{"年龄", "age"}, {"职业", "career"}, {"社交关系", "socRelation"}};
        String[][] creAttr = {{"蜀信e注册年限", "regYear"}, {"资产负债", "proDebt"}};
        String[][] opeAttr = {{"网页操作行为", "opeBehavior"}, {"搜索引擎", "searchEng"}, {"活跃变化特征", "chaFeature"}, {"网银搜索关键字", "searchKey"}, {"使用时段", "useTime"}, {"终端", "terminal"}, {"活跃功能特征", "funcFeature"}};
        tradFeas.add(tradFea);
        peoAttrs.add(peoAttr);
        creAttrs.add(creAttr);
        opeAttrs.add(opeAttr);
        setAttr("tradFeas", tradFeas.get(0));
        setAttr("peoAttrs", peoAttrs.get(0));
        setAttr("creAttrs", creAttrs.get(0));
        setAttr("opeAttrs", opeAttrs.get(0));
    }

    public void age() {

    }

    //职业----start
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

    public void careerConfig() {
        String[] keys = getParaValues("key");
        for (int i = 0; i < keys.length; i++) {
            String[] checked = getParaValues(keys[i]);
            if (checked != null) {
                for (int j = 0; j < checked.length; j++) {
                    value.append(checked[j]).append("、");
                }
                //执行sql语句。
                value.deleteCharAt(value.length() - 1);
                value.insert(1, "job_content_name = '");
                value.append("' where job_name = '").append(keys[i]).append("';");
                ;
            } else {
                value.append("job_content_name = NULL").append(" where job_name = '").append(keys[i]).append("';");
            }
            InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.job_label_config, value.toString()), false);
            value = value.delete(1, value.length());
        }
        redirect("/tag");
    }
    //职业----end

    public void regYear() {

    }

    public void proDebt() {

    }

    public void peoTravel() {

    }

    public void prodServer() {

    }

    public void partActive() {

    }

    //产品功能消费偏好----start
    public void customHobby() {
        List<Map<String, Object>> customHobbys = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.custom_hobby_label), false);
        String s = new String();
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < customHobbys.size(); i++) {
            s = customHobbys.get(i).get("pfcp_contente").toString();
            for (int j = 0; j < s.split("、").length; j++) {
                contents.add(s.split("、")[j]);
            }
        }
        setAttr("contents", contents);
        setAttr("customHobbys", customHobbys);
    }

    public void customHobbyConfig() {
        value = value.append("pfcp_order = '").append(getPara("rank")).append("' where pfcp_key = '").append(getPara("key")).append("';");
        InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.custom_hobby_label_config, value.toString()), false);
        redirect("/tag");
    }
    //产品功能消费偏好----end

    public void tranNum() {

    }

    public void tranMoney() {

    }

    public void timeHobby() {

    }

    public void secAuth() {

    }

    public void payHobby() {

    }

    public void sendEff() {

    }

    public void bankTran() {

    }

    public void tranHobby() {

    }

    public void tranType() {

    }

    public void prodHobby() {

    }

    public void groupType() {

    }

    public void socRelation() {

    }

    public void opeBehavior() {

    }

    //使用时段----start
    @RequiresPermissions("/tag/useTime")
    public void useTime() {
        List<Map<String, Object>> useTime = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.use_time_label), false);
        setAttr("useTimes", useTime);
    }

    @RequiresPermissions("/tag/useTimeConfig")
    public void useTimeConfig() {
        String[] keys = getParaValues("key");
        for (int i = 0; i < keys.length; i++) {
            value = value.append("begin_use_time = '").append(getPara("start" + i)).append("', end_use_time = '").append(getPara("end" + i)).append("' where use_time = '").append(keys[i]).append("';");
            InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.use_time_label_config, value.toString()), false);
            value = value.delete(1, value.length());
        }
        redirect("/tag");
    }
    //使用时段----end

    public void searchEng() {

    }

    public void terminal() {

    }

    public void chaFeature() {

    }

    public void funcFeature() {

    }

    public void searchKey() {

    }
}
