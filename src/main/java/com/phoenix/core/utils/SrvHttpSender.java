package com.phoenix.core.utils;


import com.phoenix.core.exception.ServerException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SrvHttpSender {
    private static Logger logger = LoggerFactory.getLogger(SrvHttpSender.class);

    public SrvHttpSender() {
    }

    public static String pltFrontPost(String url, String paramContent) throws ServerException {
        return pltFrontPost(url, paramContent, 60000);
    }

    public static String pltFrontPost(String url, String paramContent, int timeout) throws ServerException {
        logger.info("url:" + url + " param:" + paramContent);
        DataOutputStream out = null;
        BufferedReader reader = null;
        String retStr = null;

        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
            conn.setRequestProperty("Content-Type", "text/html");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(timeout);
            conn.setRequestProperty("Charset", "utf-8");
            conn.connect();
            out = new DataOutputStream(conn.getOutputStream());
            out.write(EncryptionUtil.encode(paramContent).getBytes("utf-8"));
            out.flush();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = "";
            String ret = "";

            while(true) {
                if ((line = reader.readLine()) == null) {
                    retStr = EncryptionUtil.decode(ret);
                    break;
                }

                ret = ret + line;
            }
        } catch (SocketTimeoutException var18) {
            var18.printStackTrace();
            logger.error("url:" + url + " service error:", var18);
            throw new ServerException("05", "his超时");
        } catch (Exception var19) {
            var19.printStackTrace();
            logger.error("url:" + url + " service error:", var19);
            throw new ServerException("9009");
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (reader != null) {
                    reader.close();
                }
            } catch (Exception var17) {
                logger.error("url:" + url + " service error:", var17);
            }

        }

        logger.info("url:" + url + " result:" + retStr);
        return retStr;
    }

    public static void main(String[] args) throws ServerException {
        String json = "{\"rcptStreamNo\":\"11111111111\"}";
        System.out.println(DateUtil.getDefaultDate());
        String resJson = pltFrontPost("http://192.168.202.241:8080/srv_account/zzHosAcct/zzHosAcctConsumCancel", json);
        System.out.println(resJson);
        System.out.println(DateUtil.getDefaultDate());
    }
}

