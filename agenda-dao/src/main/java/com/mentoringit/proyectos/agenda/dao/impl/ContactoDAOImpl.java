package com.mentoringit.proyectos.agenda.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mentoringit.proyectos.agenda.dao.connection.ConnectionFactory;
import com.mentoringit.proyectos.agenda.dao.interfaces.ContactoDAO;
import com.mentoringit.proyectos.agenda.dto.Contacto;

public class ContactoDAOImpl implements ContactoDAO {

	private Connection conexion;
	
	public boolean insertarContacto(Contacto contacto) {
		boolean result = false;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String insert = "INSERT INTO contacto "
				+ "(nombre, apellidos) "
				+ "values (?, ?)";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(insert);
			sentencia.setString(1, contacto.getNombre());
			sentencia.setString(2, contacto.getApellidos());
			
			if(!sentencia.execute()) {
				result = (sentencia.getUpdateCount() > 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.getInstancia().closeConnection();
		}
		return result;
	}

	public boolean actualizarContacto(Contacto contacto) {
		boolean result = false;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String update = "UPDATE contacto "
				+ "SET nombre = ?, "
				+ "apellidos = ? "
				+ "WHERE contacto_id = ?";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(update);			
			sentencia.setString(1, contacto.getNombre());
			sentencia.setString(2, contacto.getApellidos());
			sentencia.setInt(3, contacto.getContactoId());
			
			if(!sentencia.execute()) {
				result = (sentencia.getUpdateCount() > 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.getInstancia().closeConnection();
		}
		return result;
	}

	public boolean eliminarContacto(int contactoId) {
		boolean result = false;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String delete = "DELETE FROM contacto "
				+ "WHERE contacto_id = ?";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(delete);
			sentencia.setInt(1, contactoId);
			if(!sentencia.execute()) {
				result = (sentencia.getUpdateCount() > 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.getInstancia().closeConnection();
		}
		return result;
	}

	public Contacto obtenerContacto(int contactoId) {
		Contacto contacto = null;
		ResultSet resultSet;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String select = "SELECT * FROM contacto "				
				+ "WHERE contacto_id = ?";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(select);	
			sentencia.setInt(1, contactoId);
			resultSet = sentencia.executeQuery();			
			while(resultSet.next()){
				contacto = new Contacto();
				contacto.setContactoId(resultSet.getInt("contacto_id"));
				contacto.setNombre(resultSet.getString("nombre"));
				contacto.setApellidos(resultSet.getString("apellidos"));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			contacto = null;
		} finally {
			ConnectionFactory.getInstancia().closeConnection();
		}
		
		return contacto;
	}

	public List<Contacto> listarContactos() {
		List<Contacto> contactos = new ArrayList<Contacto>();
		ResultSet resultSet;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String select = "SELECT * FROM contacto";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(select);		
			resultSet = sentencia.executeQuery();
			while(resultSet.next()){
				Contacto contacto = new Contacto();
				contacto.setContactoId(resultSet.getInt("contacto_id"));
				contacto.setNombre(resultSet.getString("nombre"));
				contacto.setApellidos(resultSet.getString("apellidos"));
				contactos.add(contacto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.getInstancia().closeConnection();
		}
		return contactos;
	}

}
