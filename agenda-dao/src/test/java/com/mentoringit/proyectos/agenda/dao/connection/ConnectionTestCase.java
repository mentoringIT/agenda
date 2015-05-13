package com.mentoringit.proyectos.agenda.dao.connection;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.mentoringit.proyectos.agenda.dao.connection.ConnectionFactory;

public class ConnectionTestCase {

	@Test
	public void testConnection() {
		
		ConnectionFactory factory = ConnectionFactory.getInstancia();
		
		assertNotNull(factory);
		
		Connection conexion = factory.getConnection();
		
		assertNotNull(conexion);
	
	}

}