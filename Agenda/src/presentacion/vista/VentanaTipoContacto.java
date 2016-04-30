package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import presentacion.controlador.Controlador;

public class VentanaTipoContacto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador;
	private JTextField txtnombre;
	private JButton btnGuardarTipo;
	private JButton btnEditar; 
	private JButton btnBorrar;
	private JTable tablaTipoContactos;
	private DefaultTableModel modelTipoContacto;
	private  String[] nombreColumnas = {"id", "Tipo Contacto"};
	private JTextField txtNombreEditar;
	private JLabel lblTipoContactoNuevo;
	private JLabel lblTipoContactoEditar;
	private JButton btnOk;
	private JButton btnCancel;

	
	public VentanaTipoContacto(Controlador controlador) {
		super();
		this.controlador = controlador;
		initialize();
		
	}
	
	private void initialize() {
		
		setTitle("Tipo Contacto");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 393, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 370, 369);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnEditar= new JButton("Editar");
		btnEditar.setBounds(217, 72, 112, 23);
		btnEditar.addActionListener(this.controlador);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(217, 149, 112, 23);
		btnBorrar.addActionListener(this.controlador);
		panel.add(btnBorrar);
		
		btnGuardarTipo = new JButton("Añadir");
		btnGuardarTipo.setBounds(217, 24, 112, 25);
		btnGuardarTipo.addActionListener(this.controlador);
		panel.add(btnGuardarTipo);
		
		lblTipoContactoNuevo = new JLabel("Tipo Contacto Nuevo");
		lblTipoContactoNuevo.setBounds(29, 12, 155, 15);
		panel.add(lblTipoContactoNuevo);
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		txtnombre.setBounds(29, 27, 155, 19);
		panel.add(txtnombre);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 152, 155, 191);
		panel.add(scrollPane);
		
		
		modelTipoContacto = new DefaultTableModel(null,nombreColumnas);
		tablaTipoContactos = new JTable(modelTipoContacto);
		scrollPane.setViewportView(tablaTipoContactos);
		
		lblTipoContactoEditar = new JLabel("Tipo Contacto Editar");
		lblTipoContactoEditar.setBounds(29, 59, 155, 15);
		panel.add(lblTipoContactoEditar);
		
		txtNombreEditar = new JTextField();
		txtNombreEditar.setColumns(10);
		txtNombreEditar.setBounds(29, 74, 155, 19);
		panel.add(txtNombreEditar);
		
		btnOk = new JButton("ok");
		btnOk.setBounds(29, 105, 51, 25);
		btnOk.addActionListener(this.controlador);
		panel.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(92, 105, 90, 25);
		btnCancel.addActionListener(this.controlador);
		panel.add(btnCancel);
	}
		
	
	
	public JTextField getTxtnombre() {
		return txtnombre;
	}

	public JButton getBtnGuardarTipo() {
		return btnGuardarTipo;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}


	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTablaTipoContactos() {
		return tablaTipoContactos;
	}

	public DefaultTableModel getModelTipoContacto() {
		return modelTipoContacto;
	}

	public JTextField getTxtNombreEditar() {
		return txtNombreEditar;
	}

	public JLabel getLblTipoContactoNuevo() {
		return lblTipoContactoNuevo;
	}

	public JLabel getLblTipoContactoEditar() {
		return lblTipoContactoEditar;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}
	
}
