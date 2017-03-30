package test.io.transwarp.scrcu.portal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import test.io.transwarp.scrcu.JFinalConfigTest;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

import static org.junit.Assert.assertEquals;

/**
 * UserAnalysisController Tester.
 *
 * @author hang_xiao
 * @version 1.0
 * @since <pre>三月 28, 2017</pre>
 */
public class UserAnalysisControllerTest extends ControllerTestCase<JFinalConfigTest> {

    /**
     * Method: area()
     */
    @Test
    public void testArea() throws Exception {
        String resultDay = use("/portal/userAnalysis/area?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray areaData = jsonResultDay.getJSONArray("areaData");

        assertEquals(2, jsonResultDay.size());

        assertEquals(22, areaData.size());
        assertEquals("乐山市", areaData.getJSONArray(0).get(0));
        assertEquals("3800", areaData.getJSONArray(0).get(1));
        assertEquals("0", areaData.getJSONArray(0).get(2));
        assertEquals("9279", areaData.getJSONArray(0).get(3));
        assertEquals("3874", areaData.getJSONArray(0).get(4));
    }

    /**
     * Method: terminal()
     */
    @Test
    public void testTerminal() throws Exception {
        String resultDay = use("/portal/userAnalysis/terminal?start_dt=2017-02-04&end_dt=2017-02-04&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray terminalOsData = jsonResultDay.getJSONArray("terminalOsData");
        JSONArray terminalBrowserData = jsonResultDay.getJSONArray("terminalBrowserData");

        assertEquals(4, jsonResultDay.size());

        assertEquals(11, terminalOsData.size());
        assertEquals("2017-02-04", terminalOsData.getJSONArray(0).get(0));
        assertEquals("Android", terminalOsData.getJSONArray(0).get(1));
        assertEquals("4294", terminalOsData.getJSONArray(0).get(2));

        assertEquals(5, terminalBrowserData.size());
        assertEquals("chrome", terminalBrowserData.getJSONArray(0).get(1));
        assertEquals("7559", terminalBrowserData.getJSONArray(0).get(2));
        assertEquals("13609", terminalBrowserData.getJSONArray(0).get(3));
    }

    /**
     * Method: userVisitPage()
     */
    @Test
    public void testUserVisitPage() throws Exception {
        String resultDay = use("/portal/userAnalysis/userVisitPage?start_dt=2017-02-04&end_dt=2017-02-04&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataPage = jsonResultDay.getJSONArray("dataPage");
        assertEquals(1, jsonResultDay.size());

        assertEquals(557, dataPage.size());
        assertEquals("2017-02-04", dataPage.getJSONArray(0).get(0));
    }

    /**
     * Method: userLoss()
     */
    @Test
    public void testUserLoss() throws Exception {
        String resultDay = use("/portal/userAnalysis/userLoss?start_dt=2017-02-04&end_dt=2017-02-05&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataLoss = jsonResultDay.getJSONArray("dataLoss");
        assertEquals(2, jsonResultDay.size());

        assertEquals(1, dataLoss.size());
        assertEquals("2017-02-04", dataLoss.getJSONArray(0).get(0));
        assertEquals("34981", dataLoss.getJSONArray(0).get(1));
    }

    /**
     * Method: userOnly()
     */
    @Test
    public void testUserOnly() throws Exception {
        String resultDay = use("/portal/userAnalysis/userOnly?start_dt=2015-11-01&end_dt=2017-02-04&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataUserOnly = jsonResultDay.getJSONArray("dataUserOnly");

        assertEquals(2, jsonResultDay.size());

        assertEquals(3, dataUserOnly.size());
        assertEquals("2015-11-01", dataUserOnly.getJSONArray(0).get(0));
        assertEquals("35221", dataUserOnly.getJSONArray(0).get(1));
        assertEquals("2026", dataUserOnly.getJSONArray(0).get(2));
    }


} 
