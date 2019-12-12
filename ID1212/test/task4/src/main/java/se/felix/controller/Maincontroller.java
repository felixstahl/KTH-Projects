package se.felix.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import se.felix.controller.ConvertForm;

@Controller
public class Maincontroller {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/convert")
	public String emptyConvertSubmitted(Model model) {
		model.addAttribute("convertForm", new ConvertForm());
		return "index";
	}
	
	@Transactional
	@PostMapping("/convert")
	public String convertSubmitted(@Valid ConvertForm convertForm, BindingResult br) {
		if(br.hasErrors()) {
			convertForm = null;
			return "index";
		}
		String query = "SELECT rate from converter WHERE `from` = ? AND `to` = ?";
	    double rate = jdbcTemplate.queryForObject(query, Double.class, convertForm.getFromCurrency(), convertForm.getToCurrency());
	    convertForm.setResult(rate);
		
		return "result";
	}
}
