package io.transwarp.scrcu;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import io.transwarp.scrcu.system.nav.SysNav;
import io.transwarp.scrcu.system.resource.SysRes;
import io.transwarp.scrcu.system.role.SysRole;
import io.transwarp.scrcu.system.role.SysRoleRes;
import io.transwarp.scrcu.system.users.UserRoles;
import io.transwarp.scrcu.system.users.Users;

/**
 * @author admin
 * @date 2017/3/15
 */
public class JfinalUnitTestsConfig {

    private static ActiveRecordPlugin activeRecord;
    private static DruidPlugin dp;
    /**
     * 数据连接地址
     */
    private static final String URL = "jdbc:mysql://10.16.8.10/test?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
    private static final String USERNAME = "scrcu";
    private static final String PASSWORD = "scrcu";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_TYPE = "mysql";

    /**
     * @throws java.lang.Exception
     */
    public static void setDataSourceBeforeClass(String configName) throws Exception {
        dp = new DruidPlugin(URL, USERNAME, PASSWORD, DRIVER);
        dp.addFilter(new StatFilter());
        dp.setInitialSize(3);
        dp.setMinIdle(2);
        dp.setMaxActive(5);
        dp.setMaxWait(60000);
        dp.setTimeBetweenEvictionRunsMillis(120000);
        dp.setMinEvictableIdleTimeMillis(120000);

        WallFilter wall = new WallFilter();
        wall.setDbType(DATABASE_TYPE);
        dp.addFilter(wall);

        dp.getDataSource();
        dp.start();

        activeRecord = new ActiveRecordPlugin(configName, dp);
        activeRecord.setDialect(new MysqlDialect()).setDevMode(true)
                .setShowSql(true); // 是否打印sql语句

        // 映射数据库的表和继承与model的实体
        // 只有做完该映射后，才能进行junit测试
        activeRecord.addMapping("sys_users", Users.class);
        activeRecord.addMapping("sys_user_roles", UserRoles.class);
        activeRecord.addMapping("sys_res", SysRes.class);
        activeRecord.addMapping("sys_role", SysRole.class);
        activeRecord.addMapping("sys_nav", SysNav.class);
        activeRecord.addMapping("sys_role_res", SysRoleRes.class);
        activeRecord.start();
    }

    /**
     * 测试完成后关闭数据源
     *
     * @throws java.lang.Exception
     */
    public static void closeDataSource() throws Exception {
        dp.stop();
        activeRecord.stop();
    }

}
