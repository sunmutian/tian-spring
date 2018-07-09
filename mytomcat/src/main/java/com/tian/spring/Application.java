package com.tian.spring;

import com.tian.spring.tomcat.catalina.LawtTomcat;

/**
 * Hello world!
 */
public class Application {
    public static void main(String[] args) {
        System.out.println("Start to start my Tomcat!");
        LawtTomcat tomcat = new LawtTomcat(8011);
        tomcat.start();
        System.out.println("my Tomcat start succeed!");
    }
}
