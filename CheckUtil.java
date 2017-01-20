package com.incrediable.wechat.util;


import java.security.MessageDigest;
import java.util.Arrays;


/**
 * Created by mac on 2017/1/12.
 */
public class CheckUtil {
    public static final String token="incrediable";
    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String arr[]=new String[]{token,timestamp,nonce};
        Arrays.sort(arr);
        StringBuffer content=new StringBuffer();
        for(String attr:arr){
            content.append(attr);
        }
        String tmpSig=getSha1(content.toString());
        return tmpSig.equals(signature);
    }
    public static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
