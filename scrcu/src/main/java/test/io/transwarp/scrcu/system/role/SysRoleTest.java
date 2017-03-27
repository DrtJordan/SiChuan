package test.io.transwarp.scrcu.system.role;

import com.jfinal.plugin.activerecord.Page;
import io.transwarp.scrcu.system.role.SysRole;
import org.junit.*;
import test.io.transwarp.scrcu.JFinalConfigTest;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

import static org.junit.Assert.assertEquals;

/**
 * SysRole Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 20, 2017</pre>
 */
public class SysRoleTest extends ControllerTestCase<JFinalConfigTest> {

    /**
     * Method: paginate(int pageNumber, int pageSize)
     */
    @Test
    public void testPaginate() throws Exception {
        Page<SysRole> sysRolePage = SysRole.dao.paginate(1, 10);

        assertEquals(1, sysRolePage.getTotalPage());
        assertEquals("admin", sysRolePage.getList().get(0).get("cname"));
        assertEquals(10, sysRolePage.getList().get(0).get("id"));
    }

} 
