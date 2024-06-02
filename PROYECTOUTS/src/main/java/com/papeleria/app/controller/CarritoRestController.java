package com.papeleria.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.papeleria.app.entidades.Producto;
import com.papeleria.app.service.CarritoService;

@RestController
@RequestMapping("/api/carrito")
public class CarritoRestController {

	@Autowired
    private CarritoService carritoService;

	@PostMapping("/agregar/{productoId}")
	public RedirectView agregarAlCarrito(@PathVariable String productoId) {
	    carritoService.agregarAlCarrito(productoId);
	    System.out.println("Producto agregado al carrito con ID: " + productoId);
	    return new RedirectView("/productos", true); // Redirige a la URL /productos
	}

    @GetMapping("/productos")
    public List<Producto> obtenerProductosEnCarrito() {
        return carritoService.obtenerProductosEnCarrito();
    }
    
}
