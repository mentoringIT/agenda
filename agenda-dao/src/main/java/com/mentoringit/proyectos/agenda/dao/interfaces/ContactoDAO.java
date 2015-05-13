package com.mentoringit.proyectos.agenda.dao.interfaces;

import java.util.List;

import com.mentoringit.proyectos.agenda.dto.Contacto;

public interface ContactoDAO {

	boolean insertarContacto(Contacto contacto);
	
	boolean actualizarContacto(Contacto contacto);
	
	boolean eliminarContacto(int contactoId);
	
	Contacto obtenerContacto(int contactoId);
	
	List<Contacto> listarContactos();
	
}
