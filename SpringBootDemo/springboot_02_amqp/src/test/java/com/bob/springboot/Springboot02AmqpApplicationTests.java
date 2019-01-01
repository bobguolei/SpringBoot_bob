package com.bob.springboot;

import com.bob.springboot.bean.Book;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void contextLoads() {
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "第一条信息");
		map.put("data", Arrays.asList("helloworld", 123, true));
		rabbitTemplate.convertAndSend("exchange.direct", "bob.news", new Book("西游记", "吴承恩"));
	}

	@Test
	public void test01() {
    Object o = rabbitTemplate.receiveAndConvert("bob.news");
    System.out.println(o.getClass());
    System.out.println(o);
  }

  @Test
  public void test02() {
	  rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("三国演义", "罗贯中"));
  }

  @Test
  public void create() {
//	  amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
//    amqpAdmin.declareQueue(new Queue("amqpAdmin.queue", true));
//    System.out.println("创建成功");
    amqpAdmin.declareBinding(new Binding("amqpAdmin.queue", DestinationType.QUEUE, "amqpAdmin.exchange", "amqp.haha", null));
	}


}

