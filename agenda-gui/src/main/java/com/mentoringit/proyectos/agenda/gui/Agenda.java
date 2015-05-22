package com.mentoringit.proyectos.agenda.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JButton;

import com.mentoringit.proyectos.agenda.dao.impl.ContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.impl.DetalleContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.interfaces.ContactoDAO;
import com.mentoringit.proyectos.agenda.dao.interfaces.DetalleContactoDAO;
import com.mentoringit.proyectos.agenda.dto.Contacto;
import com.mentoringit.proyectos.agenda.dto.DetalleContacto;

public class Agenda {

	private JFrame frmAgenda;
	private final JDesktopPane desktopPane = new JDesktopPane();
	private JTable table;
	private ContactoDAO contactoDAO;
	private DetalleContactoDAO detalleDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda window = new Agenda();
					window.frmAgenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Agenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAgenda = new JFrame();
		frmAgenda.setTitle("Agenda");
		frmAgenda.setResizable(false);
		frmAgenda.setBounds(100, 100, 326, 476);
		frmAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgenda.getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		contactoDAO = new ContactoDAOImpl();
		detalleDAO = new DetalleContactoDAOImpl();
		
		List<Contacto> contactos = contactoDAO.listarContactos();
		DefaultListModel listModel = new DefaultListModel();
		for(Contacto contacto : contactos){
			listModel.addElement(contacto.getApellidos() + " " + contacto.getNombre());
		}
		
		JList list = new JList(listModel);
		list.setBounds(10, 169, 287, -132);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		desktopPane.add(list);
		
		table = new JTable();
		table.setBounds(10, 266, 287, 161);
		desktopPane.add(table);
		
		JLabel lblContactos = new JLabel("CONTACTOS");
		lblContactos.setHorizontalAlignment(SwingConstants.CENTER);
		lblContactos.setBounds(10, 11, 287, 14);
		desktopPane.add(lblContactos);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(10, 232, 89, 23);
		desktopPane.add(btnNuevo);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(208, 232, 89, 23);
		desktopPane.add(btnEliminar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 232, 89, 23);
		desktopPane.add(btnEditar);
	}

}
