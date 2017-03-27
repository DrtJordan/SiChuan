package test.io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.io.transwarp.scrcu.JFinalConfigTest;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

import static org.junit.Assert.assertEquals;

/**
 * AppEventController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 24, 2017</pre>
 */
public class AppEventControllerTest extends ControllerTestCase<JFinalConfigTest> {

    /**
     * Method: list()
     */
    @Test
    public void testList() throws Exception {
        String resultDay = use("/app/event/list?start_dt=2017-01-01&end_dt=2017-01-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray eventData = jsonResultDay.getJSONArray("eventData");

        assertEquals(1, eventData.size());
        assertEquals(1, jsonResultDay.size());
        assertEquals("2017-01-01", eventData.getJSONArray(0).get(0));
        assertEquals("2", eventData.getJSONArray(0).get(1));
        assertEquals("3", eventData.getJSONArray(0).get(2));
        assertEquals("3", eventData.getJSONArray(0).get(3));
        assertEquals("35.667", eventData.getJSONArray(0).get(4));
        assertEquals("0", eventData.getJSONArray(0).get(5));
        assertEquals("0", eventData.getJSONArray(0).get(6));
        assertEquals("0", eventData.getJSONArray(0).get(7));
        assertEquals("0", eventData.getJSONArray(0).get(8));
        assertEquals("3", eventData.getJSONArray(0).get(9));
        assertEquals("3", eventData.getJSONArray(0).get(10));
        assertEquals("1", eventData.getJSONArray(0).get(11));

    }

    /**
     * Method: detail()
     */
    @Test
    public void testDetail() throws Exception {
        String resultDay = use("/app/event/detail?start_dt=2017-01-01&end_dt=2017-01-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray detailData = jsonResultDay.getJSONArray("detailData");

        assertEquals(3, detailData.size());
        assertEquals(1, jsonResultDay.size());
        assertEquals("2017-01-01", detailData.getJSONArray(0).get(0));
        assertEquals("手机充值", detailData.getJSONArray(0).get(1));
    }

    /**
     * Method: tendency()
     */
    @Test
    public void testTendency() throws Exception {
        String resultDay = use("/app/event/tendency?start_dt=2017-01-01&end_dt=2017-01-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray tendencyData = jsonResultDay.getJSONArray("tendencyData");

        assertEquals(3, tendencyData.size());
        assertEquals(1, jsonResultDay.size());
        assertEquals("2017-01-01", tendencyData.getJSONArray(0).get(0));
        assertEquals("10-nbm-sjcz", tendencyData.getJSONArray(0).get(1));
        assertEquals("手机充值", tendencyData.getJSONArray(0).get(2));

    }


} 
