package io.transwarp.scrcu.portal;

import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.service.portal.SiteAnalysisService;
import io.transwarp.scrcu.sqlinxml.SqlKit;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.alibaba.fastjson.JSONObject;

@RequiresAuthentication
public class SiteAnalysisController extends BaseController {

    /**
     * 访问分析
     */
    @RequiresPermissions("/portal/siteAnalysis/visitAnalysis")
    public void visitAnalysis() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            //获取日期类型，dateType：day,week,month,quarter,year
            String dateType = getPara("dateType");

            // 返回结果
            JSONObject result = SiteAnalysisService.visitAnalysis(dateType, condition);
            renderJson(result);
        }
    }

    /**
     * 页面排行
     */
    @RequiresPermissions("/portal/siteAnalysis/pageRank")
    public void pageRank() {

        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());

            //获取日期类型,dateType:day,week,month,quarter,year
            String dateType = getPara("dateType");

            //返回页面排行的json数据
            JSONObject result = SiteAnalysisService.pageRank(dateType, condition);
            renderJson(result);
        }
    }

    /**
     * 流量趋势
     */
    @RequiresPermissions("/portal/siteAnalysis/flowTrend")
    public void flowTrend() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

            JSONObject result = SiteAnalysisService.flowTrend(dateType, condition);
            renderJson(result);
        }

    }

    /**
     * 广告趋势
     */
    @RequiresPermissions("/portal/siteAnalysis/adTrend")
    public void adTrend() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            //获取日期类型，dateType：day,week,month,quarter,year
            String dateType = getPara("dateType");

            JSONObject result = SiteAnalysisService.adTrend(dateType, condition);
            renderJson(result);
        }

    }

    /**
     * 实时数据
     */
    @RequiresPermissions("/portal/siteAnalysis/real")
    public void real() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getDateCondition(getRequest());
            // 执行查询
            List<List<String>> data = InceptorUtil
                    .query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_real.toString(), condition), 24);
            // 返回结果
            JSONObject result = new JSONObject();
            result.put("data", data);
            renderJson(result);
        }
    }

}
