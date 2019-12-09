package se.felix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Maincontroller {
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("helloworld", "boy if you don't get");
		return "index";
	}
}
