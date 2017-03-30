package test.io.transwarp.scrcu.portal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import test.io.transwarp.scrcu.JFinalConfigTest;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

import static org.junit.Assert.assertEquals;

/**
 * SiteAnalysisController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 28, 2017</pre>
 */
public class SiteAnalysisControllerTest extends ControllerTestCase<JFinalConfigTest> {

    /**
     * Method: visitAnalysis()
     */
    @Test
    public void testVisitAnalysis() throws Exception {

        String resultDay = use("/portal/siteAnalysis/visitAnalysis?start_dt=2017-02-04&end_dt=2017-02-04&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray timeData = jsonResultDay.getJSONArray("timeData");
        JSONArray depthData = jsonResultDay.getJSONArray("depthData");

        assertEquals(4, jsonResultDay.size());

        assertEquals(4, timeData.size());
        assertEquals("2017-02-04", timeData.getJSONArray(0).get(0));
        assertEquals("10~30分钟", timeData.getJSONArray(0).get(1));

        assertEquals(3, depthData.size());
        assertEquals("2~4", depthData.getJSONArray(0).get(1));
        assertEquals("258", depthData.getJSONArray(0).get(2));
    }

    /**
     * Method: pageRank()
     */
    @Test
    public void testPageRank() throws Exception {

        String resultDay = use("/portal/siteAnalysis/pageRank?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray guestData = jsonResultDay.getJSONArray("guestData");

        assertEquals(1, jsonResultDay.size());

        assertEquals(557, guestData.size());
        assertEquals("2015-11-01", guestData.getJSONArray(0).get(0));
        assertEquals("http://www.scrcu.com/", guestData.getJSONArray(0).get(1));
    }

    /**
     * Method: flowTrend()
     */
    @Test
    public void testFlowTrend() throws Exception {

        String resultDay = use("/portal/siteAnalysis/flowTrend?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataFlowTrend = jsonResultDay.getJSONArray("dataFlowTrend");
        JSONArray dataNewVisitor = jsonResultDay.getJSONArray("dataNewVisitor");

        assertEquals(4, jsonResultDay.size());

        assertEquals(1, dataFlowTrend.size());
        assertEquals("2015-11-01", dataFlowTrend.getJSONArray(0).get(0));
        assertEquals("999", dataFlowTrend.getJSONArray(0).get(1));
        assertEquals("185907", dataFlowTrend.getJSONArray(0).get(2));

        assertEquals(1, dataNewVisitor.size());
        assertEquals("70442", dataNewVisitor.getJSONArray(0).get(1));
        assertEquals("3.594", dataNewVisitor.getJSONArray(0).get(2));
    }

    /**
     * Method: adTrend()
     */
    @Test
    public void testAdTrend() throws Exception {

        String resultDay = use("/portal/siteAnalysis/adTrend?start_dt=2017-02&end_dt=2017-03&dateType=month").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray adData = jsonResultDay.getJSONArray("adData");

        assertEquals(1, jsonResultDay.size());

        assertEquals(2, adData.size());
        assertEquals("2017-02", adData.getJSONArray(0).get(0));
        assertEquals("", adData.getJSONArray(0).get(1));
        assertEquals("http://www.scrcu.com/", adData.getJSONArray(0).get(2));

    }

    /**
     * Method: real()
     */
    @Test
    public void testReal() throws Exception {

        String resultDay = use("/portal/siteAnalysis/real?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray data = jsonResultDay.getJSONArray("data");

        assertEquals(1, jsonResultDay.size());

        assertEquals(0, data.size());
    }

} 
