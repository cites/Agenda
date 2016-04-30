package presentacion.vista;


import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;

public class VentanaPersonaModificar extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnModificar;
	private Controlador controlador;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private JLabel lblDireccion;
	private JTextField txtCallenro;
	private JTextField txtPiso;
	private JTextField txtDpto;
	private JComboBox<String> cBoxLocalidad; 
	private JComboBox<String> cBoxTipoContacto;
	private JTextField txtId;
	private JComboBox<String> jcmbDias;
	private JComboBox<String> jcmbMeses;
	private JTextField txtAnio;

	public VentanaPersonaModificar(Controlador controlador) 
	{
		super();
		setTitle("Modoficar Contacto");
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
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this.controlador);
		btnModificar.setBounds(269, 266, 118, 23);
		panel.add(btnModificar);
		
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
		
		JLabel lblCumpleanos = new JLabel("Fecha Nacimiento");
		lblCumpleanos.setBounds(10, 103, 133, 14);
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
		
		txtId = new JTextField();
		txtId.setForeground(Color.WHITE);
		txtId.setEditable(false);
		txtId.setEnabled(false);
		txtId.setBounds(0, 0, 0, 0);
		panel.add(txtId);
		txtId.setColumns(10);
		
		jcmbDias = new JComboBox<String>();
		jcmbDias.setBounds(141, 101, 55, 20);
		panel.add(jcmbDias);
		
		jcmbMeses = new JComboBox<String>();
		jcmbMeses.setBounds(200, 101, 98, 20);
		panel.add(jcmbMeses);
		
		txtAnio = new JTextField();
		txtAnio.setBounds(310, 101, 77, 20);
		panel.add(txtAnio);
		txtAnio.setColumns(10);
		
	}
	
	public JTextField getTxtAnio() {
		return txtAnio;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}

	public JButton getBtnModificar() {
		
		return btnModificar;
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

	public JComboBox<String> getcBoxLocalidad() {
		
		return cBoxLocalidad;
	}
	
	public JComboBox<String> getcBoxTipoContacto() {
		
		return cBoxTipoContacto;
	}

	public boolean validarDatosIngresados() {
		return txtNombre.getText().equals("") ||
				txtTelefono.getText().equals("");
	}
	
	public JComboBox<String> getJcmbDias() {
		return jcmbDias;
	}

	public JComboBox<String> getJcmbMeses() {
		return jcmbMeses;
	}
}

