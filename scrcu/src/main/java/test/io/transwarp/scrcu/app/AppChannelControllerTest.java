package test.io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import test.io.transwarp.scrcu.JFinalConfigTest;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

import static org.junit.Assert.assertEquals;

/**
 * AppChannelController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 24, 2017</pre>
 */
public class AppChannelControllerTest extends ControllerTestCase<JFinalConfigTest> {

    /**
     * Method: list()
     */
    @Test
    public void testList() throws Exception {
        String resultDay = use("/app/channel/list?start_dt=2015-11-30&end_dt=2015-12-01&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataTime = jsonResultDay.getJSONArray("data");

        assertEquals(1, dataTime.size());
        assertEquals(2, jsonResultDay.size());
        assertEquals("2015-11-30", dataTime.getJSONArray(0).get(0));
        assertEquals("07", dataTime.getJSONArray(0).get(1));
        assertEquals("8963", dataTime.getJSONArray(0).get(2));
        assertEquals("64105", dataTime.getJSONArray(0).get(3));
        assertEquals("61502", dataTime.getJSONArray(0).get(4));
    }

    /**
     * Method: detail()
     */
    @Test
    public void testDetail() throws Exception {
        String resultDay = use("/app/channel/detail?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataTime = jsonResultDay.getJSONArray("data");

        assertEquals(2, dataTime.size());
        assertEquals(2, jsonResultDay.size());
        assertEquals("2015-11-01", dataTime.getJSONArray(0).get(0));
        assertEquals("07", dataTime.getJSONArray(0).get(1));
    }


} 
