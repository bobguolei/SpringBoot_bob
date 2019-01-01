package com.bob.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

  @GetMapping("/abc")
  public String success(Model model) {
    System.out.println("abcc-----abcc");
    model.addAttribute("msg", "您好");
    return "success";
  }
}
