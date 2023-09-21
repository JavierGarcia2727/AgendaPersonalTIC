package com.agenda.Personal.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

		List<Contacto> contactos = contactoRepository.findAll();

		model.addAttribute("contactos", contactos);

		return "index";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute("contacto", new Contacto());

		return "nuevo";

	}

	@PostMapping("/crear")
	public String crear(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes mensaje,
			Model model) {

		System.out.println("################ crear #### " + contacto.getCorreo());

		if (bindingResult.hasErrors()) {
			model.addAttribute("contacto", contacto);

			return "nuevo";

		}

		contacto.setFechaRegistro(LocalDateTime.now());
		contactoRepository.save(contacto);

		mensaje.addFlashAttribute("msgExito", "El contacto se ha creado correctamente");

		return "redirect:/contacto/index";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		System.out.println("################ editar su id es ####### "+id);

		Contacto contacto = contactoRepository.findById(id).orElse(null);
		System.out.println("xxxxxxxxxxxxxxx "+contacto.getId());
		model.addAttribute("contacto", contacto);

		return "Actualizar";

	}

	/*@PostMapping("/actualizar/{id}")
	public String actualizar(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes mensaje,
			Model model, @PathVariable Integer id) {
		System.out.println("################ actualizar ####### "+id);
		Contacto contactoFormDB = contactoRepository.findById(id).orElse(null);
		
		contactoFormDB.setNombre(contacto.getNombre());
		contactoFormDB.setCelular(contacto.getCelular());
		contactoFormDB.setCorreo(contacto.getCorreo());
		contactoFormDB.setFechaNacimiento(contacto.getFechaNacimiento());
		
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("contacto", contacto);

			return "editar";

		}
		contactoRepository.save(contactoFormDB);

		mensaje.addFlashAttribute("msgExito", "El contacto se ha actualizado correctamente");

		return "redirect:/contacto/index";
	}*/
	@PostMapping("/actualizar")
	public String actualizar(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes mensaje,
			Model model) {
		System.out.println("================== actualizar ####### "+contacto.getId());
		
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("contacto", contacto);

			return "Actualizar";

		}
		contactoRepository.save(contacto);

		mensaje.addFlashAttribute("msgExito", "El contacto se ha actualizado correctamente");

		return "redirect:/contacto/index";
	}
	
	@PostMapping("/eleminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes mensaje) {
	
				
		Contacto contacto = contactoRepository.findById(id).orElse(null);
		contactoRepository.delete(contacto);

		mensaje.addFlashAttribute("msgExito", "El contacto se ha elimino correctamente");

		return "redirect:/contacto/index";
	}
	

}
