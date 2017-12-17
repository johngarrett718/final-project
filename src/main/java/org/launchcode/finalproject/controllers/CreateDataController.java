package org.launchcode.finalproject.controllers;

import org.launchcode.finalproject.models.data.ClientDao;
import org.launchcode.finalproject.models.data.SaleDao;
import org.launchcode.finalproject.models.data.SalesRepDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("createdata")
public class CreateDataController {
    @Autowired
    private ClientDao clientDao;

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private SalesRepDao salesRepDao;

    @RequestMapping(value = "")
    public String createdata(Model model) {
        model.addAttribute("title", "Create Data");

        return "createData";
    }

    @RequestMapping(method= RequestMethod.POST)
    public String postdata (Model model){
        return "redirect:";
    }

}
