package com.bob.springboot.service;

import com.bob.springboot.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @RabbitListener(queues = "bob.news")
  public void receive(Book book) {
    System.out.println("收到消息：" + book);
  }

  @RabbitListener(queues = "bob")
  public void receive02(Message message) {
    System.out.println(message.getBody());
    System.out.println(message.getMessageProperties());
  }

}
