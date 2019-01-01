package com.bob.springboot.config;

import com.bob.springboot.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAppConfig {

  @Bean
  public HelloService helloService() {
    System.out.println("配置Bean.....");
    return new HelloService();
  }

}
