package com.incrediable.wechat.util;


import com.incrediable.wechat.model.AccessToken;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
/**
 * Created by mac on 2017/1/13.
 */
public class AccessUtil {
    private static final String APPID="wx8dddb2f14b230129";
    private static final String APPSECRET = "99beae508c12135cd82ff123cb6bb729";

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";


    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static JSONObject doGetStr(String url) throws IOException {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url);
        JSONObject jsonObject=null;
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                HttpEntity entity=httpResponse.getEntity();
                String result= EntityUtils.toString(entity,"UTF-8");
                jsonObject=JSONObject.fromObject(result);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            httpClient.close();
        }
        return jsonObject;
    }

    /**
     * post请求
     * @param url
     * @param entityParams
     * @return
     */
    public static JSONObject doPostStr(String url,String entityParams){
        JSONObject jsonObject=null;
        CloseableHttpClient httpClient=HttpClients.createDefault();
        CloseableHttpResponse httpResponse=null;
        HttpPost httpPost=new HttpPost(url);
        //List<NameValuePair> params=new ArrayList<NameValuePair>();
        try{
            httpPost.setEntity(new StringEntity(entityParams,"UTF-8"));
            httpResponse=httpClient.execute(httpPost);
            String result=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
            jsonObject=JSONObject.fromObject(result);
        }catch (Exception e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * 获取 access_token
     * @return
     * @throws Exception
     */
    public static AccessToken getAccessToken()throws Exception{
        AccessToken token=new AccessToken();
        String url=ACCESS_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        JSONObject jsonObject=doGetStr(url);
        if(jsonObject!=null){
            token.setToken(jsonObject.getString("access_token"));
            token.setExpiresIn(jsonObject.getInt("expires_in"));
        }
        return token;
    }


}
