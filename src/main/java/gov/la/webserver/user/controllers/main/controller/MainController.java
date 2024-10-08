package gov.la.webserver.user.controllers.main.controller;

import gov.la.webserver.user.entity.LoginUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class MainController {
    @PostMapping("/main")
    public ModelAndView index(@AuthenticationPrincipal LoginUser loginUser, ModelAndView mv){
        mv.addObject("data",String.format("Welcome %s, S.H.I.E.L.D.",loginUser.getUser().getNickName()));
        mv.setViewName("index");
        return mv;
    }
    @GetMapping("/main/logout")
    public String logout(){
        return "logout";
    }
}
