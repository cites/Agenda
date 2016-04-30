package presentacion.vista;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAddPersona;
	private Controlador controlador;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private JLabel lblDireccion;
	private JTextField txtCallenro;
	private JTextField txtPiso;
	private JTextField txtDpto;
	private JComboBox<String> cBoxLocalidad;
	private JComboBox<String> cBoxTipoContacto;
	private JComboBox<String> jcmbDias;
	private JComboBox<String> jcmbMeses;
	private JTextField txtAnio;

	public VentanaPersona(Controlador controlador) 
	{
		super();
		setTitle("Nuevo Contacto");
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 422, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 399, 300);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 133, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(10, 39, 113, 14);
		panel.add(lblTelfono);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(143, 9, 244, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(86, 37, 301, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		btnAddPersona = new JButton("Agregar");
		btnAddPersona.addActionListener(this.controlador);
		btnAddPersona.setBounds(269, 266, 118, 23);
		panel.add(btnAddPersona);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(52, 69, 335, 20);
		panel.add(txtEmail);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(52, 133, 335, 20);
		panel.add(txtDireccion);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 71, 113, 14);
		panel.add(lblEmail);
		
		JLabel lblCumpleanos = new JLabel("Fecha de Nacimiento");
		lblCumpleanos.setBounds(10, 103, 155, 14);
		panel.add(lblCumpleanos);
		
		lblDireccion = new JLabel("Calle");
		lblDireccion.setBounds(10, 135, 113, 14);
		panel.add(lblDireccion);
		
		JLabel lblNro = new JLabel("Nro");
		lblNro.setBounds(10, 167, 32, 15);
		panel.add(lblNro);
		
		txtCallenro = new JTextField();
		txtCallenro.setBounds(52, 165, 91, 20);
		panel.add(txtCallenro);
		txtCallenro.setColumns(10);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(158, 167, 38, 15);
		panel.add(lblPiso);
		
		txtPiso = new JTextField();
		txtPiso.setBounds(195, 165, 84, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);
		
		JLabel lblDpto = new JLabel("Dpto");
		lblDpto.setBounds(286, 169, 38, 15);
		panel.add(lblDpto);
		
		txtDpto = new JTextField();
		txtDpto.setBounds(332, 166, 55, 20);
		panel.add(txtDpto);
		txtDpto.setColumns(10);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 201, 70, 15);
		panel.add(lblLocalidad);
		
		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto");
		lblTipoDeContacto.setBounds(10, 235, 123, 15);
		panel.add(lblTipoDeContacto);
		
		cBoxLocalidad = new JComboBox<String>();
		cBoxLocalidad.setBounds(108, 196, 279, 20);
		panel.add(cBoxLocalidad);
		
		cBoxTipoContacto = new JComboBox<String>();
		cBoxTipoContacto.setBounds(143, 230, 242, 20);
		panel.add(cBoxTipoContacto);
		
		jcmbDias = new JComboBox<String>();
		jcmbDias.setBounds(167, 101, 55, 20);
		panel.add(jcmbDias);
		
		jcmbMeses = new JComboBox<String>();
		jcmbMeses.setBounds(234, 101, 62, 20);
		panel.add(jcmbMeses);
		
		txtAnio = new JTextField();
		txtAnio.setBounds(303, 101, 84, 20);
		panel.add(txtAnio);
		txtAnio.setColumns(10);

	}
	
	public JTextField getTxtAnio()
	{
		return txtAnio;
	}

	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAddPersona;
	}

	public JTextField getTxtEmail() {
		
		return txtEmail;
	}

	public JTextField getTxtDireccion() {
		
		return txtDireccion;
	}

	public JTextField getTxtCallenro() {
		
		return txtCallenro;
	}

	public JTextField getTxtPiso() {
		
		return txtPiso;
	}

	public JTextField getTxtDpto() {
		
		return txtDpto;
	}
	
	public JComboBox<String> getComboLocalidad() {
		
		return cBoxLocalidad;
	}
	
	public JComboBox<String> getComboTipoContacto() {
		
		 return cBoxTipoContacto;
	}

	public boolean validarDatosIngresados() {
		return txtNombre.getText().equals("") ||
				txtTelefono.getText().equals("")||
				txtAnio.getText().equals("");		
	}

	public JComboBox<String> getJcmbDias() {
		return jcmbDias;
	}

	public JComboBox<String> getJcmbMeses() {
		return jcmbMeses;
	}
}

