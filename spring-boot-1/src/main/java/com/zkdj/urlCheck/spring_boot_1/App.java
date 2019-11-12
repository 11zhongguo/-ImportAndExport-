package com.zkdj.urlCheck.spring_boot_1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Hello world!
 *
 */
@EnableAutoConfiguration
@SpringBootConfiguration
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(value = "com.zkdj.urlCheck.spring_boot_1.main.java.mapper")
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class, args);
    }
}
