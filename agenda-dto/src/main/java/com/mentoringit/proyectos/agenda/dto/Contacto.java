package com.mentoringit.proyectos.agenda.dto;

import java.util.List;

public class Contacto {

	private int contactoId;
	
	private String nombre;
	
	private String apellidos;
	
	private List<DetalleContacto> detallesContacto;

	public int getContactoId() {
		return contactoId;
	}

	public void setContactoId(int contactoId) {
		this.contactoId = contactoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public List<DetalleContacto> getDetallesContacto() {
		return detallesContacto;
	}

	public void setDetallesContacto(List<DetalleContacto> detallesContacto) {
		this.detallesContacto = detallesContacto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contacto [contactoId=");
		builder.append(contactoId);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellidos=");
		builder.append(apellidos);
		builder.append(", detallesContacto=");
		builder.append(detallesContacto);
		builder.append("]");
		return builder.toString();
	}
	
}
