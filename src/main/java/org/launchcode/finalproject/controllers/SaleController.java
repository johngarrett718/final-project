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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.GregorianCalendar;

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
    @RequestMapping(value = "view", method = RequestMethod.POST)
    public String view(@RequestParam int month, @RequestParam int year, Model model){
        //Use SaleDao to find all sales
//        Iterable <SalesRepModel> salesReps = salesRepDao.findAll();
//        ArrayList <SalesRepModel> repList = new ArrayList<SalesRepModel>();
//        for (SalesRepModel salesRep: salesReps) {
//            repList.add(salesRep);
//
//        }
        Iterable <SaleModel> sales = saleDao.findAll();
        //Create a new empoty collection
        ArrayList<SaleModel> salesList = new ArrayList<SaleModel>();

        //Iterate over all sales
        for (SaleModel sale: sales){
            //Add to empty collection if they meet our criteria
            //TODO Need to add year to sale
            if (month==sale.getMonth()){
                salesList.add(sale);
        }
        }
        //Attach collection to the model
        model.addAttribute("salesList", salesList);
        return "sale/view";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    public String add(Model model){

        model.addAttribute("sale", new SaleModel());
        model.addAttribute("title", "Add a Sale");
        model.addAttribute("clients", clientDao.findAll());
        model.addAttribute("salesReps", salesRepDao.findAll());

        ArrayList months = new ArrayList();
        for(Integer i=1; i<13; i++){
            months.add(i);
        }
        model.addAttribute("months", months);

        ArrayList days = new ArrayList();
        for(Integer i=1; i<32; i++){
            days.add(i);
        }
        model.addAttribute("days", days);




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

        double hours = sale.getHoursSold();
        double currentHours = sale.getClient().getHours();
        sale.getClient().setHours(hours + currentHours);

        //TODO Update First Contact
        currentHours = sale.getFirstContact().getSalesRepHours();
        sale.getFirstContact().setSalesRepHours(.65 * hours + currentHours);

        //TODO Update Closer
        currentHours = sale.getCloser().getSalesRepHours();
        sale.getCloser().setSalesRepHours(.35 * hours + currentHours);

        //TODO Check for Valid day of Month

        saleDao.save(sale);
        return "redirect:";
    }
}
