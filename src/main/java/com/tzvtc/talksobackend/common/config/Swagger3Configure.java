package com.tzvtc.talksobackend.common.config;


import com.tzvtc.talksobackend.common.global.GlobalConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger3Configure {

  @Bean
  public Docket createRestApi() {
    ApiInfo apiInfo = new ApiInfoBuilder()
        .title(GlobalConstants.APP_NAME)
        .version(String.valueOf(GlobalConstants.VERSION))
        .build();

    return new Docket(DocumentationType.OAS_30)
        .apiInfo(apiInfo);
  }
}
