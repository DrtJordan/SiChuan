package io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONObject;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.base.util.GenerateAppChartsUtils;
import io.transwarp.scrcu.sqlinxml.SqlKit;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianfei.tang on 2017/1/5.
 */
@RequiresAuthentication
public class AppMemberController extends BaseController {

    /**
     * 会员页面分析
     */
    @RequiresPermissions("/app/memberAnalysis/memberPage")
    public void memberPage() {
        if (BaseUtils.isAjax(getRequest())) {
            List<List<String>> data = new ArrayList<>();
            JSONObject result = new JSONObject();
            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());
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
            renderJson(result);
        }
    }

    /**
     * 会员流失分析
     */
    @RequiresPermissions("/app/memberAnalysis/memberRunOff")
    public void memberRunOff() {

        if (BaseUtils.isAjax(getRequest())) {
            //执行查询
            List<List<String>> data = new ArrayList<>();
            //返回结果
            JSONObject result = new JSONObject();

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());
            if (dateType != null) {
                if (dateType.equals("day")) {
                    data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_member_runoff_day, condition), false);
                }
                if (dateType.equals("week")) {
                    data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_member_runoff_week, condition), false);
                }
            }

            //返回会员流失生成的折线图图表数据
            result.put("memberLostCharts", GenerateAppChartsUtils.genMemberRunOffCharts(dateType, data));

            result.put("data", data);
            renderJson(result);
        }
    }
}
