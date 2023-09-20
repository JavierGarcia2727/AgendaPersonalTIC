package com.agenda.Personal.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agenda.Personal.model.Contacto;
import com.agenda.Personal.repository.ContactoRepository;

@Controller
@RequestMapping("/contacto")
public class ContactoController {
	
	@Autowired
	private ContactoRepository contactoRepository;
	
	@GetMapping("/index")
	public String index(Model model) {
		

		List<Contacto> contactos =  contactoRepository.findAll();


		model.addAttribute("contactos", contactos);

		System.out.println("################ 3 ###########################");
		return "index";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		System.out.println("################ Nuevo ###########################");
		model.addAttribute("contacto", new Contacto());
		
		return "nuevo";
		
	}
	
	
	@PostMapping("/nuevo")
	public String crear(Contacto contacto, RedirectAttributes ra) {
		
		System.out.println("################ crear #### "+contacto.getCorreo());

		System.out.println("################ crear #### "+contacto.getFechaNacimiento());
		contacto.setFechaRegistro(LocalDateTime.now());
		contactoRepository.save(contacto);
		
		ra.addFlashAttribute("msgExito", "El contacto se ha creado correctamente");
		
	return "redirect:/contacto/index";	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
