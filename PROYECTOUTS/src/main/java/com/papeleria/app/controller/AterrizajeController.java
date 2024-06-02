package com.papeleria.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.papeleria.app.entidades.Admin;
import com.papeleria.app.entidades.User;
import com.papeleria.app.repositorio.AdminRepository;
import com.papeleria.app.repositorio.UserRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class AterrizajeController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/login")
	public String loginAdm(Model model) {
		return "login";
	}
	@GetMapping("/terms")
	public String Terminos(Model model) {
		return "Terminos";
	}
	@GetMapping("/returns")
	public String Devoluciones(Model model) {
		return "Devoluciones";
	}
	@GetMapping("/privacy")
	public String Privacidad(Model model) {
		return "Privacidad";
	}
	@GetMapping("/contact")
	public String Contacto(Model model) {
		return "Contacto";
	}
	@GetMapping("/about")
	public String Nosotros(Model model) {
		return "Nosotros";
	}
	@GetMapping("/delivery")
	public String Domicilios(Model model) {
		return "Domicilios";
	}
	@GetMapping("/faq")
	public String Preguntas_Frecuentes(Model model) {
		return "Preguntas";
	}
	@GetMapping("/categories")
	public String categoriass(Model model) {
		return "categorias";
	}
	
	@GetMapping("/pass")
	public String re(Model model) {
		return "recuperacion";
	}
	
	@GetMapping("/registro")
	public String registro(Model model) {
		return "registro";
	}
	@GetMapping("/logout")
	public String cerrar(Model model) {
		return "logout";
	}
	@GetMapping("/ayuda")
	public String help(Model model) {
		return "ayuda";
	}
	
	
	@GetMapping("/configuracion")
	public String ck(Model model) {
		return "configuracion";
	}
	
	
	
	
	
	
	
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
	    Admin admin = adminRepository.findByEmail(email);
	    User user = userRepository.findByEmail(email);

	    if (admin != null && admin.getPassword().equals(password)) {
	        // Si es el administrador específico, redirigir al dashboard de administrador
	        if ("admin@gmail.com".equals(email)) {
	            session.setAttribute("isAdmin", true);
	            session.setAttribute("isUser", false);
	            return "redirect:/administrador/dashboard"; // Ruta del panel de administrador
	        } else {
	            // Si es un administrador, redirigir al panel de productos
	            session.setAttribute("isAdmin", true);
	            session.setAttribute("isUser", false);
	            return "redirect:/productos";
	        }
	    } else if (user != null && user.getPassword().equals(password)) {
	        // Si es un usuario normal, redirigir a la página de productos
	        session.setAttribute("isAdmin", false);
	        session.setAttribute("isUser", true);
	        return "redirect:/productos";
	    } else {
	        // Si las credenciales no coinciden, mostrar mensaje de error
	        session.setAttribute("msg", "Correo o contraseña incorrecta. Verifica por favor");
	        return "login"; // Página de inicio de sesión general
	    }
	}

	    
	
}
