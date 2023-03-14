package cn.gd.cz.hong.demo.springboot.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class RouterController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/user/add")
    public String toAdd() {
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String toUpdate() {
        return "user/update";
    }

}
