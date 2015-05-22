package com.mentoringit.proyectos.agenda.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.mentoringit.proyectos.agenda.dao.impl.ContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.interfaces.ContactoDAO;
import com.mentoringit.proyectos.agenda.dto.Contacto;

public class ContactoDAOTest {

	ContactoDAO contactoDAO = new ContactoDAOImpl();
	List<Contacto> contactos; 
	
	@Test
	public void insertarTest() {
		Contacto contacto = new Contacto();
		contacto.setNombre("Adrian");
		contacto.setApellidos("Osorio");
		
		assertNotNull(contacto);
		assertNotNull(contactoDAO);
		
		assertTrue(contactoDAO.insertarContacto(contacto));
		
		contacto = new Contacto();
		contacto.setNombre("Jesus");
		contacto.setApellidos("Pulido");
		assertTrue(contactoDAO.insertarContacto(contacto));
		
	}
	
	@Test
	public void listarTest() {
		contactos = contactoDAO.listarContactos();		
		assertFalse(contactos.isEmpty());
	}
	
	@Test
	public void obtenerTest() {
		
		Contacto contacto = contactoDAO.obtenerContacto(1);
		assertNotNull(contacto);
		assertTrue(contacto instanceof Contacto);
		System.out.println("Contacto: " + contacto);
		
	}
	
	
	@Test
	public void actuaizarTest() {
		
		Contacto contacto = contactoDAO.obtenerContacto(1);
		assertNotNull(contacto);
		assertTrue(contacto instanceof Contacto);
		System.out.println("Contacto: " + contacto);
		
		contacto.setNombre(contacto.getNombre().toUpperCase());
		contacto.setApellidos(contacto.getApellidos().toLowerCase());
		assertTrue(contactoDAO.actualizarContacto(contacto));
		System.out.println("Contacto: " + contacto);
	}
	
	@Test
	public void eliminarTest() {
		contactos = contactoDAO.listarContactos();	
		for(Contacto contactoTemp : contactos) {
			if(contactoTemp.getContactoId() > 1) {
				assertTrue(contactoDAO.eliminarContacto(contactoTemp.getContactoId()));
			}
		}
		contactos = contactoDAO.listarContactos();		
		assertTrue(contactos.size() == 1);
	}	

}
