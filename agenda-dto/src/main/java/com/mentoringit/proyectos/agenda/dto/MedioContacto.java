package com.mentoringit.proyectos.agenda.dto;

public enum MedioContacto {
	
	TELEFONO(1, "T", "Numero de telefono del contacto"), 
	DIRECCION(2, "D", "Direccion del contacto"), 
	MAIL(3, "M", "Correo Electronico del contacto");
	
	private int medioContactoid;

	private String clave;
	
	private String descripcion;
	
	MedioContacto (int medioContactoId, String clave, String descripcion){	
		this.medioContactoid = medioContactoId;
		this.clave = clave;
		this.descripcion = descripcion;		
	}
	
	public static MedioContacto valueOf(int medioContactoId) {
		for(MedioContacto medio : MedioContacto.values()){
			if(medioContactoId == medio.getMedioContactoid()){
				return medio;
			}
		}
		return null;
	}
	
	public int getMedioContactoid() {
		return medioContactoid;
	}

	public void setMedioContactoid(int medioContactoid) {
		this.medioContactoid = medioContactoid;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
	
}