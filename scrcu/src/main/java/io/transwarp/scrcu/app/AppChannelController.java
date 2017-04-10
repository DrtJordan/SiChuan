package io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONObject;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.service.app.AppChannelService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * App渠道控制层
 *
 * @author hang_xiao
 * @date 2017/01/16
 */
@RequiresAuthentication
public class AppChannelController extends BaseController {

    /**
     * 获取app渠道列表
     */
    @RequiresPermissions("/app/channel/list")
    public void list() {
        if (BaseUtils.isAjax(getRequest())) {

            // 得到查询条件
            String dateType = getPara("dateType");
            String condition = InceptorUtil.getQueryCondition(getRequest());

            JSONObject result = AppChannelService.channelList(dateType, condition);
            renderJson(result);
        }

    }

    /**
     * 获取app渠道详情
     */
    @RequiresPermissions("/app/channel/detail")
    public void detail() {

        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());

            JSONObject result = AppChannelService.channelDetail(condition);
            renderJson(result);
        }
    }

}
