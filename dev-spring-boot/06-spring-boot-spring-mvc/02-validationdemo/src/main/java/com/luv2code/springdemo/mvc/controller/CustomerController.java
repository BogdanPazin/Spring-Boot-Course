package com.luv2code.springdemo.mvc.controller;

import com.luv2code.springdemo.mvc.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @GetMapping("/")
    public String showForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @PostMapping("/processForm")
    //VALID anotacija ce da kaze spring mvc-u da izvrsi validaciju
    public String processForm(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult){

        System.out.println("Last name: |" + customer.getLastName() + "|");

        System.out.println("The binding results: " + bindingResult.toString());
        System.out.println();

        if(bindingResult.hasErrors()){
            return "customer-form";
        }
        else{
            return "customer-confirmation";
        }
    }

    @InitBinder // OVA ANOTACIJA CE DA IZVRSI SVE PROCESE PRE NEGO STO DODJE DO SAMOG CONTROLLER-A
    //ODNOSNO OVO CE SE POZVATI ZA SVAKI WEB REQUEST KOJI DOLAZI U CONTROLLER
    //I POMOCU NJE CU DA ONEMOGUCIM DA SE ZA lastName POSALJU SAMO SPACE-OVI
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        //u konstruktoru je poslat true, s tim ce onda ako su sve space-ovi onda ce da prebaci u NULL

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
        //ovaj string trimmer ce da se koristi za string klasu
    }
}
