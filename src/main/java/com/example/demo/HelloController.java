package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController()
public class HelloController {
	@Autowired
	private CustomerRepository repository;
	
	@RequestMapping("/hello")
	public ModelAndView hello(Model model) {
		ModelAndView helloView = new ModelAndView("hello");
		helloView.getModel().put("customers", repository.findAll());
		return helloView;
	}
	
	@RequestMapping(value="/remove/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteCustomer(@PathVariable("id") String id) {
		repository.deleteById(id);
		return "removed";
	}
	
	
	@RequestMapping("/getAllCustomers")
	public @ResponseBody List<Customer> customers() {
		return repository.findAll();
	}
	
	@RequestMapping("/getCustomer")
	public @ResponseBody Customer customer(@RequestParam(value="firstname") String firstname) {
		return repository.findByFirstname(firstname);
	}
	
	@RequestMapping("/getCustomers")
	public @ResponseBody List<Customer> customerL(@RequestParam(value="lastname") String lastname) {
		return repository.findByLastname(lastname);
	}
	
	@RequestMapping(value="/newCustomer", method=RequestMethod.POST)
	public RedirectView  newCustomer(@ModelAttribute(value="customer") Customer customer, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return null;
		}
		repository.save(customer);
		RedirectView helloView = new RedirectView("hello");
		helloView.addStaticAttribute("customers", repository.findAll());
		return helloView;
	}
}
