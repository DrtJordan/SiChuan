package io.transwarp.scrcu.authority.shiro;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.util.BaseUtils;
import io.transwarp.scrcu.system.nav.SysNav;

import java.util.Map;

/**
 * Created by admin on 2017/1/6.
 */
public class ResourceInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Map<Integer, Map<Integer, SysNav>> navTree = SysNav.dao.tree();
		BaseUtils.navTree = navTree;

		Map<String, Integer> nav = SysNav.dao.nav();
		BaseUtils.nav = nav;
		String action = inv.getActionKey();
		if (!action.equals("/login") && !action.equals("/signin")){
			BaseController baseController = (BaseController) inv.getController();
			baseController.setNav();
		}
		// 执行正常逻辑
		inv.invoke();
	}
}
