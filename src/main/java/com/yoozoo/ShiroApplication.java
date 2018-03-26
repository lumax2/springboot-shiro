package com.yoozoo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Hao on 2018/3/26.
 */
@SpringBootApplication
@MapperScan("com.yoozoo.mapper")
public class ShiroApplication {
    public static void main(String[] args){
        SpringApplication.run(ShiroApplication.class,args);
    };
}
