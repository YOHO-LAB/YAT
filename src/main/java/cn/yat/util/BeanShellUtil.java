package cn.yat.util;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.TargetError;

import java.util.Map;

/**
 * Created by rong.gao on 2018/5/11.
 */
public class BeanShellUtil {

    public static Object execute(Map<String,String> vars,Map<String,String> varsHttp,String[] args,String javaCode) throws Exception{
        Interpreter interpreter = new Interpreter();
        Object ret ;
        try {
            interpreter.set("vars",vars);
            interpreter.set("varsHttp",varsHttp);
            interpreter.set("bsh.args",args);
            ret = interpreter.eval(javaCode);
        } catch ( TargetError e ) {
            throw new Exception("The script or code called by the script threw an exception: "+ e.getTarget());
        } catch ( EvalError e2 ) {
            throw new Exception("There was an error in evaluating the script:" + e2);
        }
        return ret;
    }
}
