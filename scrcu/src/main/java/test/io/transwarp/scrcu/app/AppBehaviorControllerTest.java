package test.io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import test.io.transwarp.scrcu.JFinalConfigTest;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

import static org.junit.Assert.assertEquals;

/**
 * AppBehaviorController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 24, 2017</pre>
 */
public class AppBehaviorControllerTest extends ControllerTestCase<JFinalConfigTest> {

    /**
     * Method: useTime()
     */
    @Test
    public void testUseTime() throws Exception {
        String resultDay = use("/app/behavior/useTime?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);

        JSONArray dataTime = jsonResultDay.getJSONArray("dataTime");
        JSONArray dataPhone = jsonResultDay.getJSONArray("dataPhone");
        JSONArray dataOs = jsonResultDay.getJSONArray("dataOs");
        JSONArray dataChannel = jsonResultDay.getJSONArray("dataChannel");

        assertEquals(5, jsonResultDay.size());

        assertEquals(18, dataTime.size());
        assertEquals("2015-11-01", dataTime.getJSONArray(0).get(0));
        assertEquals("1-3分", dataTime.getJSONArray(0).get(1));
        assertEquals("7481", dataTime.getJSONArray(0).get(2));
        assertEquals("0.0322", dataTime.getJSONArray(0).get(3));

        assertEquals(86, dataPhone.size());
        assertEquals("2015-11-01", dataPhone.getJSONArray(0).get(0));
        assertEquals("1.6", dataPhone.getJSONArray(0).get(1));
        assertEquals("1-3分", dataPhone.getJSONArray(0).get(2));
        assertEquals("26", dataPhone.getJSONArray(0).get(3));

        assertEquals(18, dataOs.size());
        assertEquals("2015-11-01", dataOs.getJSONArray(0).get(0));
        assertEquals("Android", dataOs.getJSONArray(0).get(1));

        assertEquals(18, dataChannel.size());
        assertEquals("2015-11-01", dataChannel.getJSONArray(0).get(0));
        assertEquals("07", dataChannel.getJSONArray(0).get(1));
    }

    /**
     * Method: useRate()
     */
    @Test
    public void testUseRate() throws Exception {
        String resultDay = use("/app/behavior/useRate?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);

        JSONArray dataTime = jsonResultDay.getJSONArray("dataTime");
        JSONArray dataPhone = jsonResultDay.getJSONArray("dataPhone");
        JSONArray dataOs = jsonResultDay.getJSONArray("dataOs");
        JSONArray dataChannel = jsonResultDay.getJSONArray("dataChannel");

        assertEquals(5, jsonResultDay.size());

        assertEquals(12, dataTime.size());
        assertEquals("2015-11-01", dataTime.getJSONArray(0).get(0));
        assertEquals("1-2次", dataTime.getJSONArray(0).get(1));
        assertEquals("26385", dataTime.getJSONArray(0).get(2));
        assertEquals("0.4746", dataTime.getJSONArray(0).get(3));

        assertEquals(60, dataPhone.size());
        assertEquals("2015-11-01", dataPhone.getJSONArray(0).get(0));
        assertEquals("1.6", dataPhone.getJSONArray(0).get(1));
        assertEquals("205", dataPhone.getJSONArray(0).get(3));

        assertEquals(12, dataOs.size());
        assertEquals("2015-11-01", dataOs.getJSONArray(0).get(0));
        assertEquals("Android", dataOs.getJSONArray(0).get(1));
        assertEquals("26385", dataOs.getJSONArray(0).get(3));

        assertEquals(12, dataChannel.size());
        assertEquals("2015-11-01", dataChannel.getJSONArray(0).get(0));
        assertEquals("07", dataChannel.getJSONArray(0).get(1));
        assertEquals("26385", dataChannel.getJSONArray(0).get(3));

    }

    /**
     * Method: depth()
     */
    @Test
    public void testDepth() throws Exception {
        String resultDay = use("/app/behavior/depth?start_dt=2015-11-20&end_dt=2015-11-21&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);

        JSONArray dataTime = jsonResultDay.getJSONArray("dataTime");
        JSONArray dataPhone = jsonResultDay.getJSONArray("dataPhone");
        JSONArray dataOs = jsonResultDay.getJSONArray("dataOs");
        JSONArray dataChannel = jsonResultDay.getJSONArray("dataChannel");

        assertEquals(5, jsonResultDay.size());

        assertEquals(6, dataTime.size());
        assertEquals("2015-11-20", dataTime.getJSONArray(0).get(0));
        assertEquals("1-2页面", dataTime.getJSONArray(0).get(1));
        assertEquals("1654", dataTime.getJSONArray(0).get(2));
        assertEquals("0.0102", dataTime.getJSONArray(0).get(3));

        assertEquals(54, dataPhone.size());
        assertEquals("2015-11-20", dataPhone.getJSONArray(0).get(0));
        assertEquals("1.6", dataPhone.getJSONArray(0).get(1));
        assertEquals("2", dataPhone.getJSONArray(0).get(3));

        assertEquals(6, dataOs.size());
        assertEquals("2015-11-20", dataOs.getJSONArray(0).get(0));
        assertEquals("Android", dataOs.getJSONArray(0).get(1));
        assertEquals("1654", dataOs.getJSONArray(0).get(3));

        assertEquals(6, dataChannel.size());
        assertEquals("2015-11-20", dataChannel.getJSONArray(0).get(0));
        assertEquals("07", dataChannel.getJSONArray(0).get(1));
        assertEquals("1654", dataChannel.getJSONArray(0).get(3));
    }

    /**
     * Method: interval()
     */
    @Test
    public void testInterval() throws Exception {
        String resultDay = use("/app/behavior/interval?start_dt=2015-11-01&end_dt=2015-11-10&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);

        JSONArray dataTime = jsonResultDay.getJSONArray("dataTime");
        JSONArray dataPhone = jsonResultDay.getJSONArray("dataPhone");
        JSONArray dataOs = jsonResultDay.getJSONArray("dataOs");
        JSONArray dataChannel = jsonResultDay.getJSONArray("dataChannel");

        assertEquals(5, jsonResultDay.size());

        assertEquals(45, dataTime.size());
        assertEquals("2015-11-01", dataTime.getJSONArray(0).get(0));
        assertEquals("0-24h", dataTime.getJSONArray(0).get(1));
        assertEquals("177660", dataTime.getJSONArray(0).get(2));
        assertEquals("0.7664", dataTime.getJSONArray(0).get(3));

        assertEquals(350, dataPhone.size());
        assertEquals("2015-11-01", dataPhone.getJSONArray(0).get(0));
        assertEquals("1.6", dataPhone.getJSONArray(0).get(1));
        assertEquals("2258", dataPhone.getJSONArray(0).get(3));

        assertEquals(45, dataOs.size());
        assertEquals("2015-11-01", dataOs.getJSONArray(0).get(0));
        assertEquals("Android", dataOs.getJSONArray(0).get(1));
        assertEquals("177660", dataOs.getJSONArray(0).get(3));

        assertEquals(45, dataChannel.size());
        assertEquals("2015-11-01", dataChannel.getJSONArray(0).get(0));
        assertEquals("07", dataChannel.getJSONArray(0).get(1));
        assertEquals("177660", dataChannel.getJSONArray(0).get(3));
    }

    /**
     * Method: area()
     */
    @Test
    public void testArea() throws Exception {
        String resultDay = use("/app/behavior/area?start_dt=2015-11-20&end_dt=2015-11-21&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);

        JSONArray dataTime = jsonResultDay.getJSONArray("data");
        JSONArray dataPhone = jsonResultDay.getJSONArray("dataAreaPhone");
        JSONArray dataOs = jsonResultDay.getJSONArray("dataAreaOs");
        JSONArray dataChannel = jsonResultDay.getJSONArray("dataAreaChannel");

        assertEquals(5, jsonResultDay.size());

        assertEquals(11, dataTime.size());
        assertEquals("南充市", dataTime.getJSONArray(0).get(0));
        assertEquals("2015-11-20", dataTime.getJSONArray(0).get(1));
        assertEquals("2", dataTime.getJSONArray(0).get(2));

        assertEquals(62, dataPhone.size());
        assertEquals("南充市", dataPhone.getJSONArray(0).get(0));
        assertEquals("2015-11-20", dataPhone.getJSONArray(0).get(1));
        assertEquals("1.6011", dataPhone.getJSONArray(0).get(2));

        assertEquals(11, dataOs.size());
        assertEquals("南充市", dataOs.getJSONArray(0).get(0));
        assertEquals("2015-11-20", dataOs.getJSONArray(0).get(1));
        assertEquals("Android", dataOs.getJSONArray(0).get(2));
        assertEquals("2", dataOs.getJSONArray(0).get(3));

        assertEquals(11, dataChannel.size());
        assertEquals("南充市", dataChannel.getJSONArray(0).get(0));
        assertEquals("2015-11-20", dataChannel.getJSONArray(0).get(1));
        assertEquals("07", dataChannel.getJSONArray(0).get(2));
        assertEquals("2", dataChannel.getJSONArray(0).get(3));
    }


} 
