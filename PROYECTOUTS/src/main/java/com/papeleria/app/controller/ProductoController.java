package com.papeleria.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.papeleria.app.entidades.Producto;
import com.papeleria.app.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "productos";
    }

    @GetMapping("/otra")
    public String listar(Model model) {
    	  model.addAttribute("productos", productoService.obtenerTodosLosProductos());
    	  return "admidasb";
    }
    
    @GetMapping("/nuss")
    public String listar23(Model model) {
    	  model.addAttribute("productos", productoService.obtenerTodosLosProductos());
    	  return "noreg";
    }
    
    
    @GetMapping("/23")
    public String listar2(Model model) {
    	  model.addAttribute("productos", productoService.obtenerTodosLosProductos());
    	  return "dashboard";
    }
    
    
    
 
    


    @GetMapping("/otra-ruta")
    public String listarProductosOtraRuta(Model model) {
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "productopo";
    }




    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto_form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarProducto(@PathVariable("id") String id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "producto_form";
        } else {
            return "redirect:/productos/otra";
        }
    }

    @PostMapping("/guardar-producto")
    public String guardarProducto(@ModelAttribute("producto") Producto producto, Model model) {
        productoService.crearProducto(producto);
        model.addAttribute("mensaje", "¡Producto agregado correctamente!");
        return "redirect:/productos/nuevo";
    }
    
    @DeleteMapping("/delete/{id}")
    public String eliminarProducto(@PathVariable String id, Model model) {
        Producto productoEliminado = productoService.eliminarProducto(id);
        if (productoEliminado != null) {
            model.addAttribute("mensaje", "¡Producto eliminado correctamente!");
        } else {
            model.addAttribute("mensaje", "No se pudo eliminar el producto. Producto no encontrado.");
        }
        return "redirect:/productos";
    }

}
