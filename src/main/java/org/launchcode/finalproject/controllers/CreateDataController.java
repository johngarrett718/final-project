package org.launchcode.finalproject.controllers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.launchcode.finalproject.models.ClientModel;
import org.launchcode.finalproject.models.data.ClientDao;
import org.launchcode.finalproject.models.data.SaleDao;
import org.launchcode.finalproject.models.data.SalesRepDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


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

    @RequestMapping(method = RequestMethod.POST)
    public String postdata(Model model) throws IOException {
        Reader in = new FileReader("/Users/johng/Desktop/Client.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord client : records) {

            ClientModel newClient = new ClientModel();
            newClient.setName(client.get("name"));
            newClient.setCity(client.get("city"));
            newClient.setState(client.get("state"));
            newClient.setPhoneNumber(client.get("phone"));
            newClient.setEmail(client.get("email"));

            clientDao.save(newClient);
        }
        return "redirect:";
    }
}
