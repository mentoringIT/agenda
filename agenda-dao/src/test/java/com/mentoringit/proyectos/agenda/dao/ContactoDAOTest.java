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
		assertTrue(contactoDAO.insertarContacto(contacto));
		
		contacto = new Contacto();
		contacto.setNombre("Jesus");
		contacto.setApellidos("Pulido");
		assertTrue(contactoDAO.insertarContacto(contacto));
		
	}
	
	@Test
	public void obtenerTest() {
		contactos = contactoDAO.listarContactos();	
		for(Contacto contactoTemp : contactos) {
			Contacto contacto = contactoDAO.obtenerContacto(contactoTemp.getContactoId());
			assertNotNull(contacto);
			assertTrue(contacto instanceof Contacto);
			assertTrue(contacto.getContactoId() == contactoTemp.getContactoId());
			System.out.println("Contacto Temp: " + contactoTemp);
			System.out.println("Contacto: " + contacto);
		}
	}
	
	@Test
	public void listarTest() {
		contactos = contactoDAO.listarContactos();		
		assertFalse(contactos.isEmpty());
	}
	
	@Test
	public void actuaizarTest() {
		contactos = contactoDAO.listarContactos();	
		for(Contacto contactoTemp : contactos) {
			Contacto contacto = contactoDAO.obtenerContacto(contactoTemp.getContactoId());
			contacto.setNombre(contacto.getNombre().toUpperCase());
			contacto.setApellidos(contacto.getApellidos().toLowerCase());
			assertTrue(contactoDAO.actualizarContacto(contacto));
		}
		contactos = contactoDAO.listarContactos();
		for(Contacto contactoTemp : contactos) {
			System.out.println(contactoTemp);
		}
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
