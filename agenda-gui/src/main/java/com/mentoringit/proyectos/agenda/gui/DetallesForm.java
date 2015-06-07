package com.mentoringit.proyectos.agenda.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mentoringit.proyectos.agenda.dao.impl.DetalleContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.interfaces.DetalleContactoDAO;
import com.mentoringit.proyectos.agenda.dto.DetalleContacto;
import com.mentoringit.proyectos.agenda.dto.MedioContacto;

public class DetallesForm extends JInternalFrame {
	private int detalleContactoId;
	private int contactoId;
	private JTextField txtValor;
	private JComboBox cmbMedio;
	private DetalleContactoDAO detalleContactoDAO;
	
	public void setContactoId(int contactoId) {
		this.contactoId = contactoId;
	}
	
	public void setDetalleContactoId(int detalleId) {
		detalleContactoId = detalleId;
	}
	
	public void setValor(String valor) {
		txtValor.setText(valor);
	}
	
	public void setMedio(MedioContacto medio) {
		cmbMedio.setSelectedItem(medio);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetallesForm frame = new DetallesForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DetallesForm() {
		detalleContactoDAO = new DetalleContactoDAOImpl();
		
		setTitle("Detalle Contacto");
		setBounds(100, 100, 247, 124);
		getContentPane().setLayout(null);
		
		JLabel lblMedioDeContacto = new JLabel("Medio de Contacto");
		lblMedioDeContacto.setBounds(10, 11, 105, 14);
		getContentPane().add(lblMedioDeContacto);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(10, 36, 46, 14);
		getContentPane().add(lblValor);
		
		cmbMedio = new JComboBox(MedioContacto.values());
		cmbMedio.setBounds(114, 8, 111, 20);
		getContentPane().add(cmbMedio);
		
		txtValor = new JTextField();
		txtValor.setBounds(46, 33, 179, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(10, 61, 89, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetalleContacto detalle = new DetalleContacto(); 
				detalle.setContactoId(contactoId);
				detalle.setValor(txtValor.getText());
				
				MedioContacto medio = (MedioContacto) cmbMedio.getSelectedItem();
				detalle.setMedioContactoId(medio.getMedioContactoid());
				
				if(detalleContactoId > 0){
					detalle.setDetalleContactoid(detalleContactoId);
					detalleContactoDAO.actualizarDetalle(detalle);
				} else {
					detalleContactoDAO.insertarDetalle(detalle);					
				}
				
				Agenda.cargarDetallesContacto(contactoId);
				dispose();
			}
		});
		btnGuardar.setBounds(132, 64, 89, 23);
		getContentPane().add(btnGuardar);

	}
	
}
