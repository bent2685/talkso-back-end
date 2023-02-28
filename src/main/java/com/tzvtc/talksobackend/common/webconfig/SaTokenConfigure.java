package com.tzvtc.talksobackend.common.webconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
*
*sa-token配置类
*
* */

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {

  }
}
