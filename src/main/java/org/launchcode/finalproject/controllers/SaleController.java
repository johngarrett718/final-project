package org.launchcode.finalproject.controllers;

import org.launchcode.finalproject.models.SaleModel;
import org.launchcode.finalproject.models.data.ClientDao;
import org.launchcode.finalproject.models.data.SaleDao;
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
@RequestMapping("sale")
public class SaleController {
    @Autowired
    private SaleDao saleDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private SalesRepDao salesRepDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("sales", saleDao.findAll());
        model.addAttribute("title", "Sales");

        return "sale/index";

    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    public String add(Model model){

        model.addAttribute("sale", new SaleModel());
        model.addAttribute("title", "Add a Sale");
        model.addAttribute("clients", clientDao.findAll());
        model.addAttribute("salesReps", salesRepDao.findAll());



        return "sale/add";

    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model,
                      @ModelAttribute @Valid SaleModel sale, Errors error){
        if (error.hasErrors()){
            model.addAttribute("sale", new SaleModel());
            model.addAttribute("title", "Add a Sale");
            model.addAttribute("clients", clientDao.findAll());
            model.addAttribute("salesReps", salesRepDao.findAll());
            return "sale/add";
        }
        saleDao.save(sale);
        return "redirect:";

    }
}
