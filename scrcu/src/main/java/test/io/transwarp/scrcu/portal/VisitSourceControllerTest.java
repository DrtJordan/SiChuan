package test.io.transwarp.scrcu.portal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import test.io.transwarp.scrcu.JFinalConfigTest;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

import static org.junit.Assert.assertEquals;

/**
 * VisitSourceController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 28, 2017</pre>
 */
public class VisitSourceControllerTest extends ControllerTestCase<JFinalConfigTest> {

    /**
     * Method: searchEngine()
     */
    @Test
    public void testSearchEngine() throws Exception {
        String resultDay = use("/portal/visitSource/searchEngine?start_dt=2017-02-04&end_dt=2017-02-04&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray searchEngineData = jsonResultDay.getJSONArray("data");

        assertEquals(2, jsonResultDay.size());

        assertEquals(5, searchEngineData.size());
        assertEquals("2017-02-04", searchEngineData.getJSONArray(0).get(0));
        assertEquals("360", searchEngineData.getJSONArray(0).get(1));
        assertEquals("4", searchEngineData.getJSONArray(0).get(2));
    }

    /**
     * Method: source()
     */
    @Test
    public void testSource() throws Exception {
        String resultDay = use("/portal/visitSource/source?start_dt=2017-02-04&end_dt=2017-02-04&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataSource = jsonResultDay.getJSONArray("dataSource");

        assertEquals(1, jsonResultDay.size());

        assertEquals(557, dataSource.size());
        assertEquals("2017-02-04", dataSource.getJSONArray(0).get(0));
        assertEquals("外部链接", dataSource.getJSONArray(0).get(1));
    }

    /**
     * Method: entryPage()
     */
    @Test
    public void testEntryPage() throws Exception {
        String resultDay = use("/portal/visitSource/entryPage?start_dt=2017-02-04&end_dt=2017-02-04&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray dataPage = jsonResultDay.getJSONArray("dataPage");

        assertEquals(1, jsonResultDay.size());

        assertEquals(261, dataPage.size());
        assertEquals("2017-02-04", dataPage.getJSONArray(0).get(0));
        assertEquals("http://www.scrcu.com/", dataPage.getJSONArray(0).get(1));
        assertEquals("20048", dataPage.getJSONArray(0).get(2));
    }


} 
