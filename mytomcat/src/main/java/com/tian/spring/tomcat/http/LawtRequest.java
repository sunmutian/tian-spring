package com.tian.spring.tomcat.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数
 *
 * @author tianweichang
 * @date 2018-07-09 9:45
 **/
public class LawtRequest {
    /**
     * 请求url
     */
    private String url;
    /**
     * get/post/...
     */
    private String method;
    /**
     * 请求参数
     */
    private Map<String, Object> parametersMap = new HashMap<>();

    public LawtRequest(InputStream is) {
        String content = "";
        byte[] buff = new byte[1024];

        try {
            int len;
            if ((len = is.read(buff)) > 0) {
                content = new String(buff, 0, len);
            }
            System.out.println(content);
            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");

            this.method = arr[0];
            if (arr[1] != null && arr[1].contains("?")) {
                //http://localhot:8080/test.do?userName=1&&userId=10
                this.url = arr[1].split("\\?")[0];
                parametersMap = convertParameters(arr[1].split("\\?")[1]);
            } else {
                this.url = arr[1];
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> convertParameters(String s) {
        System.out.println(s);
        return null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
