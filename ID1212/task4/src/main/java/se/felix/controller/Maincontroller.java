package se.felix.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.felix.controller.ConvertForm;

@Controller
public class Maincontroller {

	@GetMapping("/convert")
	public String emptyConvertSubmitted(Model model) {
		model.addAttribute("convertForm", new ConvertForm());
		return "index";
	}
	
	@PostMapping("/convert")
	public String convertSubmitted(@Valid ConvertForm convertForm, Model model) {
		convertForm.setResult();
		return "result";
	}
}
