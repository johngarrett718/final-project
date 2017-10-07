package org.launchcode.finalproject.controllers;

import org.launchcode.finalproject.models.SalesRepModel;
import org.launchcode.finalproject.models.data.SalesRepDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("salesrep")
public class SalesRepController {
    @Autowired
    private SalesRepDao salesrepDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("salesrep", salesrepDao.findAll());
        model.addAttribute("title", "Sales Reps");

        return "salesrep/index";

    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute("salesrep", new SalesRepModel());
        model.addAttribute("title", "Add a Sales Rep");

        return "salesrep/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,
                      @ModelAttribute @Valid SalesRepModel salesrep, Errors error) {
        if (error.hasErrors()) {
            model.addAttribute("title", "Add a Sales Rep");
            return "salesrep/add";
        }
        salesrepDao.save(salesrep);
        return "redirect:";
    }
}




