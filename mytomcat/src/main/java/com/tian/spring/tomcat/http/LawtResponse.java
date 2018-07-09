package com.tian.spring.tomcat.http;

import java.io.OutputStream;

/**
 * 响应参数
 *
 * @author tianweichang
 * @date 2018-07-09 9:46
 **/
public class LawtResponse {

    private OutputStream os;

    public LawtResponse(OutputStream os) {
        this.os = os;
    }

    public void write(String outStr) throws Exception {
        os.write(outStr.getBytes());
        os.flush();
    }
}
