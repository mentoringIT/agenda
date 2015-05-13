package com.mentoringit.proyectos.agenda.dao.interfaces;

import java.util.List;

import com.mentoringit.proyectos.agenda.dto.DetalleContacto;

public interface DetalleContactoDAO {

	boolean insertarDetalle(DetalleContacto detalle);
	
	boolean actualizarDetalle(DetalleContacto detalle);
	
	boolean eliminarDetalle(int detalleContactoId);
	
	DetalleContacto obtenerDetalle(int detalleContactoId);
	
	List<DetalleContacto> listarDetalles(int contactoId);
	
}
