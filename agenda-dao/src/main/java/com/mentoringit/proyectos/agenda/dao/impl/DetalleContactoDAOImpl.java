package com.mentoringit.proyectos.agenda.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mentoringit.proyectos.agenda.dao.connection.ConnectionFactory;
import com.mentoringit.proyectos.agenda.dao.interfaces.DetalleContactoDAO;
import com.mentoringit.proyectos.agenda.dto.DetalleContacto;

public class DetalleContactoDAOImpl implements DetalleContactoDAO {

	private Connection conexion;

	public boolean insertarDetalle(DetalleContacto detalle) {
		boolean result = false;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String insert = "INSERT INTO detalle_contacto "
				+ "(medio_contacto_id, valor, contacto_id) "
				+ "values (?, ?, ?)";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(insert);
			sentencia.setInt(1, detalle.getMedioContactoId());
			sentencia.setString(2, detalle.getValor());
			sentencia.setInt(3, detalle.getContactoId());
			if (!sentencia.execute()) {
				result = (sentencia.getUpdateCount() > 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.getInstancia().closeConnection();
		}
		return result;
	}

	public boolean actualizarDetalle(DetalleContacto detalle) {
		boolean result = false;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String update = "UPDATE detalle_contacto "
				+ "SET medio_contacto_id = ?, " + "valor = ? "
				+ "WHERE detalle_contacto_id = ?";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(update);
			sentencia.setInt(1, detalle.getMedioContactoId());
			sentencia.setString(2, detalle.getValor());
			sentencia.setInt(3, detalle.getDetalleContactoid());

			if (!sentencia.execute()) {
				result = (sentencia.getUpdateCount() > 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.getInstancia().closeConnection();
		}
		return result;
	}

	public boolean eliminarDetalle(int detalleContactoId) {
		boolean result = false;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String delete = "DELETE FROM detalle_contacto "
				+ "WHERE detalle_contacto_id = ?";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(delete);
			sentencia.setInt(1, detalleContactoId);
			if (!sentencia.execute()) {
				result = (sentencia.getUpdateCount() > 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.getInstancia().closeConnection();
		}
		return result;
	}

	public DetalleContacto obtenerDetalle(int detalleContactoId) {
		DetalleContacto detalle = null;
		ResultSet resultSet;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String select = "SELECT * FROM detalle_contacto "
				+ "WHERE detalle_contacto_id = ?";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(select);
			sentencia.setInt(1, detalleContactoId);
			resultSet = sentencia.executeQuery();
			while (resultSet.next()) {
				detalle = new DetalleContacto(
						resultSet.getInt("detalle_contacto_id"),
						resultSet.getInt("medio_contacto_id"),
						resultSet.getString("valor"),
						resultSet.getInt("contacto_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			detalle = null;
		} finally {
			ConnectionFactory.getInstancia().closeConnection();
		}

		return detalle;
	}

	public List<DetalleContacto> listarDetalles(int contactoId) {
		List<DetalleContacto> detalles = new ArrayList<DetalleContacto>();
		ResultSet resultSet;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String select = "SELECT * FROM detalle_contacto "
				+ "WHERE contacto_id = ?";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(select);		
			sentencia.setInt(1, contactoId);
			resultSet = sentencia.executeQuery();
			while(resultSet.next()){
				DetalleContacto detalle =  new DetalleContacto(
						resultSet.getInt("detalle_contacto_id"),
						resultSet.getInt("medio_contacto_id"),
						resultSet.getString("valor"),
						resultSet.getInt("contacto_id"));
				detalles.add(detalle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.getInstancia().closeConnection();
		}
		return detalles;
	}

}
