package io.transwarp.scrcu.portal;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.inceptor.InceptorUtil;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.base.util.SQLConfig;
import io.transwarp.scrcu.sqlinxml.SqlKit;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.ArrayList;
import java.util.List;

import static io.transwarp.scrcu.base.util.GeneratePortalChartsUtils.genSearchEngineCharts;

@RequiresAuthentication
public class VisitSourceController extends BaseController {

	Res res = I18n.use("i18n", "zh_CN");

	/**
	 * web统计 搜索引擎
	 */
	@RequiresPermissions("/portal/visitSource/searchEngine")
	public void searchEngine() {
		if (BaseUtils.isAjax(getRequest())) {

			//定义集合接收搜索引擎数据
			List<List<String>> searchEngineData = new ArrayList<>();
			List<List<String>> searchEngineChart = new ArrayList<>();
			//接收返回的结果数据
			JSONObject result = new JSONObject();

			// 得到查询条件
			String condition = InceptorUtil.getQueryCondition(getRequest());
			//获取汇总查询的类型，day:天, month:月
			String dateType = getPara("dateType");

			if (dateType != null) {
				if (dateType.equals("day")) {
					// 执行查询
					searchEngineData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_searchEngine_day, condition),
							false);
					searchEngineChart = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_searchEngine_day_chart, condition),
							false);
				}

				if (dateType.equals("month")) {
					// 执行查询
					searchEngineData = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_searchEngine_month, condition),
							false);
					searchEngineChart = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_searchEngine_month_chart, condition),
							false);
				}
			}

			//生成饼图数据并返回
			result.put("chartOption", genSearchEngineCharts(searchEngineChart));

			result.put("data", searchEngineData);
			renderJson(result);
		}
	}

	/**
	 * web统计 来源分析
	 */
	@RequiresPermissions("/portal/visitSource/source")
	public void source() {
		if (BaseUtils.isAjax(getRequest())) {

			// 执行查询
			List<List<String>> dataSource  = new ArrayList<>();
			// 得到查询条件
			String condition = InceptorUtil.getQueryCondition(getRequest());
			//获取汇总查询的类型，day:天, month:月
			String dateType = getPara("dateType");

			if (dateType != null) {
				if (dateType.equals("day")) {
					// 执行查询
					dataSource = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_source_day, condition),
							false);
				}

				if (dateType.equals("month")) {
					// 执行查询
					dataSource = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_sourceAnalysis_source_month, condition),
							false);
				}
			}
			/*List<Object> dataList = new ArrayList<Object>();
			for (List<String> list : dataSource) {
				Data d = new Data(list.get(0), Integer.valueOf(list.get(3)));
				dataList.add(d);
			}
			String str = ChartUtils.genPie(res.get("portal.visitorUV"), dataList);
			// 生成图
			result.put("chartOption", str);*/
			// 返回结果
			JSONObject result = new JSONObject();
			result.put("dataSource", dataSource);

			renderJson(result);
		}

	}

	/**
	 * web统计 入口页面分析
	 */
	@RequiresPermissions("/portal/visitSource/entryPage")
	public void entryPage() {
		if (BaseUtils.isAjax(getRequest())) {

			List<List<String>> dataPage = new ArrayList<>();
			// 得到查询条件
			String condition = InceptorUtil.getQueryCondition(getRequest());
			//获取汇总查询的类型，day:天, month:月
			String dateType = getPara("dateType");

			if (dateType != null) {
				if (dateType.equals("day")) {
					// 执行查询
					dataPage = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_entryPage_query_day, condition),
							false);
				}

				if (dateType.equals("month")) {
					// 执行查询
					dataPage = InceptorUtil.queryCache(SqlKit.propSQL(SQLConfig.portal_siteAnalysis_entryPage_query_month, condition),
							false);
				}
			}

			// 返回结果
			JSONObject result = new JSONObject();
			result.put("dataPage", dataPage);
			renderJson(result);
		}

	}

}
