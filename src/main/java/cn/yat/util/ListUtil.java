package cn.yat.util;

import cn.yat.entity.Operation;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class ListUtil {
    public static JSONArray getIdNameArray(List<Operation> list) throws Exception{
        JSONArray arr = new JSONArray();
        for(Operation operation : list){
            JSONObject obj = new JSONObject();
            obj.put("id",operation.getId());
            obj.put("name",operation.getName());
            arr.add(obj);
        }
        return arr;
    }
}
