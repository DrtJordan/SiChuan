package io.transwarp.scrcu.system.users;

import com.jfinal.aop.Before;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import io.transwarp.scrcu.base.controller.BaseController;
import io.transwarp.scrcu.base.interceptor.CommonInterceptor;
import io.transwarp.scrcu.system.role.SysRole;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Before(CommonInterceptor.class)
@RequiresAuthentication
public class UsersController extends BaseController {

    Res res = I18n.use("i18n", "zh_CN");

    @RequiresPermissions("/system/users")
    public void index() {
        setAttr("message", "hello word");
        setAttr("userPage", Users.dao.paginate(getParaToInt(0, 1), 10));
    }

    @RequiresPermissions("/system/users/add")
    public void add() {
        List<SysRole> roleList = SysRole.dao.list();
        setAttr("roleList", roleList);
        Map<String, Integer> levels = new HashMap<>();
        levels.put(res.get("system.province"), 10);
        levels.put(res.get("system.provinceAssociation.Society"), 11);
        levels.put(res.get("system.provinceAssociation.SocietyDepartment"), 12);
        levels.put(res.get("system.provinceAssociation.SocietyTwoLevelsOfDepartments"), 13);
        levels.put(res.get("system.city"), 20);
        levels.put(res.get("system.cityOfficeCityCascadeSociety"), 21);
        levels.put(res.get("system.cityOfficeCityCascadeSocietyDepartment"), 22);
        levels.put(res.get("system.cityOfficeTwoLevelsOfDepartments"), 23);
        levels.put(res.get("system.county"), 30);
        levels.put(res.get("system.countyFederationOfCommunes"), 31);
        levels.put(res.get("system.countyFederationOfCommunesDepartment"), 32);
        levels.put(res.get("system.countyFederationOfCommunesTwoLevelsOfDepartments"), 33);
        levels.put(res.get("system.centerSociety"), 41);
        levels.put(res.get("system.businessMeshPoint"), 51);
        levels.put(res.get("system.businessMeshPointDepartment"), 52);
        setAttr("levels", levels);
    }

    @RequiresPermissions("/system/users/update")
    public void edit() {
        setAttr("users", Users.dao.findByUserIdWithRoleId(getParaToInt()));
        List<SysRole> roleList = SysRole.dao.list();
        setAttr("roleList", roleList);
        Map<String, Integer> levels = new HashMap<>();
        levels.put(res.get("system.province"), 10);
        levels.put(res.get("system.provinceAssociation.Society"), 11);
        levels.put(res.get("system.provinceAssociation.SocietyDepartment"), 12);
        levels.put(res.get("system.provinceAssociation.SocietyTwoLevelsOfDepartments"), 13);
        levels.put(res.get("system.city"), 20);
        levels.put(res.get("system.cityOfficeCityCascadeSociety"), 21);
        levels.put(res.get("system.cityOfficeCityCascadeSocietyDepartment"), 22);
        levels.put(res.get("system.cityOfficeTwoLevelsOfDepartments"), 23);
        levels.put(res.get("system.county"), 30);
        levels.put(res.get("system.countyFederationOfCommunes"), 31);
        levels.put(res.get("system.countyFederationOfCommunesDepartment"), 32);
        levels.put(res.get("system.countyFederationOfCommunesTwoLevelsOfDepartments"), 33);
        levels.put(res.get("system.centerSociety"), 41);
        levels.put(res.get("system.businessMeshPoint"), 51);
        levels.put(res.get("system.businessMeshPointDepartment"), 52);
        setAttr("levels", levels);

    }

    @Before(UsersValidator.class)
    @RequiresPermissions("/system/users/save")
    public void save() {
        Users users = getModel(Users.class);
        users.save();
        UserRoles.dao.deleteByUserId(users.getInt("id"));
        int roleid = getParaToInt("role_id");
        UserRoles ur = new UserRoles();
        ur.set("user_id", users.get("id"));
        ur.set("role_id", roleid);
        ur.set("create_time", new Date());
        ur.save();
        redirect("/system/users");
    }

    @Before(UsersValidator.class)
    @RequiresPermissions("/system/users/update")
    public void update() {
        Users users = getModel(Users.class);
        UserRoles.dao.deleteByUserId(users.getInt("id"));
        int roleid = getParaToInt("role_id");
        UserRoles ur = new UserRoles();
        ur.set("user_id", users.get("id"));
        ur.set("role_id", roleid);
        ur.set("create_time", new Date());
        ur.save();
        users.update();
        redirect("/system/users");
    }

    @RequiresPermissions("/system/users/delete")
    public void delete() {
        Users users = Users.dao.findById(getParaToInt());
        users.delete();
        redirect("/system/users");
    }

    @RequiresPermissions("/system/users/online")
    public void online() {
        redirect("/system/users");
    }

}
