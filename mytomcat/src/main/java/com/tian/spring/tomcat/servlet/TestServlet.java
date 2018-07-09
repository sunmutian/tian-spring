package com.tian.spring.tomcat.servlet;

import com.tian.spring.tomcat.http.LawtRequest;
import com.tian.spring.tomcat.http.LawtResponse;
import com.tian.spring.tomcat.http.LawtServlet;

import java.util.Map;

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
        //把请求参数打印出来
        Map<String, Object> param = request.getParametersMap();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        response.write("调用TestServlet成功");
    }
}
