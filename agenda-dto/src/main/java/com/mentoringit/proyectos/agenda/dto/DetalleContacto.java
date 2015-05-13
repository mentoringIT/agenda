package com.mentoringit.proyectos.agenda.dto;

public class DetalleContacto {

	private int detalleContactoId;
	
	private int medioContactoId;
	
	private String valor;
	
	private MedioContacto medioContacto;
	
	private int contactoId;
	
	public DetalleContacto(){

	} 
	
	public DetalleContacto(int detalleContactoid, int medioContactoId,
			String valor, int contactoId) {
		this.detalleContactoId = detalleContactoid;
		this.medioContactoId = medioContactoId;
		this.valor = valor;
		this.contactoId = contactoId;
		medioContacto = MedioContacto.valueOf(medioContactoId);
	}

	public int getDetalleContactoid() {
		return detalleContactoId;
	}

	public void setDetalleContactoid(int detalleContactoId) {
		this.detalleContactoId = detalleContactoId;
	}

	public int getMedioContactoId() {
		return medioContactoId;
	}

	public void setMedioContactoId(int medioContactoId) {
		this.medioContactoId = medioContactoId;
	}

	public String getValor() {
		return valor.replace("[AT]", "@");
	}

	public void setValor(String valor) {
		valor = valor.replace("@", "[AT]");
		this.valor = valor;
	}

	public MedioContacto getMedioContacto() {
		return medioContacto;
	}

	public void setMedioContacto(MedioContacto medioContacto) {
		this.medioContacto = medioContacto;
	}

	public int getContactoId() {
		return contactoId;
	}

	public void setContactoId(int contactoId) {
		this.contactoId = contactoId;
	}
	
}
