package cn.yat.util;

import com.google.common.collect.ImmutableList;
import java.util.*;

public class SignatureVerifyUtil {

    //验签算法
    private final static String VERIFY_ARITHMETIC = "HmacSHA256";

    //客户端加签使用双秘钥
    public final static String VERIFY_TPYE_DOUBLE = "";

    //客户端加签使用单秘钥
    public final static String VERIFY_TPYE_SINGLE = "";

    //静态模式key
    private static String singleKey = "YOUR SINGLE KEY";

    public static String getPramsString(Map<String, String> reqParams) {
        ImmutableList list = ImmutableList.of();
        SortedMap<String, String> filtedMap = new TreeMap<>();
        for (Map.Entry<String, String> entry : reqParams.entrySet()) {
            String k = entry.getKey();
            if (!list.contains(k)) {
                filtedMap.put(k, entry.getValue());
            }
        }
        //string: k1=v1&k2=v2
        List<String> array = new LinkedList<>();
        for (Map.Entry<String, String> entry : filtedMap.entrySet()) {
            String pair = entry.getKey() + "=" + entry.getValue();
            array.add(pair.trim());
        }
        String paramStr = String.join("&", array);
        return paramStr;
    }
    public static String getSignature(int projectId,String envUrl,String sortedParamStr,Map<String,String> paramMap,int socketTimeout,int connectTimeout) throws Exception{

        return "YOUR SIGNATURE";
    }
}
