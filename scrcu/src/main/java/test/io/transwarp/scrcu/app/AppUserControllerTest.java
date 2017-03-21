package test.io.transwarp.scrcu.app;

import org.junit.Test;
import test.io.transwarp.scrcu.JFinalConfigTest;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

/**
 * AppUserController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 15, 2017</pre>
 */
public class AppUserControllerTest extends ControllerTestCase<JFinalConfigTest> {

    /**
     * Method: activeUser()
     */
    @Test
    public void testActiveUser() throws Exception {
        String result = use("/app/userAnalysis/activeUser?start_dt=2015-11-01&end_dt=2017-01-01&dateType=day").invoke();
        System.out.println(result);


    }

    /**
     * Method: retainUser()
     */
    @Test
    public void testRetainUser() throws Exception {
        String result = use("/app/userAnalysis/retainUser").invoke();
        System.out.println(result);
    }

    /**
     * Method: regUser()
     */
    @Test
    public void testRegUser() throws Exception {
        String result = use("/app/userAnalysis/regUser").invoke();
        System.out.println(result);    }

    /**
     * Method: loginUser()
     */
    @Test
    public void testLoginUser() throws Exception {
        String result = use("/app/userAnalysis/loginUser").invoke();
        System.out.println(result);
    }

    /**
     * Method: newUser()
     */
    @Test
    public void testNewUser() throws Exception {
        String result = use("/app/userAnalysis/newUser").invoke();
        System.out.println(result);
    }


} 
