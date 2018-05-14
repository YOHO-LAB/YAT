package cn.yat.util;

import cn.yat.myentity.LogEntity;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class LogUtil {
    private static Map<Integer,LogEntity> logEntityMap = Maps.newHashMap();
    private static Map<String,JSONArray> logMap = Maps.newHashMap();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static Map<String,JSONArray> getLogMap() throws Exception{
        return logMap;
    }
    public static Map<Integer,LogEntity> getLogEntityMap() throws Exception{
        return logEntityMap;
    }
    public static LogEntity addLogEntity(int runId) {
        LogEntity oLogEntity = new LogEntity();
        logEntityMap.put(runId,oLogEntity);
        return oLogEntity;
    }
    public static LogEntity getLogEntity(int runId) {
        LogEntity oLogEntity = null;
        if(logEntityMap.containsKey(runId)){
            oLogEntity = logEntityMap.get(runId);
        }
        return oLogEntity;
    }
    public static void removeLogEntity(int runId) {
        if(logEntityMap.containsKey(runId)){
            logEntityMap.remove(runId);
        }
    }
    public static void addLog(String uuid , String title,String content , String color,String bgColor,String href) {
        if(uuid != null){
            JSONArray arr;
            if(logMap.containsKey(uuid)){
                arr = logMap.get(uuid);
            }else{
                arr = new JSONArray();
                logMap.put(uuid,arr);
            }
            JSONObject object = makeJsonObject(title,content , color,bgColor,href);
            arr.add(object);
        }
    }
    private static JSONObject makeJsonObject(String title,String content , String color,String bgColor,String href) {
        JSONObject object = new JSONObject();
        object.put("time",sdf.format(new Date()));
        object.put("title",title);
        object.put("content",content);
        object.put("length",content.length());
        object.put("style", JSONObject.parse("{\"color\":\""+color+"\",\"background-color\":\""+bgColor+"\"}"));
        object.put("href",href);
        return object;
    }
    public static void flushLog(String parentDir,String uuid) {
        try{
            if(uuid != null && parentDir != null){
                if(!logMap.containsKey(uuid)){
                    throw new Exception("日志不存在，uuid="+uuid);
                }
                File f = new File(parentDir);
                if(!f.exists()){
                    f.mkdirs();
                }
                if(!f.isDirectory()){
                    throw new Exception("日志路径错误，logDir="+parentDir);
                }

                String logFileFullPath = parentDir+uuid;
                FileWriter fw = new FileWriter(logFileFullPath);
                fw.write(logMap.get(uuid).toJSONString());
                fw.close();
                logMap.remove(uuid);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static JSONArray getLog(String parentDir,String uuid) throws Exception{
        if(uuid != null){
            if(logMap.containsKey(uuid)){
                return logMap.get(uuid);
            }else{
                String logFileFullPath = parentDir+uuid;
                FileReader fr = new FileReader(logFileFullPath);
                int ch;
                StringBuffer sb = new StringBuffer();
                while((ch = fr.read())!=-1 ){
                    sb.append((char)ch);
                }
                return JSONArray.parseArray(sb.toString());
            }
        }else{
            return null;
        }
    }
}