package com.george.xinbaoli.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/errorPage")
public class ErrorPageController {

    @RequestMapping(value = "/403")
    public String http403() {
        return "/errorPage/403";
    }

    @RequestMapping(value = "/404")
    public String http404() {
        return "/errorPage/404";
    }
}
