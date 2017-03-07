package io.transwarp.scrcu.portal;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import io.transwarp.echarts.data.Data;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.ChartUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;

@RequiresAuthentication
public class VisitSourceController extends Controller {

	Res res = I18n.use("i18n", "zh_CN");

	/**
	 * web统计 搜索引擎
	 */
	@RequiresPermissions("/portal/visitsource/searchEngine")
	public void searchEngine() {
		if (BaseUtils.isAjax(getRequest())) {

			//定义集合接收搜索引擎数据
			List<List<String>> searchEngineData = new ArrayList<>();

			// 得到查询条件
			String condition = InceptorUtil.getQueryCondition(getRequest());
			//获取汇总查询的类型，day:天, month:月
			String dateType = getPara("dateType");

			if (dateType != null) {
				if (dateType.equals("day")) {
					// 执行查询
					searchEngineData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_searchEngine_day, condition),
							false);
				}

				if (dateType.equals("month")) {
					// 执行查询
					searchEngineData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_searchEngine_month, condition),
							false);
				}
			}

			List<Object> dataList = new ArrayList<Object>();
			for (List<String> list : searchEngineData) {
				Data d = new Data(list.get(0), Integer.valueOf(list.get(1)));
				dataList.add(d);
			}
			// 返回结果
			JSONObject result = new JSONObject();

			//生成饼图数据
			String searchEngineChart = ChartUtils.genPie(res.get("portal.visitorCount"), dataList);
			result.put("chartOption", searchEngineChart);

			result.put("data", searchEngineData);
			renderJson(result);
		}
	}

	/**
	 * web统计 来源分析以及入口页面分析
	 */
	@RequiresPermissions("/portal/visitsource/source")
	public void source() {
		if (BaseUtils.isAjax(getRequest())) {
			// 得到查询条件
			String condition = InceptorUtil.getDateCondition(getRequest());
			// 执行查询
			List<List<String>> dataSource = InceptorUtil
					.query(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_source.toString(), condition), 2);

			List<List<String>> dataPage = InceptorUtil
					.query(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_entryPage_query.toString(), condition));

			List<Object> dataList = new ArrayList<Object>();
			for (List<String> list : dataSource) {
				Data d = new Data(list.get(0), Integer.valueOf(list.get(3)));
				dataList.add(d);
			}
			// 返回结果
			JSONObject result = new JSONObject();
			result.put("dataSource", dataSource);
			result.put("dataPage", dataPage);
			String str = ChartUtils.genPie(res.get("portal.visitorUV"), dataList);
			// 生成图
			result.put("chartOption", str);
			renderJson(result);
		}

	}

}
