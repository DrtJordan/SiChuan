package test.io.transwarp.scrcu.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import test.io.transwarp.scrcu.JFinalConfigTest;
import test.io.transwarp.scrcu.conf.ControllerTestCase;

import static org.junit.Assert.assertEquals;

/**
 * AppMemberController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 24, 2017</pre>
 */
public class AppMemberControllerTest extends ControllerTestCase<JFinalConfigTest> {

    /**
     * Method: memberPage()
     */
    @Test
    public void testMemberPage() throws Exception {
        String resultDay = use("/app/memberAnalysis/memberPage?start_dt=2015-11-01&end_dt=2015-11-02&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray data = jsonResultDay.getJSONArray("data");

        assertEquals(0, data.size());
        assertEquals(1, jsonResultDay.size());
//        assertEquals("2015-11-01", data.getJSONArray(0).get(0));
//        assertEquals("55592", data.getJSONArray(0).get(1));
    }

    /**
     * Method: memberRunOff()
     */
    @Test
    public void testMemberRunOff() throws Exception {
        String resultDay = use("/app/memberAnalysis/memberRunOff?start_dt=2017-01-26&end_dt=2017-01-27&dateType=day").invoke();

        JSONObject jsonResultDay = JSONObject.parseObject(resultDay);
        JSONArray data = jsonResultDay.getJSONArray("data");

        assertEquals(1, data.size());
        assertEquals(2, jsonResultDay.size());
        assertEquals("2017-01-26", data.getJSONArray(0).get(0));
        assertEquals("0", data.getJSONArray(0).get(1));
    }


} 
