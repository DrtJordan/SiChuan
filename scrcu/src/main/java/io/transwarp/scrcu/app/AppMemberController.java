package io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONObject;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.service.app.AppMemberService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * @author  jianfei.tang
 * @date 2017/1/5.
 */
@RequiresAuthentication
public class AppMemberController extends BaseController {

    /**
     * 会员页面分析
     */
    @RequiresPermissions("/app/memberAnalysis/memberPage")
    public void memberPage() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

            //获取会员页面统计的json数据
            JSONObject result = AppMemberService.memberPage(dateType, condition);
            renderJson(result);
        }
    }

    /**
     * 会员流失分析
     */
    @RequiresPermissions("/app/memberAnalysis/memberRunOff")
    public void memberRunOff() {

        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

            JSONObject result = AppMemberService.memberRunOff(dateType, condition);
            renderJson(result);
        }
    }
}
