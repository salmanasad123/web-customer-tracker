package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    // need to inject the DAO into controller using dependency injection
    // by using autowired spring will scan for the components that implement CustomerDAO interface
    @Autowired
    private CustomerDAO customerDAO;

    @RequestMapping("/list")
    public String listCustomers(Model theModel) {

        // get the customer from DAO
        List<Customer> customers = customerDAO.getCustomers();

        // add customer to our model, we set attribute name and the value for attribute
        theModel.addAttribute("customers", customers);

        // return the string that will be used for view
        return "list-customers";
    }
}
