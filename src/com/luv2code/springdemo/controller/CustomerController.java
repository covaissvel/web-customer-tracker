package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//need to inject the service
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		//get teh customers from service
		List<Customer> customers = customerService.getCustomers();
		
		//add customers to the model
		theModel.addAttribute("customers", customers);
		
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create new model attribute to bind the data
		Customer customer = new Customer();
		
		theModel.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		//save the customer using the service
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId")int theId, Model theModel) {
		
		//get the customer from service
		Customer customer = customerService.getCustomer(theId);
		
		//set the customer as model attribute to pre-populate the form
		theModel.addAttribute("customer", customer);
		
		//send over to our form
		return "customer-form";
	}
	
	@RequestMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId")int theId, Model theModel) {
		
		//delete the customer from service
		customerService.delete(theId);		
		
		//send over to our form
		return "redirect:/customer/list";
	}
	
}
