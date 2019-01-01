package com.bob.springboot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

//  @Scheduled(cron = "0 * * * * MON-SAT")
  @Scheduled(cron = "0,1,2,3,4,5 * * * * MON-SAT")
  public void hello() {
    System.out.println("hello...");
  }

}
