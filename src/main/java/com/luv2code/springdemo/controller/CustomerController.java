package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    // need to inject the CustomerDAO into controller using dependency injection
    // by using autowired spring will scan for the components that implement CustomerService interface
    // and then create the instance and inject it here
    @Autowired
    private CustomerService customerService;

    // I only want to handle GET requests with this mapping
    @GetMapping("/list")
    public String listCustomers(Model theModel) {

        // get the customer from DAO
        List<Customer> customers = customerService.getCustomers();

        // add customer to our model, we set attribute name and the value for attribute
        theModel.addAttribute("customers", customers);

        // return the string that will be used for view
        return "list-customers";
    }
}
