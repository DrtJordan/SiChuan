package io.transwarp.scrcu.app;

import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.*;
import io.transwarp.scrcu.service.app.AppService;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.alibaba.fastjson.JSONObject;

/**
 * App统计控制层
 *
 * @author hang_xiao
 * @date 2017/01/16
 */
@RequiresAuthentication
public class AppController extends BaseController {

    /**
     * 获取启动次数
     */
    @RequiresPermissions("/app/appAnalysis/startCount")
    public void startCount() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

            JSONObject result = AppService.startCount(dateType, condition);
            renderJson(result);
        }
    }

    /**
     * 获取app版本
     */
    @RequiresPermissions("/app/appAnalysis/version")
    public void version() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

            JSONObject result = AppService.appVersion(dateType, condition);
            renderJson(result);

        }
    }

}
