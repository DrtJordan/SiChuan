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
public class TagController extends Controller{

    public void index(){
        String[][] strings = {
                {"年龄","age"},{"蜀信e注册年限","regYear"},{"差旅人群","peoTravel"},{"客户参与活动","partActive"},{"月交易次数","tranNum"},{"时间偏好","timeHobby"},{"支付偏好","payHobby"},{"行内外转账","bankTran"},{"转账类型偏好","tranType"},{"用户群体类型","groupType"},{"网页操作行为","opeBehavior"},{"搜索引擎","searchEng"},{"活跃变化特征","chaFeature"},{"网银搜索关键字", "searchKey"},
                {"职业","career"},{"资产负债","proDebt"},{"持有产品服务","prodServer"},{"产品功能消费偏好","customHobby"},{"月交易金额","tranMoney"},{"安全认证方式","secAuth"},{"汇路时效","sendEff"},{"交易偏好","tranHobby"},{"存款产品偏好","prodHobby"},{"社交关系","socRelation"},{"使用时段","useTime"},{"终端","terminal"},{"活跃功能特征","funcFeature"}
        };
        List<String[][]> tagList = new ArrayList<>();
        tagList.add(strings);
        setAttr("tagLists", tagList.get(0));
    }

    public void age(){

    }

    public void career(){

    }

    public void regYear(){

    }

    public void proDebt(){

    }

    public void peoTravel(){

    }

    public void prodServer(){

    }

    public void partActive(){

    }

    public void customHobby(){

    }

    public void tranNum(){

    }

    public void tranMoney(){

    }

    public void timeHobby(){

    }

    public void secAuth(){

    }

    public void payHobby(){

    }

    public void sendEff(){

    }

    public void bankTran(){

    }

    public void tranHobby(){

    }

    public void tranType(){

    }

    public void prodHobby(){

    }

    public void groupType(){

    }

    public void socRelation(){

    }

    public void opeBehavior(){

    }

    @RequiresPermissions("/tag/useTime")
    public void useTime(){
        List<Map<String, Object>> useTime = InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.use_time_label), false);
        setAttr("useTimes", useTime);
    }
    @RequiresPermissions("/tag/useTimeConfig")
    public void useTimeConfig(){
        String[] keys = getParaValues("key");
        StringBuffer val = new StringBuffer(" ");
        for(int i = 0; i < keys.length; i++){
            val = val.append("begin_use_time = '").append(getPara("start" + i)).append("', end_use_time = '").append(getPara("end" + i)).append("' where use_time = '").append(keys[i]).append("'").append(";");
            InceptorUtil.mapQuery(SqlKit.propSQL(SQLConfig.use_time_label_config, val.toString()), false);
            val = val.delete(1, val.length());
        }
        redirect("/tag");
    }

    public void searchEng(){

    }

    public void terminal(){

    }

    public void chaFeature(){

    }

    public void funcFeature(){

    }

    public void searchKey(){

    }
}
