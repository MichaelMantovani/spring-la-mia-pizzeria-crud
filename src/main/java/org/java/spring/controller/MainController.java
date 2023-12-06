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
	public String storePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {

		System.out.println("Pizza:\n" + pizza);

		if (bindingResult.hasErrors()) {

			System.out.println(bindingResult);
			model.addAttribute("pizza", pizza);
			return "pizzaForm";
		}

		try {
			pizzaService.save(pizza);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "redirect:/";
	}
	
	@GetMapping("pizza/edit/{id}")
	public String editPizza(@PathVariable int id, Model model) {
		
		model.addAttribute("pizza", pizzaService.findById(id));
		
		return "pizzaForm";
	}
	
	@PostMapping("pizza/edit/{id}")
	public String updatePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {

		System.out.println("Pizza:\n" + pizza);

		if (bindingResult.hasErrors()) {

			System.out.println(bindingResult);
			model.addAttribute("pizza", pizza);
			return "pizzaForm";
		}

		try {
			pizzaService.save(pizza);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "redirect:/";
	}
	
	@PostMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable int id) {
		Pizza pizza = pizzaService.findById(id);
		
		pizzaService.delete(pizza);
		
		return "redirect:/";
	}
	
	

}
