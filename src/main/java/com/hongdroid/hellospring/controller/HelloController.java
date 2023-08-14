package com.hongdroid.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        // html 파일내에 attribute name과 매칭되는 ${attributeName} 방식으로 추적하여
        // thymeleaf 문법에 상응하여 attributeValue로 치환된다.
        model.addAttribute("data", "hello!!");
        return "hello"; // resources/templates 하위 폴더 내용에서 html 파일명을 자동으로 추적함
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
}
