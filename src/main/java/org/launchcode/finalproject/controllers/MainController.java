package org.launchcode.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(value = "")
    public String main (Model model){
        model.addAttribute("title", "Monthly Sales Report");

        return "main";
    }
}
