package io.transwarp.scrcu.portal;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.service.portal.EventAnalysisService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * Web统计事件分析控制层
 *
 * @author hang_xiao
 */
@RequiresAuthentication
public class EventAnalysisController extends BaseController {

    @ActionKey("/portal/eventAnalysis")
    public void index() {
    }

    /**
     * 事件列表
     */
    @RequiresPermissions("/portal/eventAnalysis/list")
    public void list() {
        if (BaseUtils.isAjax(getRequest())) {

            //获取查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

            //事件列表Json数据
            JSONObject result = EventAnalysisService.eventList(dateType, condition);
            renderJson(result);
        }
    }

    /**
     * 事件详情
     */
    @RequiresPermissions("/portal/eventAnalysis/detail")
    public void detail() {
        if (BaseUtils.isAjax(getRequest())) {

            String condition = InceptorUtil.getQueryCondition(getRequest());

            //事件详情Json数据
            JSONObject result = EventAnalysisService.eventDetail(condition);
            renderJson(result);
        }
    }

    /**
     * 事件趋势
     */
    @RequiresPermissions("/portal/eventAnalysis/tendency")
    public void tendency() {
        if (BaseUtils.isAjax(getRequest())) {

            String condition = InceptorUtil.getQueryCondition(getRequest());

            //事件趋势Json数据
            JSONObject result = EventAnalysisService.eventTendency(condition);
            renderJson(result);
        }
    }
}
