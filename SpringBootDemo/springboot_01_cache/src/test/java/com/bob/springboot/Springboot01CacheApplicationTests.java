package com.bob.springboot;

import com.bob.springboot.bean.Employee;
import com.bob.springboot.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

	@Autowired
	EmployeeMapper employeeMapper;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
  RedisTemplate<Object, Employee> empRedisTemplate;



	@Test
	public void test01() {
//		stringRedisTemplate.opsForValue().append("msg", "hello");
    String msg = stringRedisTemplate.opsForValue().get("msg");
    System.out.println(msg);
  }

  @Test
  public void test02() {
    Employee emp = employeeMapper.getEmpById(1);
//	  redisTemplate.opsForValue().set("emp-01", emp);
	  empRedisTemplate.opsForValue().set("emp-01", emp);
  }

	@Test
	public void contextLoads() {
		Employee emp = employeeMapper.getEmpById(1);
		System.out.println(emp);
	}

}

