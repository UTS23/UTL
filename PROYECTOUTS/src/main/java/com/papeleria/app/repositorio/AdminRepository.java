package com.papeleria.app.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.papeleria.app.entidades.Admin;
import java.util.List;

public interface AdminRepository extends MongoRepository<Admin, String> {
	
	public Admin findByEmail(String email);
	
	// Método personalizado para buscar administradores por su campo isAdmin
	List<Admin> findByIsAdmin(boolean isAdmin);
}
