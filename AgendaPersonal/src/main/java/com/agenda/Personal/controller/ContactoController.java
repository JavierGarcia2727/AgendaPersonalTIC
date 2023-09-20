package com.agenda.Personal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agenda.Personal.model.Contacto;
import com.agenda.Personal.repository.ContactoRepository;

@Controller
@RequestMapping("/contacto")
public class ContactoController {
	
	@Autowired
	private ContactoRepository contactoRepository;
	
	@GetMapping("/index")
	public String index(Model model) {
		
		System.out.println("################ 1 ###########################");
		List<Contacto> contactos =  contactoRepository.findAll();

		System.out.println("################ 2 ###########################");
		model.addAttribute("contactos", contactos);

		System.out.println("################ 3 ###########################");
		return "index";
	}
	
	
	
	

}
