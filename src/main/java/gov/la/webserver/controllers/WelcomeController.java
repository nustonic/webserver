package gov.la.webserver.controllers;
import  org.springframework.web.bind.annotation.GetMapping;
import  org.springframework.web.bind.annotation.RestController;
@RestController
public class WelcomeController {
    @GetMapping("/")
    public  String getMetHello(){
        return "Spring Boot";

    }

    @GetMapping("/world")
    public  String getMetWorld(){
        return "world";

        }

    @GetMapping("/hello")
    public  String getMethHW(){
        return " Hellow World";

            }
}