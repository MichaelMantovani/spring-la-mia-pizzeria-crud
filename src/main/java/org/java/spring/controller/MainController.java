package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
	
	@Autowired PizzaService pizzaService;
	
	
	@GetMapping
	public String getPizza(Model model) {
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		
		return "home";
	}
	
	@GetMapping("/pizza/{id}")
	public String getPizzaDetail(Model model, @PathVariable int id) {
		model.getAttribute("id");
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		return "pizzaDetail";
	}
	
	
}
