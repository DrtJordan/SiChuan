package io.transwarp.scrcu.portal;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.service.portal.UserAnalysisService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 *  Web用户分析的控制层
 *
 *  @author hang_xiao
 */
@RequiresAuthentication
public class UserAnalysisController extends BaseController {

    @ActionKey("/portal/userAnalysis")
    public void index() {
    }

    /**
     * 地域分布
     */
    @RequiresPermissions("/portal/userAnalysis/area")
    public void area() {
        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

            //获取地域分布的json数据
            JSONObject result = UserAnalysisService.area(dateType, condition);

            renderJson(result);

        }
    }

    /**
     * 终端统计
     */
    @RequiresPermissions("/portal/userAnalysis/terminal")
    public void terminal() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

            //获取终端分布的json数据
            JSONObject result = UserAnalysisService.terminal(dateType, condition);

            renderJson(result);
        }

    }

    /**
     * 会员页面分析
     */
    @RequiresPermissions("/portal/userAnalysis/userVisitPage")
    @Deprecated
    public void userVisitPage() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

            //获取会员页面分析的json数据
            JSONObject result = UserAnalysisService.userVisitPage(dateType, condition);

            renderJson(result);
        }
    }

    /**
     * 流失用户分析
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/portal/userAnalysis/userLoss")
    public void userLoss() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

            //获取流失用户分析的json数据
            JSONObject result = UserAnalysisService.userLoss(dateType, condition);
            renderJson(result);
        }

    }

    /**
     * 唯一用户分析
     */
    @RequiresPermissions("/portal/userAnalysis/userOnly")
    public void userOnly() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

            //获取唯一用户分析的json数据
            JSONObject result = UserAnalysisService.userOnly(dateType, condition);
            renderJson(result);
        }
    }

}
