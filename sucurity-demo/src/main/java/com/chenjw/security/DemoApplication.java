package com.chenjw.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: TODO
 * @author: 陈建伟
 * @date: 2020-02-15
 */
@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

    /**
     * @param args
     * @throws
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author: 陈建伟
     * @date: 2020-02-15
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
