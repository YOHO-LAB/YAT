package cn.yat.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.concurrent.ThreadLocalRandom;

public class JdbcUtil {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String MYSQL_CONFIG = "?useUnicode=true&amp;characterEncoding=utf-8";
    private static final int MAX_GET_ROW_COUNT = 10;
    private static final int MAX_QUERY_ROW_COUNT = 10000;

    public static String query(String ip, int port, String db , String user , String password , String sql,String columnName,int getValType) throws Exception{
        String res = "";
        sql = sql.trim();
        if(!sql.toLowerCase().startsWith("select")){
            throw new Exception("【Error】只支持select操作！");
        }
        Class.forName(DRIVER);//指定连接类型
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+db+MYSQL_CONFIG, user, password);//获取连接
        ResultSet rs = connection.createStatement().executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData() ;
        int columnCount = rsmd.getColumnCount();
        int getColumnIdx = 0;
        for(int i=1;i<=columnCount;i++){
            if(rsmd.getColumnName(i).equals(columnName)){
                getColumnIdx = i;
                break;
            }
        }
        if(getColumnIdx == 0){
            throw new Exception("[Error]:列名 "+columnName+" ,不存在！");
        }
        int rowCount = 0;
        while(rs.next()){
            if(getValType == 1){
                res = rs.getString(getColumnIdx);
                break;
            }
            if(getValType == 2){
                res = rs.getString(getColumnIdx);
            }
            if(getValType == 3){
                res = rs.getString(getColumnIdx);
                int rand = ThreadLocalRandom.current().nextInt(100);
                if(rand < 30){
                    break;
                }
            }
            rowCount ++;
            if(rowCount >= MAX_QUERY_ROW_COUNT){
                throw new Exception("[Exception]:返回结果行数过多，超过阈值 "+MAX_QUERY_ROW_COUNT+"行");
            }
        }
        return res;
    }

    public static JSONObject query(String ip, int port, String db , String user , String password , String sql) throws Exception{
        JSONObject res = new JSONObject();
        sql = sql.trim();
        if(!sql.toLowerCase().startsWith("select")){
            throw new Exception("【Error】只支持select操作！");
        }
        Class.forName(DRIVER);//指定连接类型
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+db+MYSQL_CONFIG, user, password);//获取连接
        ResultSet rs = connection.createStatement().executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData() ;
        int columnCount = rsmd.getColumnCount();
        JSONArray colNameArr = new JSONArray();
        for(int i=1;i<=columnCount;i++){
            colNameArr.add(rsmd.getColumnName(i));
        }
        res.put("colNameArr", colNameArr);
        JSONArray rowArr = new JSONArray();
        JSONObject data = new JSONObject();
        int rowCount = 0;
        while(rs.next()){
            JSONArray rowArrTmp = new JSONArray();
            for(int i=1;i<=columnCount;i++){
                String rsData = rs.getString(i);
                rowArrTmp.add(rsData);
                data.put(rsmd.getColumnName(i)+"["+rowCount+"]",rsData);
            }
            rowArr.add(rowArrTmp);
            rowCount ++;
            if(rowCount >= MAX_GET_ROW_COUNT){
                break;
            }
        }
        res.put("rowArr", rowArr);
        res.put("data", data);
        return res;
    }
    public static int update(String ip, int port, String db , String user , String password , String sql) throws Exception{
        sql = sql.trim();
        if(!sql.toLowerCase().startsWith("update") || !sql.toLowerCase().startsWith("insert")){
            throw new Exception("【Error】只支持update/insert操作！");
        }
        Class.forName(DRIVER);//指定连接类型
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+db+MYSQL_CONFIG, user, password);//获取连接
        int res = connection.createStatement().executeUpdate(sql);
        return res;
    }
}
