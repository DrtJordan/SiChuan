package io.transwarp.scrcu.tag;

/**
 * Created by TANG_0722 on 2017-02-27.
 */
public class ConditionUtil {

    public static StringBuffer value = new StringBuffer("");

    public static StringBuffer rangToRang(String str1, String str2, String str3, String  str4, String str5, String str6){
        value = value.delete(0, value.length());
        value = value.append("('").append(str1).append("','").append(str2).append("','").append(str3).append("','").append(str4).append("','").append(str5).append("','").append(str6).append("');");
        return value;
    }

    public static StringBuffer check(String str1, String str2, String str3, String  str4, String str5){
        value = value.delete(0, value.length());
        value = value.append("('").append(str1).append("','").append(str2).append("','").append(str3).append("','").append(str4).append("','").append(str5).append("');");
        return value;
    }
}
