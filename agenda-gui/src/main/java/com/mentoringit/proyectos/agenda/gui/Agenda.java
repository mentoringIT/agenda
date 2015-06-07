package com.mentoringit.proyectos.agenda.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.mentoringit.proyectos.agenda.dao.impl.ContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.impl.DetalleContactoDAOImpl;
import com.mentoringit.proyectos.agenda.dao.interfaces.ContactoDAO;
import com.mentoringit.proyectos.agenda.dao.interfaces.DetalleContactoDAO;
import com.mentoringit.proyectos.agenda.dto.Contacto;
import com.mentoringit.proyectos.agenda.dto.DetalleContacto;

public class Agenda extends JFrame {

	private JPanel contentPane;
	private static JTable tableContactos;
	private static ContactoDAO contactoDAO;
	private static DetalleContactoDAO detalleContactoDAO;
	private static JTable tableDetalles;
	private JDesktopPane desktopPane;

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
		contactoDAO = new ContactoDAOImpl();
		detalleContactoDAO = new DetalleContactoDAOImpl();
		
		setResizable(false);
		setTitle("Agenda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(5, 5, 409, 278);
		contentPane.add(desktopPane);
		desktopPane.setLayout(new BoxLayout(desktopPane, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		desktopPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblContactos = new JLabel("CONTACTOS");
		lblContactos.setHorizontalAlignment(SwingConstants.CENTER);
		lblContactos.setBounds(0, 0, 409, 15);
		panel.add(lblContactos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 26, 409, 83);
		panel.add(scrollPane);
		
		tableContactos = new JTable();
		scrollPane.setViewportView(tableContactos);
		tableContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableContactos.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {					
					public void valueChanged(ListSelectionEvent e) {
						cargarDetalles();
					}
				}
			); 
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 175, 409, 72);
		panel.add(scrollPane_1);
		
		tableDetalles = new JTable();
		scrollPane_1.setViewportView(tableDetalles);
		
		JLabel lblDetalles = new JLabel("DETALLES");
		lblDetalles.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalles.setBounds(0, 150, 409, 14);
		panel.add(lblDetalles);
		
		JButton btnNuevoContacto = new JButton("Nuevo");
		btnNuevoContacto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ContactoForm form = new ContactoForm();
				desktopPane.add(form);
				form.setVisible(true);
				
			}
		});
		btnNuevoContacto.setBounds(123, 116, 89, 23);
		panel.add(btnNuevoContacto);
		
		JButton btnNuevoDetalle = new JButton("Nuevo Detalle");
		btnNuevoDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = tableContactos.getSelectedRow();
				int contactoId = -1;
				if(row != -1){
					Object datos = tableContactos.getModel().getValueAt(row, 0);
					contactoId = Integer.parseInt(datos.toString());
					if(contactoId > 0) {
						
						DetallesForm detallesForm = new DetallesForm();
						desktopPane.add(detallesForm);
						detallesForm.setVisible(true);
						detallesForm.setContactoId(contactoId);
						
					}
				}
				
			}
		});
		btnNuevoDetalle.setBounds(21, 255, 114, 23);
		panel.add(btnNuevoDetalle);
		
		JButton btnEliminar = new JButton("Eliminar Detalle");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = tableDetalles.getSelectedRow();
				int detalleId = -1;
				if(row != -1){
					Object datos = tableDetalles.getModel().getValueAt(row, 0);
					detalleId = Integer.parseInt(datos.toString());
					if(detalleId > 0) {
						detalleContactoDAO.eliminarDetalle(detalleId);
						cargarDetalles();
					}
				}
				
			}
		});
		btnEliminar.setBounds(271, 255, 138, 23);
		panel.add(btnEliminar);
		
		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = tableContactos.getSelectedRow();
				int contactoId = -1;
				if(row != -1){
					Object datos = tableContactos.getModel().getValueAt(row, 0);
					contactoId = Integer.parseInt(datos.toString());
					if(contactoId > 0) {
						contactoDAO.eliminarContacto(contactoId);
						cargarContactos();
					}
				}
				
			}
		});
		btnEliminar_1.setBounds(320, 116, 89, 23);
		panel.add(btnEliminar_1);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = tableContactos.getSelectedRow();
				int contactoId = -1;
				if(row != -1){
					Object datos = tableContactos.getModel().getValueAt(row, 0);
					contactoId = Integer.parseInt(datos.toString());
					if(contactoId > 0) {
						Contacto contacto = contactoDAO.obtenerContacto(contactoId);
						ContactoForm formContacto = new ContactoForm();
						formContacto.setIdContacto(contactoId);
						formContacto.setNombre(contacto.getNombre());
						formContacto.setApellidos(contacto.getApellidos());
						
						desktopPane.add(formContacto);
						formContacto.setVisible(true);
					}
				}
				
			}
		});
		btnEditar.setBounds(222, 116, 89, 23);
		panel.add(btnEditar);
		
		JButton btnEditarDetalle = new JButton("Editar Detalle");
		btnEditarDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = tableDetalles.getSelectedRow();
				int detalleId = -1;
				if(row != -1){
					Object datos = tableDetalles.getModel().getValueAt(row, 0);
					detalleId = Integer.parseInt(datos.toString());
					if(detalleId > 0) {
						DetalleContacto detalle = detalleContactoDAO.obtenerDetalle(detalleId);
						
						DetallesForm detallesForm = new DetallesForm();
						detallesForm.setContactoId(detalle.getContactoId());
						detallesForm.setDetalleContactoId(detalleId);
						detallesForm.setValor(detalle.getValor());
						detallesForm.setMedio(detalle.getMedioContacto());
						
						desktopPane.add(detallesForm);
						detallesForm.setVisible(true);
					}
				}
				
			}
		});
		btnEditarDetalle.setBounds(145, 255, 116, 23);
		panel.add(btnEditarDetalle);
		cargarContactos();
	}
	
	public static void cargarContactos() {
		List<Contacto> listaContactos = contactoDAO.listarContactos();
		DefaultTableModel modeloContactos = 
			new DefaultTableModel( new String[] {"Id", "Nombre", "Apellidos"}, 0 );
		for(Contacto contacto : listaContactos){
			String[] row = {
				String.valueOf(contacto.getContactoId()), contacto.getNombre(), contacto.getApellidos() 	
			};
			modeloContactos.addRow(row);
		}
		tableContactos.setModel(modeloContactos);
	}
	
	private void cargarDetalles(){
		int row = tableContactos.getSelectedRow();
		int contactoId = -1;
		if(row != -1){
			Object datos = tableContactos.getModel().getValueAt(row, 0);
			contactoId = Integer.parseInt(datos.toString());
			if(contactoId > 0) {
				cargarDetallesContacto(contactoId);
			}
		}
	}
	
	public static void cargarDetallesContacto(int contactoId){
		List<DetalleContacto> listaDetalles = detalleContactoDAO.listarDetalles(contactoId);
		DefaultTableModel modeloDetalles = 
			new DefaultTableModel( new String[] {"Id", "Medio Contacto", "Valor"}, 0 );
		for(DetalleContacto detalle : listaDetalles){
			String[] row = {
					String.valueOf(detalle.getDetalleContactoid()), 
					detalle.getMedioContacto().getClave(), 
					detalle.getValor()
			};
			modeloDetalles.addRow(row);
		}
		tableDetalles.setModel(modeloDetalles);
	}	
}
