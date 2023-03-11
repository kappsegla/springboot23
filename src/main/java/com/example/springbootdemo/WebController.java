package com.example.springbootdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

        @RequestMapping(value = "/showPersons", method = RequestMethod.GET)
        public String showPersons(Model model) {
            model.addAttribute("message","Hola Mundo");
            return "persons";
        }
}
