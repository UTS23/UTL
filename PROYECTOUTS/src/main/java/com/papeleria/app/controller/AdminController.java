package com.papeleria.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.papeleria.app.entidades.Admin;
import com.papeleria.app.entidades.User;
import com.papeleria.app.exception.NotFoundException;
import com.papeleria.app.repositorio.AdminRepository;
import com.papeleria.app.repositorio.UserRepository;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "administrador")
public class AdminController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private AdminRepository adminRepository;
	
	@GetMapping("/dashboard")
	public String userListTemplate(Model model, HttpSession session) {
	    boolean isAdmin = (session.getAttribute("isAdmin") != null) && (boolean) session.getAttribute("isAdmin");

	    if (isAdmin) {
	        model.addAttribute("users", userRepository.findAll());
	        return "dashboard"; // Return the view for listing users
	    } else {
	        session.setAttribute("msg", "Lo siento no tienes permiso para ingresar a esa página");
	        return "redirect:/logueo";
	    }
     
     
    }
	@ModelAttribute("user")
	public User userModel() {
		return new User();
	}
	
	
	

	@GetMapping("/litusers")
	public String UserListTemplate(Model model, HttpSession session) {
		boolean isAdmin = (session.getAttribute("isAdmin") != null) && (boolean) session.getAttribute("isAdmin");

		if (isAdmin) {
			model.addAttribute("users", userRepository.findAll());
			return "administrador";
		} else {
			session.setAttribute("msg", "Lo siento no tienes permiso para ingresar a esa página");
			return "redirect:/logueo";
		}

	}
	
	@GetMapping("/new")
	public String userNewTemplate(Model model, HttpSession session) {

		// Verificar_si_es_administrador
		boolean isAdmin = (session.getAttribute("isAdmin") != null) && (boolean) session.getAttribute("isAdmin");

		if (isAdmin) {
			// Crear_nuevo_usuario
			model.addAttribute("user", new User());
			return "registro";
		} else {
			session.setAttribute("msg", "Lo siento no tienes permiso para ingresar a esa página");
			return "redirect:/logueo";
		}

	}
	
	 @PostMapping("/registro")
	    public String registrarUsuario(@ModelAttribute User user, Model model) {
	        // Aquí puedes realizar la lógica para registrar al usuario en tu sistema
	        // Por ejemplo, puedes guardar el usuario en una base de datos
	        
	        // Agrega un mensaje para mostrar en la página de inicio de sesión
	        model.addAttribute("registroMsg", "Usuario registrado exitosamente");
	        // Redirige al usuario a la página de inicio de sesión después del registro
	        return "redirect:/login";
	    }
	
	

	 @GetMapping("/editar/{id}")
	    public String Editar(@PathVariable("id") String id, Model model, HttpSession session) {
	        // Verificar si es administrador
	        boolean isAdmin = session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin");

	        if (isAdmin) {
	            model.addAttribute("user", userRepository.findById(id)
	                                                      .orElseThrow(() -> new NotFoundException("Usuario no encontrado")));
	            return "editar"; // Suponiendo que "crearUser" es la vista para editar usuarios
	        } else {
	            session.setAttribute("msg", "Lo siento, no tienes permiso para ingresar a esa página");
	            return "redirect:/logueo"; // Redirige al inicio de sesión si no es administrador
	        }
	    }
	

	@PostMapping("/save")
	public String userSaveProcces(@ModelAttribute("user") User user) {
		if (user.getId().isEmpty()) {
			user.setId(null);
		}

		userRepository.save(user);
		return "redirect:/administrador/";
	}

	 @GetMapping("/delete/{id}")
	    public String eliminarAdmin(@PathVariable("id") String id, HttpSession session) {
	        boolean isAdmin = (session.getAttribute("isAdmin") != null) && (boolean) session.getAttribute("isAdmin");

	        if (isAdmin) {
	            adminRepository.deleteById(id);
	            return "redirect:/administrador/administradores";
	        } else {
	            session.setAttribute("msg", "Lo siento, no tienes permiso para acceder a esta página");
	            return "redirect:/logueo";
	        }
	    }
	
	 
	 
	 @GetMapping("/ne")
	    public String showCreateAdminForm() {
	        return "create-admin-form"; // Nombre de la plantilla Thymeleaf para el formulario de creación
	    }

	    @PostMapping("/ne")
	    public RedirectView createAdmin(@ModelAttribute Admin admin) {
	        adminRepository.save(admin);
	        return new RedirectView("/administrador/administradores"); 
	    }
	    
	    
	    @GetMapping("/dos")
	    public String priu() {
	        return "Registro"; // Nombre de la plantilla Thymeleaf para el formulario de creación
	    }

	    @PostMapping("/dos")
	    public RedirectView  priu(@ModelAttribute Admin admin) {
	        adminRepository.save(admin);
	        return new RedirectView("/login"); 
	    }
	    
	    
	    

	 
	 
	 @GetMapping("/administradores")
	    public String listarAdministradores(Model model, HttpSession session) {
	        boolean isAdmin = (session.getAttribute("isAdmin") != null) && (boolean) session.getAttribute("isAdmin");

	        if (isAdmin) {
	            // Obtener la lista de administradores
	            Iterable<Admin> admins = adminRepository.findAll();
	            model.addAttribute("admins", admins);
	            return "listaAdmin"; // Nombre de la vista que muestra la lista de administradores
	        } else {
	            session.setAttribute("msg", "Lo siento, no tienes permiso para acceder a esta página");
	            return "redirect:/logueo";
	        }
	    }
}
	 
	 
	 
	 
	 
	 
	



	 