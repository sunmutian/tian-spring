package com.tian.spring.tomcat.http;

import com.tian.spring.tomcat.http.LawtRequest;
import com.tian.spring.tomcat.http.LawtResponse;
import com.tian.spring.tomcat.util.ConstantUtils;

/**
 * 定义一个模板类
 *
 * @author tianweichang
 * @date 2018-07-09 9:45
 **/
public abstract class LawtServlet {

    public void service(LawtRequest request, LawtResponse response) throws Exception {

        //如果客户端发送的是GET请求，就调用doGet方法
        //如果是POST,调用doPost方法
        if (ConstantUtils.METHOD.GET.equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doGet(LawtRequest request, LawtResponse response) throws Exception;

    public abstract void doPost(LawtRequest request, LawtResponse response) throws Exception;
}
