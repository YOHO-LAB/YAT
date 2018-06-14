package cn.yat.util;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by rong.gao on 2018/6/13.
 */
public class CmdUtil {
    public static void doCmd(String cmd) throws Exception{
        Runtime.getRuntime().exec(cmd).waitFor();
    }
    public static List<String> doCmdList(String cmd) throws Exception{
        List<String> list = Lists.newArrayList();
        Runtime run = Runtime.getRuntime();
        Process process = run.exec(cmd);
        InputStream in = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String s = br.readLine();
        while (s != null) {
            list.add(s);
            s = br.readLine();
        }
        br.close();
        process.waitFor();
        return list;
    }
    public static String doCmdStr(String cmd) throws Exception{
        StringBuffer sb = new StringBuffer();
        Runtime run = Runtime.getRuntime();
        Process process = run.exec(cmd);
        InputStream in = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String s = br.readLine();
        while (s != null) {
            sb.append(s + "\n");
            s = br.readLine();
        }
        br.close();
        process.waitFor();
        return sb.toString();
    }
}
