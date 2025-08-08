package com.timezero.seckill.controller;

import com.timezero.seckill.enums.ResponseStsEnum;
import com.timezero.seckill.exceptions.TZBaseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello()
    {
        return "hello world";
    }

    @PostMapping("/hello")
    public Integer helloError() {
        return 1/0;
    }

    @PutMapping("/hello")
    public String helloError2() {
        throw new TZBaseException(ResponseStsEnum.USER_NOT_LOGIN);
    }
}
