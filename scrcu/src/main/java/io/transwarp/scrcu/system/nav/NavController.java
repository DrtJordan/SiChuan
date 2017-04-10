package io.transwarp.scrcu.system.nav;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import com.jfinal.kit.JsonKit;

import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.system.resource.SysRes;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RequiresAuthentication
public class NavController extends BaseController {

	Res res = I18n.use("i18n", "zh_CN");

	public void index() {
	}

	public void getlist() {
		Integer pid = getParaToInt("id", 0);
		List<SysNav> models = SysNav.dao.where("pid=?", pid);
		List<Map<String, Object>> nodes = new ArrayList<Map<String, Object>>();
		Iterator<SysNav> it = models.iterator();
		while (it.hasNext()) {
			SysNav model = it.next();
			nodes.add(model.toNodeData());
		}
		renderJson(nodes);
	}

	@RequiresPermissions("/system/nav/create")
	public void create() {
		Integer pid = getParaToInt(0, 0);
		int def = 0;
		if (isPost()) {
			SysNav model = getModel(SysNav.class, "sysnav");
			if (model.get("res_id") == null){
				model.set("res_id", def);
			}
			if (model.save()) {
				Map<String, Object> r = new HashMap<String, Object>();
				r.put("success", true);
				r.put("data", model.toNodeData());
				renderJavascript(JsonKit.toJson(r));
				return;
			}
		}
		setAttr("data", new SysNav().set("pid", pid));
		render("form.html");
	}

	@RequiresPermissions("/system/nav/update")
	public void update() {
		if (isPost()) {
			Integer id = getParaToInt("sysnav.id");
			SysNav model = getModel(SysNav.class, "sysnav").set("id", id);
			model.update();
			Map<String, Object> r = new HashMap<String, Object>();
			r.put("success", true);
			r.put("data", model.toNodeData());
			renderJavascript(JsonKit.toJson(r));
			return;
		} else {
			setAttr("data", SysNav.dao.findById(getParaToInt()));
			render("form.html");
		}
	}

	@RequiresPermissions("/system/nav/delete")
	public void delete() {
		int id = getParaToInt();
		if (SysNav.dao.findById(id).delete()) {
			Map<String, Object> r = new HashMap<String, Object>();
			r.put("success", true);
			r.put("data", new HashMap<String, Object>().put("id", id));
			renderJavascript(JsonKit.toJson(r));
			return;
		} else {
			Map<String, Object> r = new HashMap<String, Object>();
			r.put("success", false);
			r.put("msg", res.get("system.deleteFail"));
			renderJavascript(JsonKit.toJson(r));
			return;
		}
	}

	public void getres() {
		List<SysRes> res = SysRes.dao.findAll();
		List<Map<String, Object>> r = new ArrayList<Map<String, Object>>();
		Iterator<SysRes> it = res.iterator();
		while (it.hasNext()) {
			Map<String, Object> row = new HashMap<String, Object>();
			SysRes sysRes = (SysRes) it.next();
			row.put("id", sysRes.get("id"));
			row.put("pId", sysRes.get("pid"));
			row.put("name", sysRes.get("cname"));
			if (sysRes.hasChild()) {
				row.put("open", true);
			}
			r.add(row);
		}
		renderJavascript(JsonKit.toJson(r));
	}
}
