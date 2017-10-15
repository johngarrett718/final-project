package org.launchcode.finalproject.controllers;


import org.launchcode.finalproject.models.ClientModel;
import org.launchcode.finalproject.models.data.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("client")
public class ClientController {
    @Autowired
    private ClientDao clientDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("clients", clientDao.findAll());
        model.addAttribute("title", "Clients");

        return "client/index";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){

        model.addAttribute("client", new ClientModel());
        model.addAttribute("title", "Add a Client");

        return "client/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,
                      @ModelAttribute @Valid ClientModel client, Errors error) {
        if (error.hasErrors()) {
            model.addAttribute("client", new ClientModel());
            model.addAttribute("title", "Add a Client");
            return "client/add";
        }
        clientDao.save(client);
        return "redirect:";
    }
}

