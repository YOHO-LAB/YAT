package cn.yat.util;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

public class MD5 {

    public static String md5(String message) {
        String res;
        try {
            res = new String(Hex.encodeHex(md5Digest(message)));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return res;
    }

    private static byte[] md5Digest(String message) throws Exception{
        byte[] md5Bytes ;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md5Bytes = md.digest(message.getBytes(Charsets.UTF_8));
        return md5Bytes;
    }

}
