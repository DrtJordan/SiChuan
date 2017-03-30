package test.io.transwarp.scrcu.portal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import test.io.transwarp.scrcu.JFinalConfigTest;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

import static org.junit.Assert.assertEquals;

/**
 * EventAnalysisController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 28, 2017</pre>
 */
public class EventAnalysisControllerTest extends ControllerTestCase<JFinalConfigTest> {

    /**
     * Method: list()
     */
    @Test
    public void testList() throws Exception {
        String resultDay = use("/portal/eventAnalysis/list?start_dt=2017-01-01&end_dt=2017-01-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray eventData = jsonResultDay.getJSONArray("eventData");

        assertEquals(1, jsonResultDay.size());

        assertEquals(1, eventData.size());
        assertEquals("2017-01-01", eventData.getJSONArray(0).get(0));
        assertEquals("0", eventData.getJSONArray(0).get(1));
        assertEquals("3", eventData.getJSONArray(0).get(2));
        assertEquals("3", eventData.getJSONArray(0).get(3));
    }

    /**
     * Method: detail()
     */
    @Test
    public void testDetail() throws Exception {

        String resultDay = use("/portal/eventAnalysis/detail?start_dt=2017-01-01&end_dt=2017-01-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray detailData = jsonResultDay.getJSONArray("detailData");

        assertEquals(1, jsonResultDay.size());

        assertEquals(9, detailData.size());
        assertEquals("2017-01-01", detailData.getJSONArray(0).get(0));
        assertEquals("单次资金归集", detailData.getJSONArray(0).get(1));
        assertEquals("0", detailData.getJSONArray(0).get(2));
        assertEquals("0", detailData.getJSONArray(0).get(3));
    }

    /**
     * Method: tendency()
     */
    @Test
    public void testTendency() throws Exception {

        String resultDay = use("/portal/eventAnalysis/tendency?start_dt=2017-01-01&end_dt=2017-01-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray tendencyData = jsonResultDay.getJSONArray("tendencyData");

        assertEquals(1, jsonResultDay.size());

        assertEquals(9, tendencyData.size());
        assertEquals("2017-01-01", tendencyData.getJSONArray(0).get(0));
        assertEquals("单次资金归集", tendencyData.getJSONArray(0).get(1));
        assertEquals("0", tendencyData.getJSONArray(0).get(2));
        assertEquals("0", tendencyData.getJSONArray(0).get(3));
    }

} 
