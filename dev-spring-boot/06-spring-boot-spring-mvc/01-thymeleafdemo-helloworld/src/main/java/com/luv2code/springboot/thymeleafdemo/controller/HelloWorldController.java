package com.luv2code.springboot.thymeleafdemo.controller;

//import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    @RequestMapping("/processForm")
    public String showData(){
        return "helloworld";
    }

    @PostMapping("processFormVersionThree")
                            //pomocu anotacije se cita podatak iz forme
    public String upperCase(@RequestParam("studentName") String name, Model model){

        name = name.toUpperCase();

        String res = "AYO! " + name;

        model.addAttribute("message", res);

        return "helloworld";
    }
}


//    @RequestMapping("processFormVersionTwo")
//    public String upperCase(HttpServletRequest request, Model model){
//        String name = request.getParameter("studentName");
//        name.toUpperCase();
//
//        String res = "HEY! " + name;
//
//        model.addAttribute("message", res);
//
//        return "helloworld";
//    }