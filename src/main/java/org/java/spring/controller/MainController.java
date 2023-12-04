package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class MainController {

	@Autowired
	PizzaService pizzaService;

	@GetMapping
	public String getPizzaIndex(Model model, @RequestParam(name = "searchValue", required = false) String searchValue) {

		List<Pizza> pizze = searchValue == null ? pizzaService.findAll() : pizzaService.findByName(searchValue);

		model.addAttribute("searchValue", searchValue);
		model.addAttribute("pizze", pizze);

		return "home";
	}

	@GetMapping("/pizza/{id}")
	public String getPizzaDetail(Model model, @PathVariable int id) {
		model.addAttribute("pizza", pizzaService.findById(id));
		return "pizzaDetail";
	}

	@GetMapping("/create")
	public String createPizza(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "pizzaForm";
	}

	@PostMapping("/create")
	public String storePizza(
			Model model,
			@Valid @ModelAttribute Pizza pizzaForm, 
			BindingResult bindingResult) {
		
		System.out.println("Pizza:\n" + pizzaForm);
		
		if (bindingResult.hasErrors()) {
			
			System.out.println(bindingResult);
			model.addAttribute("pizza", pizzaForm);
			return "pizzaForm";
		}
		
		try {
		pizzaService.save(pizzaForm);	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/";
	}

}
