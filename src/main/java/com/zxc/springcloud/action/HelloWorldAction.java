package com.zxc.springcloud.action;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldAction {

    @Value("${book.name}")
    private String bookName;

    @RequestMapping("/hello")
    public String hello() {
        return "hello " + this.bookName;
    }

}
