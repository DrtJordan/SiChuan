package io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.ChartUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.ArrayList;
import java.util.List;

@RequiresAuthentication
public class AppChannelController extends BaseController {

	Res res = I18n.use("i18n", "zh_CN");

	/**
	 * 获取app渠道列表
	 */
	@SuppressWarnings("unchecked")
	@RequiresPermissions("/app/channel/list")
	public void list() {
		if (BaseUtils.isAjax(getRequest())) {

			List<List<String>> data = new ArrayList<>();
			JSONObject result = new JSONObject();

			// 得到查询条件
			String dateType = getPara("dateType");
			String condition = InceptorUtil.getQueryCondition(getRequest());
			if (dateType != null) {
				if (dateType.equals("day")) {
					data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_channel_list_day, condition),false);
				}
				if (dateType.equals("week")) {
					data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_channel_list_week, condition),false);
				}
				if (dateType.equals("month")) {
					data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_channel_list_month, condition),false);
				}
				if (dateType.equals("quarter")) {
					data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_channel_list_quarter, condition),false);
				}
				if (dateType.equals("year")) {
					data = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_channel_list_year, condition),false);
				}
			}

			// 返回结果
			List<Object> xDataList = new ArrayList<>();
			List<Object> newUserList = new ArrayList<>();
			List<Object> startUserList = new ArrayList<>();
			List<Object> sumCntList = new ArrayList<>();
			List<Object> startCntList = new ArrayList<>();
			Object[] nameList = new Object[]{"新增用户", "启动用户", "累计用户", "启动次数"};
			for (List<String> list : data) {
				if (!xDataList.contains(list.get(0))) {
					xDataList.add(list.get(0));
				}
				newUserList.add(list.get(2));
				startUserList.add(list.get(3));
				sumCntList.add(list.get(4));
				startCntList.add(list.get(6));
			}

			//生成渠道列表的数据
			String genDetailChannel = ChartUtils.genAppMultiLineCharts(dateType,"line",  xDataList, nameList, newUserList, startUserList, sumCntList, startCntList);
			result.put("chartOption", genDetailChannel);
			// 返回结果
			result.put("data", data);
			renderJson(result);
		}

	}

    /**
     * 获取app渠道详情
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("/app/channel/detail")
    public void detail() {

        if (BaseUtils.isAjax(getRequest())) {
            // 得到查询条件
            String condition = InceptorUtil.getQueryCondition(getRequest());
            // 执行查询
            List<List<String>> dataChannel = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.app_channel_detail_day, condition), false);

            JSONObject result = new JSONObject();
            // 返回结果
            List<Object> xDataList = new ArrayList<>();
            List<Object> newUserList = new ArrayList<>();
            List<Object> startUserList = new ArrayList<>();
            List<Object> startCntList = new ArrayList<>();
            Object[] nameList = new Object[]{"新增用户", "启动用户", "启动次数"};
            for (List<String> list : dataChannel) {
                if (!xDataList.contains(list.get(0))) {
                    xDataList.add(list.get(0));
                }
                newUserList.add(list.get(2));
                startUserList.add(list.get(3));
                startCntList.add(list.get(4));
            }

            //生成渠道详情的数据
            String genDetailChannel = ChartUtils.genAppMultiLineCharts("","line",  xDataList, nameList, newUserList, startUserList, startCntList);
            result.put("chartOption", genDetailChannel);
            result.put("data", dataChannel);
            renderJson(result);
        }
    }

}
