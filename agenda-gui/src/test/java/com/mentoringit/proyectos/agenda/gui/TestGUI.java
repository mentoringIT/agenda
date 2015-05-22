package com.mentoringit.proyectos.agenda.gui;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mentoringit.proyectos.agenda.dao.impl.ContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.impl.DetalleContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.interfaces.ContactoDAO;
import com.mentoringit.proyectos.agenda.dao.interfaces.DetalleContactoDAO;
import com.mentoringit.proyectos.agenda.dto.Contacto;
import com.mentoringit.proyectos.agenda.dto.DetalleContacto;


public class TestGUI {

	Contacto contacto = new Contacto();
	DetalleContacto detalleContacto = new DetalleContacto();
	ContactoDAO contactoDAO = new ContactoDAOImpl();
	DetalleContactoDAO detalleDAO = new DetalleContactoDAOImpl();
	
	@Test
	public void test() {
		assertNotNull(contacto);
		assertNotNull(detalleContacto);
		assertNotNull(contactoDAO);
		assertNotNull(detalleDAO);
	}

}
