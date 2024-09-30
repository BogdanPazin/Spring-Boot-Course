package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}") //mora da bude isto kao naziv u application.properties, kako bi ubacilo odgovarajuce vrednosti u listu
    List<String> countries;

    @Value("${languages}")
    List<String> languages;

    @Value("${systems}")
    List<String> systems;

    @GetMapping("/showStudentForm")
    public String showForm(Model model){

        Student student = new Student();

        model.addAttribute("student", student);
        model.addAttribute("countries", countries);
        model.addAttribute("languages", languages);
        model.addAttribute("systems", systems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
                     //mora da bude isti naziv za anotaciju kao i iz forme i iz samog atributa
    public String processForm(@ModelAttribute("student") Student student){

        System.out.println("Student: " + student.getFirstName() + " " + student.getLastName() + ", country: " + student.getCountry() + ", favorite programming language is " + student.getLanguage());

        return "student-confirmation";
    }

}
