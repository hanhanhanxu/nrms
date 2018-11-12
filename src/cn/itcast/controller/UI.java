package cn.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/UI")
public class UI {

    @RequestMapping("/regist")
    public String regist(){
        return "/WEB-INF/jsp/regist.jsp";
    }

    @RequestMapping("/tomenu")
    public String tomenu(){
        //return "/ElefeeController/findAll.action";
        return "/ElefeeController/findElePage.action";
    }

}
