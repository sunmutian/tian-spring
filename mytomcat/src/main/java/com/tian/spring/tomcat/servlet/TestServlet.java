package com.tian.spring.tomcat.servlet;

import com.tian.spring.tomcat.http.LawtRequest;
import com.tian.spring.tomcat.http.LawtResponse;
import com.tian.spring.tomcat.http.LawtServlet;

/**
 * 测试
 *
 * @author tianweichang
 * @date 2018-07-09 10:32
 **/
public class TestServlet extends LawtServlet {

    @Override
    public void doGet(LawtRequest request, LawtResponse response) throws Exception {
        this.doPost(request, response);
    }

    //在这里处理自己所有的逻辑
    @Override
    public void doPost(LawtRequest request, LawtResponse response) throws Exception {
        System.out.println("调用TestServlet成功");

        response.write("调用TestServlet成功");
    }
}
