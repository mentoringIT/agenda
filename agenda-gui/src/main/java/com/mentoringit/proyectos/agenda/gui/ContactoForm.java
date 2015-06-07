package com.mentoringit.proyectos.agenda.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mentoringit.proyectos.agenda.dao.impl.ContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.interfaces.ContactoDAO;
import com.mentoringit.proyectos.agenda.dto.Contacto;

public class ContactoForm extends JInternalFrame {
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private ContactoDAO contactoDAO;
	private int idContacto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactoForm frame = new ContactoForm();
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
	public ContactoForm() {
		contactoDAO = new ContactoDAOImpl();
		
		setTitle("Contacto");
		setBounds(100, 100, 221, 136);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 36, 46, 14);
		getContentPane().add(lblApellidos);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(66, 8, 132, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(66, 33, 132, 20);
		getContentPane().add(txtApellidos);
		txtApellidos.setColumns(10);
		
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
				Contacto contacto = new Contacto();
				contacto.setNombre(txtNombre.getText());
				contacto.setApellidos(txtApellidos.getText());
				
				if(idContacto > 0) {
					contacto.setContactoId(idContacto);
					contactoDAO.actualizarContacto(contacto);
				} else {
					contactoDAO.insertarContacto(contacto);					
				}
				
				Agenda.cargarContactos();
				dispose();
			}
		});
		btnGuardar.setBounds(109, 61, 89, 23);
		getContentPane().add(btnGuardar);

	}
	
	public void setNombre(String nombre){
		txtNombre.setText(nombre);
	}
	
	public void setApellidos(String apellidos){
		txtApellidos.setText(apellidos);
	}
	
	public void setIdContacto(int idContacto){
		this.idContacto = idContacto;
	}
	
}
