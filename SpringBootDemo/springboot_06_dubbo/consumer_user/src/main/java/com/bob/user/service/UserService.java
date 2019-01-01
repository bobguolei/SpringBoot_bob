package com.bob.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bob.ticket.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Reference
  TicketService ticketService;

  public void hello() {
    String ticket = ticketService.getTicket();
    System.out.println("已买到票：" + ticket);
  }
}
