package cn.xdzn.oj.service.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 系统应用程序
 *
 * @author shelly
 * @date 2024/10/18
 */
@EnableDiscoveryClient
@ComponentScan("cn.xdzn.oj")
@EnableFeignClients(basePackages = "cn.xdzn.oj.common.client")
@SpringBootApplication
@EnableAsync
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
