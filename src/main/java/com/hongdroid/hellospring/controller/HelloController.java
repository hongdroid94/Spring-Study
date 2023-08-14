package com.hongdroid.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // hello spring
    }

    @GetMapping("hello-api")
    @ResponseBody // 이 어노테이션을 작성하기만해도 return이 모델클래스 형태로 해줄 경우 기본적으로 json 객체로 변환되어 결과값을 제공한다.
                  // 만약 없다면 viewResolver 시스템에 의해 html 뷰랑 연계를 시도하게 된다.
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // sample class 생성
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
