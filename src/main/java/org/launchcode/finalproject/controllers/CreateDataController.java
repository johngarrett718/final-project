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
import java.util.*;


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
        for (CSVRecord salesrep : records) {
            SalesRepModel newSalesRep = new SalesRepModel();
            newSalesRep.setFirstName(salesrep.get("FirstName"));
            newSalesRep.setLastName(salesrep.get("LastName"));

            salesRepDao.save(newSalesRep);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        GregorianCalendar cal = new GregorianCalendar();
        int year = 2017;
        int total = 365;
        cal.set(Calendar.YEAR, year);
        if (cal.isLeapYear(year)) {
            total++;
        }

        for (int d = 1; d <= total; d++) {
            cal.set(Calendar.DAY_OF_YEAR, d);
            Date date = cal.getTime();
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            Random rand = new Random();
            ArrayList<Integer> packageSize = new ArrayList();
            packageSize.add(9);
            packageSize.add(12);
            packageSize.add(15);
            packageSize.add(24);
            packageSize.add(30);
            packageSize.add(36);
            packageSize.add(50);
            packageSize.add(48);

            //Loop over all customers

            for (ClientModel client : clientDao.findAll()) {
                //Inside loop: randomly decide if customer made a purchase
                int randomNum = rand.nextInt(10);
                //if they did, randomnly decide how many hours
                if (randomNum == 5) {
                    //number of hours to use
                    Integer hoursBought = packageSize.get(rand.nextInt(packageSize.size()));

                    //randomly select firstContact - salesrepDAO
                    Iterable <SalesRepModel> salesReps = salesRepDao.findAll();
                    ArrayList <SalesRepModel> repList = new ArrayList<SalesRepModel>();
                    for (SalesRepModel salesRep: salesReps) {
                        repList.add(salesRep);

                    }
                    Collections.shuffle(repList);
                    SalesRepModel firstContact = repList.get(0);


                    //randomly select closer
                    Collections.shuffle(repList);
                    SalesRepModel closer = repList.get(0);

                    //create sale using client, hours, first contact and closer
                    SaleModel newSale = new SaleModel();
                    newSale.setFirstContact(firstContact);
                    newSale.setCloser(closer);
                    newSale.setClient(client);
                    newSale.setHoursSold(hoursBought);
                    newSale.setMonth(month);
                    newSale.setDay(day);

                    saleDao.save(newSale);

                }


            }


        }

        return "redirect:";
    }
}

