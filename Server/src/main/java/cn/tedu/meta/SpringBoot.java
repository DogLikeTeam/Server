package cn.tedu.meta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by LYJ on 2017/10/17.
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("cn.tedu.meta.mapper")  //扫描接口 为接口创建代理对象
public class SpringBoot {

    public static void main(String[] args){
        SpringApplication.run(SpringBoot.class,args);
    }
}
