package com.tian.spring.tomcat.catalina;

import com.tian.spring.tomcat.http.LawtRequest;
import com.tian.spring.tomcat.http.LawtResponse;
import com.tian.spring.tomcat.http.LawtServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @author tianweichang
 * @date 2018-07-09 9:56
 **/
public class LawtTomcat {
    /**
     * 端口号
     */
    private int port = 8080;

    private ServerSocket server;
    /**
     * key=url,value=**servlet
     */
    private Map<Pattern, Class<?>> servletMapping = new ConcurrentHashMap<>();

    private Properties webXml = new Properties();
    //获取WEB_INFO目录
    private String WEB_INF = this.getClass().getResource("/").getPath();

    public LawtTomcat() {
    }

    public LawtTomcat(int port) {
        this.port = port;
    }

    //在容器启动之前，要加载所有的配置文件
    private void init() {

        FileInputStream fis = null;
        try {

            fis = new FileInputStream(WEB_INF + "web.properties");

            webXml.load(fis);

            for (Object k : webXml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {

                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webXml.getProperty(key);

                    Pattern pattern = Pattern.compile(url);

                    String className = webXml.getProperty(servletName + ".className");

                    Class<?> servletClass = Class.forName(className);

                    servletMapping.put(pattern, servletClass);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //等待用户请求了
    private void process(Socket client) throws Exception {
        //首先要把Request和Respose搞出来
        //Request 实际上就是对我们的InputStream的一个封装
        //Respose OutputStream的封装
        InputStream is = client.getInputStream();
        OutputStream out = client.getOutputStream();

        LawtRequest request = new LawtRequest(is);
        LawtResponse response = new LawtResponse(out);

        //
        try {

            //此时此刻，还缺一个Serlvet
            //service(Request,Reponse)　　　　　doGet doPost

            //这个Servlet自己从来没有亲手new过？
            //通过读取web.xml 文件来获取自己的servlet
            //利用反射机制给new出来

            String url = request.getUrl();

            boolean isPattern = false;

            for (Map.Entry<Pattern, Class<?>> entry : servletMapping.entrySet()) {

                if (entry.getKey().matcher(url).matches()) {

                    LawtServlet servlet = (LawtServlet) entry.getValue().newInstance();
                    servlet.service(request, response);

                    isPattern = true;
                    break;
                }

            }

            if (!isPattern) {
                response.write("404 - Not Found");
            }

        } catch (Exception e) {
            response.write("500 ," + e.getMessage());
        } finally {
            is.close();
            out.close();
            //HTTP本身是无状态的协议
            client.close();
        }

    }

    public void start() {

        init();

        //BIO的写法,现在新的tomcat已经支持nio了
        try {
            server = new ServerSocket(this.port);

            System.out.println("GP Tomcat 已启动，监听端口是：" + this.port);

        } catch (IOException e) {
//			e.printStackTrace();
            System.out.println("GP Tomcat 启动失败，" + e.getMessage());
            return;
        }

        while (true) {

            //容器能够持续提供服务了
            try {
                Socket client = server.accept();

                process(client);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
