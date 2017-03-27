package test.io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import test.io.transwarp.scrcu.JFinalConfigTest;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

import static org.junit.Assert.assertEquals;

/**
 * AppController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 24, 2017</pre>
 */
public class AppControllerTest extends ControllerTestCase<JFinalConfigTest> {

    /**
     * Method: startCount()
     */
    @Test
    public void testStartCount() throws Exception {
        String resultDay = use("/app/appAnalysis/startCount?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataPhone = jsonResultDay.getJSONArray("dataPhone");

        assertEquals(1, dataPhone.size());
        assertEquals(4, jsonResultDay.size());
        assertEquals("2015-11-01", dataPhone.getJSONArray(0).get(0));
        assertEquals("Android", dataPhone.getJSONArray(0).get(1));
        assertEquals("231799", dataPhone.getJSONArray(0).get(2));
        assertEquals("1", dataPhone.getJSONArray(0).get(3));
    }

    /**
     * Method: version()
     */
    @Test
    public void testVersion() throws Exception {
        String resultDay = use("/app/appAnalysis/version?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataTime = jsonResultDay.getJSONArray("data");

        assertEquals(6, dataTime.size());
        assertEquals(1, jsonResultDay.size());
        assertEquals("2015-11-01", dataTime.getJSONArray(0).get(0));
        assertEquals("1.6", dataTime.getJSONArray(0).get(1));
        assertEquals("192", dataTime.getJSONArray(0).get(2));
        assertEquals("0.0067", dataTime.getJSONArray(0).get(3));
        assertEquals("487", dataTime.getJSONArray(0).get(4));
        assertEquals("564", dataTime.getJSONArray(0).get(5));
        assertEquals("2745", dataTime.getJSONArray(0).get(6));
    }

} 
