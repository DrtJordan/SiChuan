package test.io.transwarp.scrcu.system.users;

import com.jfinal.plugin.activerecord.Page;
import test.io.transwarp.scrcu.JFinalConfigTest;
import io.transwarp.scrcu.system.users.Users;
import org.junit.Test;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

import static org.junit.Assert.assertEquals;

/**
 * Users Tester.
 *
 * @author hang_xiao
 * @version 1.0
 * @since <pre>三月 15, 2017</pre>
 */
public class UsersTest extends ControllerTestCase<JFinalConfigTest> {

    /**
     * Method: paginate(int pageNumber, int pageSize)
     */
    @Test
    public void testPaginate() throws Exception {
        Page<Users> users = Users.dao.paginate(1, 3);

        assertEquals(3, users.getList().size());
        assertEquals("admin", users.getList().get(0).get("username"));
    }

    /**
     * Method: findByUserIdWithRoleId(int userId)
     */
    @Test
    public void testFindByUserIdWithRoleId() throws Exception {
        Users user = Users.dao.findByUserIdWithRoleId(4);

        assertEquals(4, user.get("id"));
        assertEquals("admin", user.get("username"));    }

    /**
     * Method: findByUserName(String name)
     */
    @Test
    public void testFindByUserName() throws Exception {
        Users user = Users.dao.findByUserName("admin");

        assertEquals("admin", user.get("username"));
        assertEquals(4, user.get("id"));
    }

}
