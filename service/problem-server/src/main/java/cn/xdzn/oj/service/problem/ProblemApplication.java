package cn.xdzn.oj.service.problem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 题目应用程序
 *
 * @author Onism
 * @date 2024/10/18
 */
@EnableDiscoveryClient
@ComponentScan("cn.xdzn.oj")
@EnableFeignClients(basePackages = "cn.xdzn.oj")
@SpringBootApplication
public class ProblemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProblemApplication.class,args);
    }
}
