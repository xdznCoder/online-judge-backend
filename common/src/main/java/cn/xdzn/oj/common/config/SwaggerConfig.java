package cn.xdzn.oj.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Swagger 配置
 *
 * @author Onism
 * @date 2024/10/17
 */
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * OpenAPI 配置（元信息、安全协议）
     */
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                // 接口信息定义
                .info(new Info()
                        .title("虚动智能 OJ 系统")
                        .version("1.0.0")
                        .description("虚动智能 OJ 系统")
                        .contact(new Contact().name("虚动智能团队"))
                        .license(new License().name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                );
    }
}
