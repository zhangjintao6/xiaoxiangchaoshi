package com.example.xiaoxiangshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.example.xiaoxiangshop.demos.dao")
public class XiaoxiangshopApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(XiaoxiangshopApplication.class, args);
    }
    //继承SpringBootServletInitializer，起到web.xml作用
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(XiaoxiangshopApplication.class);
    }

}

