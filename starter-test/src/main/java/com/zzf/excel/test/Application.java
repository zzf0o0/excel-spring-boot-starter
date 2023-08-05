package com.zzf.excel.test;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zzf
 * @description: 启动类
 */
@SpringBootApplication(scanBasePackages = {"com.zzf"})
@Configurable
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
