package com.mentoringit.proyectos.agenda.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.mentoringit.proyectos.agenda.dao.impl.DetalleContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.interfaces.DetalleContactoDAO;
import com.mentoringit.proyectos.agenda.dto.DetalleContacto;
import com.mentoringit.proyectos.agenda.dto.MedioContacto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesForm extends JInternalFrame {
	private JTextField txtValor;
	private JComboBox cmbMedio;
	private DetalleContactoDAO detalleContactoDAO;
	private int contactoId;

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
		setBounds(100, 100, 280, 130);
		getContentPane().setLayout(null);
		
		JLabel lblMedioDeContacto = new JLabel("Medio de Contacto");
		lblMedioDeContacto.setBounds(10, 11, 100, 14);
		getContentPane().add(lblMedioDeContacto);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(10, 39, 46, 14);
		getContentPane().add(lblValor);
		
		cmbMedio = new JComboBox(MedioContacto.values());
		cmbMedio.setBounds(108, 8, 146, 20);
		getContentPane().add(cmbMedio);
		
		txtValor = new JTextField();
		txtValor.setBounds(46, 36, 208, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelar.setBounds(10, 64, 115, 23);
		getContentPane().add(cancelar);
		
		JButton guardarDetalle = new JButton("Guardar");
		guardarDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetalleContacto detalle = new DetalleContacto();
				detalle.setContactoId(contactoId);
				detalle.setValor(txtValor.getText());
				MedioContacto medio = (MedioContacto)cmbMedio.getSelectedItem();
				detalle.setMedioContactoId(medio.getMedioContactoid());
				detalleContactoDAO.insertarDetalle(detalle);
				Agenda.actualizaDetalles(contactoId);
				dispose();
			}
		});
		guardarDetalle.setBounds(139, 64, 115, 23);
		getContentPane().add(guardarDetalle);
	}
	
	public void setIdContacto(int contactoId){
		this.contactoId = contactoId;
	}
	
}
