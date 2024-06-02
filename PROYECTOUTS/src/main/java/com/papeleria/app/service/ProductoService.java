package com.papeleria.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.papeleria.app.entidades.Producto;
import com.papeleria.app.repositorio.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(String id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto eliminarProducto(String id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto productoEliminado = productoOptional.get();
            productoRepository.delete(productoEliminado);
            return productoEliminado;
        } else {
            return null; // Opcionalmente, puedes lanzar una excepción o manejarlo de otra manera según tu lógica de negocio.
        }
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Producto productoExistente) {
        return productoRepository.save(productoExistente);
    }
}


