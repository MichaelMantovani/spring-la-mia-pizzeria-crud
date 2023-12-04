package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

}
