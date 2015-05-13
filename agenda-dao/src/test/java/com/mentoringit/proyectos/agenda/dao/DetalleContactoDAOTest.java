package com.mentoringit.proyectos.agenda.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.mentoringit.proyectos.agenda.dao.impl.DetalleContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.interfaces.DetalleContactoDAO;
import com.mentoringit.proyectos.agenda.dto.DetalleContacto;
import com.mentoringit.proyectos.agenda.dto.MedioContacto;

public class DetalleContactoDAOTest {

	DetalleContactoDAO detalleContactoDAO = new DetalleContactoDAOImpl();
	List<DetalleContacto> detalles;
	final int contactoId = 1; 
	
	@Test
	public void insertarTest() {
		DetalleContacto detalleContacto = new DetalleContacto();
		detalleContacto.setContactoId(contactoId);
		detalleContacto.setMedioContactoId(MedioContacto.DIRECCION.getMedioContactoid());
		detalleContacto.setValor("Calle falsa 123");
		assertTrue(detalleContactoDAO.insertarDetalle(detalleContacto));
		
		detalleContacto = new DetalleContacto();
		detalleContacto.setContactoId(contactoId);
		detalleContacto.setMedioContactoId(MedioContacto.TELEFONO.getMedioContactoid());
		detalleContacto.setValor("55-01234-5678");
		assertTrue(detalleContactoDAO.insertarDetalle(detalleContacto));
		
		detalleContacto = new DetalleContacto();
		detalleContacto.setContactoId(contactoId);
		detalleContacto.setMedioContactoId(MedioContacto.MAIL.getMedioContactoid());
		detalleContacto.setValor("adrian.osorio@mentoringit.com.mx");
		assertTrue(detalleContactoDAO.insertarDetalle(detalleContacto));
		
	}
	
	@Test
	public void listarTest() {
		detalles = detalleContactoDAO.listarDetalles(contactoId);		
		assertFalse(detalles.isEmpty());
	}
	
	@Test
	public void obtenerTest() {
		detalles = detalleContactoDAO.listarDetalles(contactoId);
		for(DetalleContacto detalleTemp : detalles) {
			DetalleContacto detalle = detalleContactoDAO.obtenerDetalle(detalleTemp.getDetalleContactoid());
			assertNotNull(detalle);
			assertTrue(detalle instanceof DetalleContacto);
			assertTrue(detalle.getContactoId() == detalleTemp.getContactoId());
			System.out.println("DetalleContacto Temp: " + detalleTemp);
			System.out.println("DetalleContacto: " + detalle);
		}
	}
	
	@Test
	public void actuaizarTest() {
		detalles = detalleContactoDAO.listarDetalles(contactoId);
		for(DetalleContacto detalle : detalles) {
			detalle.setValor(detalle.getValor().toUpperCase());
			assertTrue(detalleContactoDAO.actualizarDetalle(detalle));
		}
		detalles = detalleContactoDAO.listarDetalles(contactoId);
		for(DetalleContacto detalle : detalles) {
			System.out.println(detalle);
		}
	}
	
	@Test
	public void eliminarTest() {
		detalles = detalleContactoDAO.listarDetalles(contactoId);
		for(DetalleContacto detalle : detalles) {
			assertTrue(detalleContactoDAO.eliminarDetalle(detalle.getDetalleContactoid()));
		}
		detalles = detalleContactoDAO.listarDetalles(contactoId);	
		assertTrue(detalles.isEmpty());
	}	

}
