package com.mentoringit.proyectos.agenda.gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.mentoringit.proyectos.agenda.dao.impl.ContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.impl.DetalleContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.interfaces.ContactoDAO;
import com.mentoringit.proyectos.agenda.dao.interfaces.DetalleContactoDAO;
import com.mentoringit.proyectos.agenda.dto.Contacto;
import com.mentoringit.proyectos.agenda.dto.DetalleContacto;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class Agenda extends JFrame {
	private static JTable table;
	private static DetalleContactoDAO detalleDAO;
	private static JTable detallesTable;
	private JDesktopPane desktopPane;
	private static ContactoDAO contactoDAO;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda frame = new Agenda();
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
	public Agenda() {
		setResizable(false);
		setTitle("Agenda");
		setBounds(100, 100, 295, 432);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 287, 404);
		getContentPane().add(desktopPane);
		desktopPane.setLayout(new BoxLayout(desktopPane, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		desktopPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 243, 287, 114);
		panel.add(scrollPane_1);
		
		detallesTable = new JTable();
		scrollPane_1.setViewportView(detallesTable);
		detallesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblDetalles = new JLabel("DETALLES");
		lblDetalles.setBounds(0, 218, 287, 14);
		panel.add(lblDetalles);
		lblDetalles.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				Contacto contacto;
				int row = table.getSelectedRow();
				int contactoId = -1;
				if (row != -1) {
					contactoId = Integer.parseInt(table.getModel().getValueAt(row, 0)
							.toString());
					if (contactoId > 0) {
						contacto = contactoDAO.obtenerContacto(contactoId);
						ContactoForm frmContactos = new ContactoForm();
						frmContactos.setIdContacto(contactoId);
						frmContactos.setNombre(contacto.getNombre());
						frmContactos.setApellidos(contacto.getApellidos());
						desktopPane.add(frmContactos);
						frmContactos.setVisible(true);
					}
				}		

			}
		});
		btnEditar.setBounds(99, 184, 89, 23);
		panel.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarContacto();
			}
		});
		btnEliminar.setBounds(198, 184, 89, 23);
		panel.add(btnEliminar);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(0, 184, 89, 23);
		panel.add(btnNuevo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 25, 287, 148);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblContactos = new JLabel("CONTACTOS");
		lblContactos.setBounds(0, 5, 287, 14);
		panel.add(lblContactos);
		lblContactos.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNuevoDetalle = new JButton("Nuevo");
		btnNuevoDetalle.setBounds(99, 370, 89, 23);
		panel.add(btnNuevoDetalle);
		
		JButton btnEliminarDetalle = new JButton("Eliminar");
		btnEliminarDetalle.setBounds(198, 370, 89, 23);
		panel.add(btnEliminarDetalle);
		btnNuevoDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int contactoId = -1;
				if (row != -1) {
					contactoId = Integer.parseInt(table.getModel().getValueAt(row, 0)
							.toString());
					if (contactoId > 0) {
						DetallesForm frmDetalles = new DetallesForm();
						frmDetalles.setIdContacto(contactoId);
						desktopPane.add(frmDetalles);
						frmDetalles.setVisible(true);
					}
				}
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {			
			public void valueChanged(ListSelectionEvent e) {
				cargarDetalles();
			}
		});
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ContactoForm frmContactos = new ContactoForm();
				desktopPane.add(frmContactos);
				frmContactos.setVisible(true);
			}
		});		
		contactoDAO = new ContactoDAOImpl();
		detalleDAO = new DetalleContactoDAOImpl();
		cargarContactos();
		
	}
	
	public static void cargarContactos(){
		List<Contacto> contactos;
		DefaultTableModel modeloContactos;
		contactos = contactoDAO.listarContactos();
		modeloContactos = new DefaultTableModel(
				new String[] {"Id", "Nombre", "Apellidos"}, 0);
		for(Contacto contacto : contactos){
			String[] row = {
				String.valueOf(contacto.getContactoId()), contacto.getNombre(), 
					contacto.getApellidos()};
			modeloContactos.addRow(row);
		}
		table.setModel(modeloContactos);
	}
	
	public static void actualizaDetalles(int contactoId){
		List<DetalleContacto> detalles;
		DefaultTableModel modeloDetalles = new DefaultTableModel(
				new String[] {"Id", "Medio Contacto", "Valor"}, 0);
		detalles = detalleDAO.listarDetalles(contactoId);
		for(DetalleContacto detalle : detalles){
			String[] modelRow = {
				String.valueOf(detalle.getDetalleContactoid()), 
					detalle.getMedioContacto().getClave(), detalle.getValor()};
			modeloDetalles.addRow(modelRow);
		}
		detallesTable.setModel(modeloDetalles);
	}
	
	private void cargarDetalles() {
		int row = table.getSelectedRow();
		int contactoId = -1;
		if (row != -1) {
			contactoId = Integer.parseInt(table.getModel().getValueAt(row, 0)
					.toString());
			
			if (contactoId > 0) {
				actualizaDetalles(contactoId);
			}			
			
		}		

	}
	
	private void eliminarContacto(){
		int row = table.getSelectedRow();
		int contactoId = -1;
		if (row != -1) {
			contactoId = Integer.parseInt(table.getModel().getValueAt(row, 0)
					.toString());
			if (contactoId > 0) {
				contactoDAO.eliminarContacto(contactoId);
			}
			cargarContactos();			
		}		

	}
}
