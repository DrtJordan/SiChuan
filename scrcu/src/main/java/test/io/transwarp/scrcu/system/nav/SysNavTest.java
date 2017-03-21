package test.io.transwarp.scrcu.system.nav;

import com.jfinal.plugin.activerecord.Page;
import io.transwarp.scrcu.system.nav.SysNav;
import org.junit.*;
import test.io.transwarp.scrcu.JfinalUnitTestsConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * SysNav Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 20, 2017</pre>
 */
public class SysNavTest {

    @BeforeClass
    public static void setUp() throws Exception {
        JfinalUnitTestsConfig.setDataSourceBeforeClass("sysNavDataSourceConfig");
    }

    @AfterClass
    public static void closeDataSource() throws Exception {
        JfinalUnitTestsConfig.closeDataSource();
    }

    /**
     * Method: paginate(int pageNumber, int pageSize)
     */
    @Test
    public void testPaginate() throws Exception {

        Page<SysNav> sysNavPage = SysNav.dao.paginate(1, 10);

        assertEquals(10, sysNavPage.getList().size());
        assertEquals(7, sysNavPage.getTotalPage());
        assertEquals(200, sysNavPage.getList().get(0).get("id"));
        assertEquals("门户统计", sysNavPage.getList().get(0).get("title"));

    }

    /**
     * Method: findByRoleId(int roleId)
     */
    @Test
    public void testFindByRoleId() throws Exception {
        List<SysNav> sysNavList = SysNav.dao.findByRoleId(1);

        assertEquals(12, sysNavList.size());
        assertEquals(210, sysNavList.get(0).get("id"));
        assertEquals("系统概况", sysNavList.get(0).get("title"));
        assertEquals(200, sysNavList.get(0).get("pid"));
    }

    /**
     * Method: findNav()
     */
    @Test
    public void testFindNav() throws Exception {

        List<SysNav> sysNavList = SysNav.dao.findNav();

        assertEquals(31, sysNavList.size());
        assertEquals(200, sysNavList.get(0).get("res_id"));
        assertEquals(0, sysNavList.get(0).get("pid"));
        assertEquals(200, sysNavList.get(0).get("id"));
        assertEquals("/", sysNavList.get(0).get("ak"));
        assertEquals("门户统计", sysNavList.get(0).get("title"));
    }

} 
