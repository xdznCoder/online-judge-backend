package cn.xdzn.oj.service.judge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 判题应用程序
 *
 * @author Onism
 * @date 2024/10/19
 */
@EnableDiscoveryClient
@ComponentScan("cn.xdzn.oj")
@EnableFeignClients(basePackages = "cn.xdzn.oj")
@SpringBootApplication
public class JudgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(JudgeApplication.class,args);
    }
}
