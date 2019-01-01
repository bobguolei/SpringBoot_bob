package com.bob.springboot.config;

import com.bob.springboot.bean.Department;
import com.bob.springboot.bean.Employee;
import java.net.UnknownHostException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class MyRedisConfig {

  @Bean
  public RedisTemplate<Object, Employee> empRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    RedisTemplate<Object, Employee> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
    template.setDefaultSerializer(serializer);
    return template;
  }

  @Bean
  public RedisTemplate<Object, Department> deptRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    RedisTemplate<Object, Department> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    Jackson2JsonRedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<Department>(Department.class);
    template.setDefaultSerializer(serializer);
    return template;
  }

  @Primary
  @Bean
  public RedisCacheManager employeeCacheManager(RedisTemplate<Object, Employee> empRedisTemplate) {
    RedisCacheManager redisCacheManager = new RedisCacheManager(empRedisTemplate);
    redisCacheManager.setUsePrefix(true);

    return redisCacheManager;
  }

  @Bean
  public RedisCacheManager departmentCacheManager(RedisTemplate<Object, Department> deptRedisTemplate) {
    RedisCacheManager redisCacheManager = new RedisCacheManager(deptRedisTemplate);
    redisCacheManager.setUsePrefix(true);

    return redisCacheManager;
  }

}
