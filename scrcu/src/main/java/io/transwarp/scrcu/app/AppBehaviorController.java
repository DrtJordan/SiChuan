package io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.echarts.data.Data;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.*;
import io.transwarp.scrcu.service.app.AppBehaviorService;
import io.transwarp.scrcu.sqlinxml.SqlKit;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.ArrayList;
import java.util.List;

import static io.transwarp.scrcu.base.util.GenerateAppChartsUtils.*;

@RequiresAuthentication
public class AppBehaviorController extends BaseController {

    /**
     * 获取使用时长
     */
    @RequiresPermissions("/app/behavior/useTime")
    public void useTime() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

            JSONObject result = AppBehaviorService.useTime(dateType, condition);
            renderJson(result);
        }

    }

    /**
     * 获取使用频率
     */
    @RequiresPermissions("/app/behavior/useRate")
    public void useRate() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

            JSONObject result = AppBehaviorService.useRate(dateType, condition);
            renderJson(result);
        }

    }

    /**
     * 获取访问深度
     */
    @RequiresPermissions("/app/behavior/depth")
    public void depth() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

            JSONObject result = AppBehaviorService.visitDepth(dateType, condition);
            renderJson(result);
        }

    }

    /**
     * 获取访问间隔
     */
    @RequiresPermissions("/app/behavior/interval")
    public void interval() {

        if (BaseUtils.isAjax(getRequest())) {


            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

            JSONObject result = AppBehaviorService.interval(dateType, condition);
            renderJson(result);
        }

    }

    /**
     * 获取地域分布
     */
    @RequiresPermissions("/app/behavior/area")
    public void area() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

            JSONObject result = AppBehaviorService.area(dateType, condition);
            renderJson(result);
        }
    }

}
