package cn.gd.cz.hong.demo.springboot.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipsController {

    @RequestMapping("/noauth")
    public String noAuth() {
        return "未经授权不能访问此页面";
    }
}
