package test.io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import test.io.transwarp.scrcu.JFinalConfigTest;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

import static org.junit.Assert.assertEquals;

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
        String resultDay = use("/app/userAnalysis/activeUser?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataTime = jsonResultDay.getJSONArray("dataTime");
        JSONArray dataPhone = jsonResultDay.getJSONArray("dataPhone");
        JSONArray dataChannel = jsonResultDay.getJSONArray("dataChannel");

        assertEquals(6, jsonResultDay.size());

        assertEquals(2, dataTime.size());
        assertEquals("2015-11-01", dataTime.getJSONArray(0).get(0));
        assertEquals("55592", dataTime.getJSONArray(0).get(1));

        assertEquals(2, dataPhone.size());
        assertEquals("Android", dataPhone.getJSONArray(0).get(1));
        assertEquals("55592", dataPhone.getJSONArray(0).get(2));

        assertEquals(2, dataChannel.size());
        assertEquals("07", dataChannel.getJSONArray(0).get(1));
        assertEquals("55592", dataChannel.getJSONArray(0).get(2));

    }

    /**
     * Method: retainUser()
     */
    @Test
    public void testRetainUser() throws Exception {

        String resultDay = use("/app/userAnalysis/retainUser?start_dt=2016-12-31&end_dt=2017-01-01&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataTime = jsonResultDay.getJSONArray("dataTime");
        JSONArray dataPhone = jsonResultDay.getJSONArray("dataPhone");
        JSONArray dataChannel = jsonResultDay.getJSONArray("dataChannel");

        assertEquals(6, jsonResultDay.size());

        assertEquals(2, dataTime.size());
        assertEquals("2016-12-31", dataTime.getJSONArray(0).get(0));
        assertEquals("4250", dataTime.getJSONArray(0).get(1));

        assertEquals(0, dataPhone.size());
//        assertEquals("Android", dataPhone.getJSONArray(0).get(0));
//        assertEquals("4250", dataPhone.getJSONArray(0).get(1));

        assertEquals(3, dataChannel.size());
        assertEquals("01", dataChannel.getJSONArray(0).get(1));
        assertEquals("1295", dataChannel.getJSONArray(0).get(2));
    }

    /**
     * Method: regUser()
     */
    @Test
    public void testRegUser() throws Exception {
        String resultDay = use("/app/userAnalysis/regUser?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataPhone = jsonResultDay.getJSONArray("dataPhone");
        JSONArray dataChannel = jsonResultDay.getJSONArray("dataChannel");

        assertEquals(4, jsonResultDay.size());

        assertEquals(1, dataPhone.size());
        assertEquals("2015-11-01", dataPhone.getJSONArray(0).get(0));
        assertEquals("Android", dataPhone.getJSONArray(0).get(1));
        assertEquals("54136", dataPhone.getJSONArray(0).get(2));

        assertEquals(0, dataChannel.size());
//        assertEquals("07", dataChannel.getJSONArray(0).get(1));
//        assertEquals("54136", dataChannel.getJSONArray(0).get(2));
    }

    /**
     * Method: loginUser()
     */
    @Test
    public void testLoginUser() throws Exception {
        String resultDay = use("/app/userAnalysis/loginUser?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataTime = jsonResultDay.getJSONArray("dataTime");
        JSONArray dataPhone = jsonResultDay.getJSONArray("dataPhone");
        JSONArray dataChannel = jsonResultDay.getJSONArray("dataChannel");

        assertEquals(6, jsonResultDay.size());

        assertEquals(2, dataTime.size());
        assertEquals("2015-11-01", dataTime.getJSONArray(0).get(0));
        assertEquals("55592", dataTime.getJSONArray(0).get(1));
        assertEquals("15", dataTime.getJSONArray(0).get(2));

        assertEquals(2, dataPhone.size());
        assertEquals("Android", dataPhone.getJSONArray(0).get(1));
        assertEquals("55592", dataPhone.getJSONArray(0).get(2));

        assertEquals(2, dataChannel.size());
        assertEquals("07", dataChannel.getJSONArray(0).get(1));
        assertEquals("55592", dataChannel.getJSONArray(0).get(2));

    }

    /**
     * Method: newUser()
     */
    @Test
    public void testNewUser() throws Exception {
        String resultDay = use("/app/userAnalysis/newUser?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataTime = jsonResultDay.getJSONArray("dataTime");
        JSONArray dataPhone = jsonResultDay.getJSONArray("dataPhone");

        assertEquals(4, jsonResultDay.size());

        assertEquals(1, dataTime.size());
        assertEquals("2015-11-01", dataTime.getJSONArray(0).get(0));
        assertEquals("54136", dataTime.getJSONArray(0).get(1));
        assertEquals("1.9141", dataTime.getJSONArray(0).get(2));

        assertEquals(1, dataPhone.size());
        assertEquals("Android", dataPhone.getJSONArray(0).get(1));
        assertEquals("54136", dataPhone.getJSONArray(0).get(2));
    }

} 
