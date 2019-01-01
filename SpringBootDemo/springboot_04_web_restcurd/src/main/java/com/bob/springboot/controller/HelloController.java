package com.bob.springboot.controller;

import com.bob.springboot.exception.UserNotExistException;
import java.util.Arrays;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

  @ResponseBody
  @RequestMapping("/hello")
  public  String hello(@RequestParam("user") String user){
    if(user.equals("aaa")){
      throw new UserNotExistException();
    }
    return "Hello World";
  }

  @RequestMapping("/success")
  public String success(Map<String, Object> map) {
    map.put("hello", "<h1>您好</h1>");
    map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
    return "success";
  }

}
