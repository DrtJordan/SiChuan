package io.transwarp.scrcu.portal;

import com.alibaba.fastjson.JSONObject;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.service.portal.VisitSourceService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RequiresAuthentication
public class VisitSourceController extends BaseController {
    /**
     * web统计 搜索引擎
     */
    @RequiresPermissions("/portal/visitSource/searchEngine")
    public void searchEngine() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            //获取汇总查询的类型，day:天, month:月
            String dateType = getPara("dateType");

            //获取返回结果
            JSONObject result = VisitSourceService.searchEngine(dateType, condition);
            renderJson(result);
        }
    }

    /**
     * web统计 来源分析
     */
    @RequiresPermissions("/portal/visitSource/source")
    public void source() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            //获取汇总查询的类型，day:天, month:月
            String dateType = getPara("dateType");

            //获取来源分析json数据
            JSONObject result = VisitSourceService.source(dateType, condition);
            renderJson(result);
        }

    }

    /**
     * web统计 入口页面分析
     */
    @RequiresPermissions("/portal/visitSource/entryPage")
    public void entryPage() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            //获取汇总查询的类型，day:天, month:月
            String dateType = getPara("dateType");

            //获取入口页面分析json数据
            JSONObject result = VisitSourceService.entryPage(dateType, condition);
            renderJson(result);
        }

    }

}
