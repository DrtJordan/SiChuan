package test.io.transwarp.scrcu.system.users;

import test.io.transwarp.scrcu.JfinalUnitTestsConfig;
import io.transwarp.scrcu.system.users.UserRoles;
import org.junit.*;

import static org.junit.Assert.assertEquals;


/**
 * UserRoles Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 15, 2017</pre>
 */
public class UserRolesTest {

    @BeforeClass
    public static void setUp() throws Exception {
        JfinalUnitTestsConfig.setDataSourceBeforeClass("userRolesDataSourceConfig");
    }

    /**
     * Method: deleteByUserId(int userId)
     */
    @Test
    public void testDeleteByUserId() throws Exception {

        UserRoles.dao.deleteByUserId("userRolesDataSourceConfig", 999);

        System.out.println("删除成功");
    }

    /**
     * Method: findByUserId(String username)
     */
    @Test
    public void testFindByUserId() throws Exception {
        UserRoles userRoles = UserRoles.dao.findByUserId("admin");

        assertEquals(4, userRoles.get("user_id"));
        assertEquals(10, userRoles.get("role_id"));
    }

    @AfterClass
    public static void closeDataSource() throws Exception {
        JfinalUnitTestsConfig.closeDataSource();
    }
} 
