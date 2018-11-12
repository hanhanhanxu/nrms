package cn.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/UserController")
public class UserController {

    @RequestMapping("/loadMenu")
    public String loadMenu(){

        return "/WEB-INF/jsp/menu.jsp";//登录，进入菜单页面
    }

}
