package com.st;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: TimeApplication
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/8 0:01
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.st.mapper")
@EnableTransactionManagement
@EnableCaching //开启注解缓存功能
@EnableScheduling
public class TimeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimeApplication.class,args);
        log.info("项目启动");
    }
}
