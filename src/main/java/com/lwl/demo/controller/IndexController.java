package com.lwl.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping("/lwl")
    public String  index(){
        return "lwl";
    }
    
    
    @RequestMapping("/wwj1")
    public ModelAndView  index1(){
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("xxx", "xxxx");
    	mv.setViewName("lwl");
        return mv;
    }
}
