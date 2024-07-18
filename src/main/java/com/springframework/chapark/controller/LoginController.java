package com.springframework.chapark.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springframework.chapark.common.CommonMap;

@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login.do")
    public String loginPage(CommonMap commonMap) {
        return "login";
    }

    @RequestMapping(value = "/logout.do")
    public String logout(Model model, Map paramMap, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("memberInfo");
        return "logout";
    }
}
