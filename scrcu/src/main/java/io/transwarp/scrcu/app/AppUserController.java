package io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONObject;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.service.app.AppUserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * App用户统计控制层
 *
 * @author hang_xiao
 */
@RequiresAuthentication
public class AppUserController extends BaseController {

    /**
     * app活跃用户数据
     */
    @RequiresPermissions("/app/userAnalysis/activeUser")
    public void activeUser() {

        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            //获取日期类型
            String type = getPara("dateType");

            //获取活跃用户的json数据
            JSONObject result = AppUserService.activeUser(type, condition);
            renderJson(result);
        }

    }

    /**
     * app留存用户数据
     */
    @RequiresPermissions("/app/userAnalysis/retainUser")
    public void retainUser() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());

            //获取留存用户的json数据
            JSONObject result = AppUserService.retainUser(condition);
            renderJson(result);
        }
    }

    /**
     * app注册用户数据
     */
    @RequiresPermissions("/app/userAnalysis/regUser")
    public void regUser() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String dateType = getPara("dateType");

            //获取注册用户的json数据
            JSONObject result = AppUserService.registerUser(dateType, condition);
            renderJson(result);

        }
    }

    /**
     * app登录用户数据
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/userAnalysis/loginUser")
    public void loginUser() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String type = getPara("dateType");

            //获取登录用户的json数据
            JSONObject result = AppUserService.loginUser(type, condition);
            renderJson(result);
        }
    }

    /**
     * app新增用户数据
     */
    @RequiresPermissions("/app/userAnalysis/newUser")
    public void newUser() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            String type = getPara("dateType");

            //获取新增用户的json数据
            JSONObject result = AppUserService.newUser(type, condition);
            renderJson(result);
        }
    }

}

