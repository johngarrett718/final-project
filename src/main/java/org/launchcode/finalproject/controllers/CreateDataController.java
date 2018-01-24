package org.launchcode.finalproject.controllers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.launchcode.finalproject.models.ClientModel;
import org.launchcode.finalproject.models.SaleModel;
import org.launchcode.finalproject.models.SalesRepModel;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
        for (CSVRecord client : records) {

            ClientModel newClient = new ClientModel();
            newClient.setName(client.get("name"));
            newClient.setCity(client.get("city"));
            newClient.setState(client.get("state"));

            String phoneNumber = client.get("phone");
            phoneNumber = phoneNumber.substring(0, 3) + phoneNumber.substring(4, 7)
                    + phoneNumber.substring(8);
            newClient.setPhoneNumber(phoneNumber);
            newClient.setEmail(client.get("email"));

            clientDao.save(newClient);
        }
        in = new FileReader("Users/johng/Desktop/SalesRep.csv");
        records = CSVFormat.EXCEL.withHeader().parse(in);
        for (CSVRecord salesrep : records){
            SalesRepModel newSalesRep = new SalesRepModel();
            newSalesRep.setFirstName(salesrep.get("FirstName"));
            newSalesRep.setLastName(salesrep.get("LastName"));

            salesRepDao.save(newSalesRep);
        }
        SimpleDateFormat formatter=new SimpleDateFormat("MM-dd-yyyy");
        GregorianCalendar cal=new GregorianCalendar();
        int year=2013;
        int total=365;
        cal.set(Calendar.YEAR, year);
        if (cal.isLeapYear(year)) {
            total++;
        }

        for(int d=1; d<=total; d++) {
            cal.set(Calendar.DAY_OF_YEAR, d);
            Date date = cal.getTime();
        }

        return "redirect:";
    }
}

