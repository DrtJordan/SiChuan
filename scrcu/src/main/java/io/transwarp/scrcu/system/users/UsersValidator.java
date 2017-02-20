package io.transwarp.scrcu.system.users;

import com.jfinal.core.Controller;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import com.jfinal.validate.Validator;

public class UsersValidator extends Validator {

	Res res  = I18n.use("i18n", "zh_CN");

	protected void validate(Controller controller) {
		validateRequiredString("users.username", "usernameMsg", res.get("validator.username"));
		validateRequiredString("users.password", "passwordMsg", res.get("validator.password"));
	}

	protected void handleError(Controller controller) {
		controller.keepModel(Users.class);
		String actionKey = getActionKey();
		if (actionKey.equals("/system/users/save"))
			controller.render("add.html");
		else if (actionKey.equals("/system/users/update"))
			controller.render("edit.html");
	}
}
