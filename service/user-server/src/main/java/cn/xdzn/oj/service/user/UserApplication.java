package cn.xdzn.oj.service.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户应用程序
 *
 * @author Onism
 * @date 2024/10/19
 */
@EnableDiscoveryClient
@ComponentScan("cn.xdzn.oj")
@EnableFeignClients(basePackages = "cn.xdzn.oj")
@SpringBootApplication
@MapperScan("cn.xdzn.oj.service.user.infrastructure.dao")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
