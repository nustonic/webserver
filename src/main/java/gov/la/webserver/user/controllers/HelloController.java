package gov.la.webserver.user.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web-server")
public class HelloController {

    @GetMapping("/hellos")
    public  String hello(){
        return "<h1>hi hellom Spring Boot</h1>";

    }
    @GetMapping("/world")
    public  String world(){
        return "world";

    }

    @GetMapping("/hello")
    public  String helworld(){
        return " Hellow World";

    }
}
