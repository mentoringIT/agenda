package com.mentoringit.proyectos.agenda.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.mentoringit.proyectos.agenda.dao.impl.ContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.interfaces.ContactoDAO;
import com.mentoringit.proyectos.agenda.dto.Contacto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContactoForm extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private ContactoDAO contactoDAO;
	private int contactoId;

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
		setTitle("Contacto");
		setBounds(100, 100, 274, 133);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(66, 8, 182, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 43, 46, 14);
		contentPane.add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(66, 39, 182, 20);
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);
		
		JButton btnSiguiente = new JButton("Guardar");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Contacto contacto = new Contacto();
				contacto.setNombre(txtNombre.getText());
				contacto.setApellidos(txtApellidos.getText());
				
				if(contactoId > 0){
					contacto.setContactoId(contactoId);
					contactoDAO.actualizarContacto(contacto);
				} else {
					contactoDAO.insertarContacto(contacto);
				}
				
				Agenda.cargarContactos();
				dispose();
			}
		});
		btnSiguiente.setBounds(133, 68, 115, 23);
		contentPane.add(btnSiguiente);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(10, 68, 115, 23);
		contentPane.add(btnCancelar);
		
		contactoDAO = new ContactoDAOImpl();
	}
	
	public void setNombre(String nombre){
		txtNombre.setText(nombre);
	}
	
	public void setApellidos(String apellidos){
		txtApellidos.setText(apellidos);
	}
	
	public void setIdContacto(int contactoId){
		this.contactoId = contactoId;
	}
	
}
