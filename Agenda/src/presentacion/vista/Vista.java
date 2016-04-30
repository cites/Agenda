package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Vista
{
	private JFrame frmAgendaContactos;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private JMenuItem mntmLocalidad;
	private JMenuItem mntmTipoDeContacto;
	private DefaultTableModel modelPersonas;
	private  String[] nombreColumnas = {"Nombre y apellido", "Tel\u00E9fono", "Email", "Cumplea\u00F1os", "Direccion", "Altura", 
			"Piso", "Depto.", "Localidad", "Tipo Contacto","id"};

	public Vista() 
	{
		super();
		initialize();
	}


	private void initialize() 
	{
		frmAgendaContactos = new JFrame();
		frmAgendaContactos.setTitle("Agenda Contactos");
		frmAgendaContactos.setBounds(100, 100, 1002, 299);
		frmAgendaContactos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgendaContactos.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 24, 982, 238);
		frmAgendaContactos.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(0, 0, 979, 182);
		panel.add(spPersonas);
		
		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);
		this.ocultarColumna();
		
		
		spPersonas.setViewportView(tablaPersonas);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 194, 116, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(146, 194, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(247, 194, 89, 23);
		panel.add(btnBorrar);
		
		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(348, 194, 116, 23);
		panel.add(btnReporte);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(40, 0, 62, 20);
		frmAgendaContactos.getContentPane().add(menuBar);
		
		JMenu mnAadir = new JMenu("Menu");
		menuBar.add(mnAadir);
		
		mntmLocalidad = new JMenuItem("Localidad");
		mnAadir.add(mntmLocalidad);
		
		mntmTipoDeContacto = new JMenuItem("Tipo de Contacto");
		mnAadir.add(mntmTipoDeContacto);
	}
	
	public void ocultarColumna() {
		
		tablaPersonas.getColumnModel().getColumn(10).setMaxWidth(0);
        tablaPersonas.getColumnModel().getColumn(10).setMinWidth(0);
        tablaPersonas.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
        tablaPersonas.getTableHeader().getColumnModel().getColumn(10).setMinWidth(0);
		
	}
	
	public void show()
	{
		this.frmAgendaContactos.setVisible(true);
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}

	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	
	public JButton getBtnEditar()
	{
		
		return btnEditar;
	}

	public JButton getBtnReporte() 
	{
		return btnReporte;
	}
	
	public DefaultTableModel getModelPersonas() 
	{
		return modelPersonas;
	}
	
	public JTable getTablaPersonas()
	{
		return tablaPersonas;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}
	
	public JMenuItem getBtnLocalidad()
	{
		return mntmLocalidad;
	}
	
	public JMenuItem getBtnTipoContacto()
	{
		return mntmTipoDeContacto;
	}
}
