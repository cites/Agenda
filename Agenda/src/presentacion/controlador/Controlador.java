package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;
import presentacion.reportes.ReporteNacPorMes;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaPersonaModificar;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;

	public class Controlador implements ActionListener
	{
		private Vista vista;
		private List<PersonaDTO> personas_en_tabla;
		private List<LocalidadDTO> localidades_en_tabla;
		private List<TipoContactoDTO> tipos_contactos_en_tabla; 
		private List<TipoContactoDTO> tipos_contactos_en_combo_agregar;
		private List<TipoContactoDTO> tipos_contactos_en_combo_modificar;
		private List<LocalidadDTO> localidades_en_combo_agregar;
		private List<LocalidadDTO> localidades_en_combo_modificar;
		private VentanaPersona ventanaPersona; 
		private VentanaPersonaModificar ventanaPersModificar;
		private Agenda agenda;
		private VentanaLocalidad ventanaLocalidad;
		private VentanaTipoContacto ventanaTipoContacto;
		private String [] meses;
		private String [] dias;
		private String dia;
		private String mes;
		private String anio;
		private int contadorClickBtnAgregarPersona;
		private int contadorClickBtnModificarPersona;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);
			this.vista.getBtnEditar().addActionListener(this);
			this.vista.getBtnReporte().addActionListener(this);
			this.vista.getBtnTipoContacto().addActionListener(this);
			this.vista.getBtnLocalidad().addActionListener(this);
			this.agenda = agenda;
			this.personas_en_tabla = null;
			this.localidades_en_tabla = null;
			this.tipos_contactos_en_tabla = null;
			this.tipos_contactos_en_combo_agregar = null;
			this.localidades_en_combo_agregar = null;
			this.tipos_contactos_en_combo_modificar = null;
			this.localidades_en_combo_modificar = null;
			
			this.ventanaPersona = new VentanaPersona(this);
			this.ventanaLocalidad = new VentanaLocalidad(this);
			this.ventanaTipoContacto = new VentanaTipoContacto(this);
			this.ventanaPersModificar = new VentanaPersonaModificar(this);
		}
		
		public void inicializar()
		{
			this.llenarTabla();
			this.contadorClickBtnAgregarPersona = 0;
			this.contadorClickBtnModificarPersona = 0;
			
			this.dias = new String [32];
			
			for (Integer i= 0; i < 32; i++){
				 this.dias[i] = i.toString();
			}
			
			this.meses =new String [] {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio"
					,"Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		}
		
		
		public void actionPerformed(ActionEvent e) 
	//------------------------------------------------vista------------------------------------------------------
		{
			if(e.getSource() == this.vista.getBtnAgregar())//Accion de boton para Agregar nuevo contacto
			{
				this.ventanaPersona.setVisible(true);
				
				contadorClickBtnAgregarPersona += 1 ;
				
				if(contadorClickBtnAgregarPersona == 1){

					this.llenarComboDiasPersona();
					this.llenarComboMesesPersona();
					this.llenarComboLocalidad();
					this.llenarComboTipocontacto();
				}else{	
					this.limpiarDatosVentanaPersona();
				}

			}
			else if(e.getSource() == this.vista.getBtnBorrar())//Borrar contacto seleccionado
			{
				if(this.vista.getTablaPersonas().getSelectedRow()==-1)
				{
					JOptionPane.showMessageDialog(ventanaPersona, "Debe seleccionar al menos una fila para continuar", "Atencion!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
					
					for (int fila:filas_seleccionadas)
					{
						this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
					}
					
					this.llenarTabla();
				}
			}
			else if(e.getSource() == this.vista.getBtnReporte())//Abrir ventana de reporte
			{				
				ReporteNacPorMes reporte = new ReporteNacPorMes(Agenda.todosMes());
				reporte.mostrar();				
			}
			
			else if (e.getSource() == this.vista.getBtnEditar())//Abrir ventana editar seleccionada
			{
				int fila = this.vista.getTablaPersonas().getSelectedRow();
				
				if (fila != -1)
				{ 
					this.contadorClickBtnModificarPersona += 1;
					
					if(this.contadorClickBtnModificarPersona == 1){
						
						this.llenarComboDiasPersonaModificar();
			            this.llenarComboMesesPersonaModificar();
			            this.editarCargarDatos(fila);
			            
			            this.llenarCombosLocalidadesTipoContactoPersonaModificar(fila);
			            
			            this.editarCargarDatos(fila);
					}else{
						this.limpiarDatosVentanaEditarPersona();
						this.editarCargarDatos(fila);
						this.ventanaPersModificar.setVisible(true);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(ventanaPersona, "Debe seleccionar una fila para continuar", "Atencion!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(e.getSource() == this.vista.getBtnTipoContacto())//Abrir ventana para agregar tipo de contacto
			{
				this.ventanaTipoContacto.setVisible(true);
				
				this.llenarTablaTipoContacto();
				this.ocultarColumnaTipoContacto();
				this.ventanaTipoContacto.getBtnOk().setEnabled(false);
				this.ventanaTipoContacto.getBtnCancel().setEnabled(false);
				
			}
			
			else if(e.getSource() == this.vista.getBtnLocalidad())//Abrir Ventana para agregar localidad
			{
				this.ventanaLocalidad.setVisible(true);
				
				this.llenarTablaLocalidades();
				this.ocultarColumnaLocalidades();
				
			}
  //------------------------------------------------VentanaPersonaModificar----------------------------------------------------
			
			else if(e.getSource() == this.ventanaPersModificar.getBtnModificar())//Accion para modificar la persona
			{

				if(!this.ventanaPersModificar.validarDatosIngresados()){

					if (this.validarEmail(this.ventanaPersModificar.getTxtEmail().getText())){

						if (this.validarFecha((String)this.ventanaPersModificar.getJcmbDias().getSelectedItem()
								, (String)this.ventanaPersModificar.getJcmbMeses().getSelectedItem()
								, this.ventanaPersModificar.getTxtAnio().getText())){
							
							int year = Integer.parseInt(this.ventanaPersModificar.getTxtAnio().getText());
							int month = this.ventanaPersModificar.getJcmbMeses().getSelectedIndex()+1;
							int day = this.ventanaPersModificar.getJcmbDias().getSelectedIndex()+1;

							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

							String date = year + "-" + month + "-" + day;
							java.util.Date cumpleanos = null;
							try {
								cumpleanos = formatter.parse(date);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}

							PersonaDTO persona_a_editar = new PersonaDTO(
									Integer.parseInt(this.ventanaPersModificar.getTxtId().getText()),
									this.ventanaPersModificar.getTxtNombre().getText(), 
									this.ventanaPersModificar.getTxtTelefono().getText(),
									this.ventanaPersModificar.getTxtEmail().getText(),
									cumpleanos,
									this.ventanaPersModificar.getTxtDireccion().getText(),
									this.ventanaPersModificar.getTxtCallenro().getText(),
									this.ventanaPersModificar.getTxtPiso().getText(),
									this.ventanaPersModificar.getTxtDpto().getText(),
									this.ventanaPersModificar.getcBoxLocalidad().getSelectedItem().toString(), 
									this.ventanaPersModificar.getcBoxTipoContacto().getSelectedItem().toString()
									);
							this.agenda.modificarPersona(persona_a_editar);
							this.llenarTabla();
							this.ventanaPersModificar.dispose();

						}else{
							JOptionPane.showMessageDialog(ventanaPersModificar, "Deber ingresar fecha correctamente. "
									+ "Para continuar", "Atencion!", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(ventanaPersModificar, "Deber ingresar email correctamente. "
								+ "Para continuar", "Atencion!", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(ventanaPersModificar, "Debe ingresar al menos el nombre y el telefono o el año, para continuar para continuar", "Atencion!", JOptionPane.ERROR_MESSAGE);
				}
			}

			//---------------------------------------------VentanaPersona-----------------------------------------------------------------

			else if(e.getSource() == this.ventanaPersona.getBtnAgregarPersona())//Agregar nuevo contacto
			{

				if(!this.ventanaPersona.validarDatosIngresados())
				{
					if (this.validarEmail(this.ventanaPersona.getTxtEmail().getText()))
					{
						if (this.validarFecha((String)this.ventanaPersona.getJcmbDias().getSelectedItem()
								, (String)this.ventanaPersona.getJcmbMeses().getSelectedItem()
								, this.ventanaPersona.getTxtAnio().getText()))
						{
							int year = Integer.parseInt(this.ventanaPersona.getTxtAnio().getText());
							int month = this.ventanaPersona.getJcmbMeses().getSelectedIndex()+1;
							int day = this.ventanaPersona.getJcmbDias().getSelectedIndex()+1;

							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

							String date = year + "-" + month + "-" + day;
							java.util.Date cumpleanos = null;
							try {
								cumpleanos = formatter.parse(date);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							PersonaDTO nuevaPersona = new PersonaDTO(
									0,
									this.ventanaPersona.getTxtNombre().getText(), 
									this.ventanaPersona.getTxtTelefono().getText(),
									this.ventanaPersona.getTxtEmail().getText(),
									cumpleanos,
									this.ventanaPersona.getTxtDireccion().getText(),
									this.ventanaPersona.getTxtCallenro().getText(),
									this.ventanaPersona.getTxtPiso().getText(),
									this.ventanaPersona.getTxtDpto().getText(),
									this.ventanaPersona.getComboLocalidad().getSelectedItem().toString(), 
									this.ventanaPersona.getComboTipoContacto().getSelectedItem().toString()
									);



							this.agenda.agregarPersona(nuevaPersona);
							this.llenarTabla();
							this.ventanaPersona.dispose();
							this.limpiarDatosVentanaPersona();
						}
						else 
						{
							JOptionPane.showMessageDialog(ventanaPersona, "Deber ingresar fecha correctamente. "
									+ "Para continuar", "Atencion!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(ventanaPersona, "Deber ingresar email correctamente. "
								+ "Para continuar", "Atencion!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(ventanaPersona, "Debe ingresar al menos nombre, "
							+ "telefono y email. Para continuar ", "Atencion!", JOptionPane.ERROR_MESSAGE);
				}
			}

  //----------------------------------------------VentanaLocalidad-----------------------------------------------------
			
			else if(e.getSource() == this.ventanaLocalidad.getBtnAgregarLocalidad())//Accion para agregar nueva localidad
			{
				String nombreLocalidadNueva = this.ventanaLocalidad.getTxtNombre().getText();
				if(!nombreLocalidadNueva.equals(""))
				{
					LocalidadDTO nuevaLocalidad = new LocalidadDTO(0,nombreLocalidadNueva);
					this.agenda.agregarLocalidad(nuevaLocalidad);
					this.ventanaLocalidad.getTxtNombre().setText("");
					this.llenarTablaLocalidades();
					this.ocultarColumnaLocalidades();
				}
				else
				{
					JOptionPane.showMessageDialog(ventanaLocalidad, "Debe ingresar una localidad nueva antes de añadir", "Atencion!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(e.getSource() == this.ventanaLocalidad.getBtnBorrar())
			{
				if(this.ventanaLocalidad.getTablaLocalidades().getSelectedRow()==-1)
				{
					JOptionPane.showMessageDialog(ventanaPersona, "Debe seleccionar al menos una fila para continuar", "Atencion!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int[] filas_seleccionadas = this.ventanaLocalidad.getTablaLocalidades().getSelectedRows();
					
					for (int fila:filas_seleccionadas)
					{
						this.agenda.borrarLocalidad(this.localidades_en_tabla.get(fila));
					}
					
					this.llenarTablaLocalidades();
					this.ocultarColumnaLocalidades();
				}
				
			}
			else if(e.getSource() == this.ventanaLocalidad.getBtnEditar())
			{
				int fila = this.ventanaLocalidad.getTablaLocalidades().getSelectedRow();

				if (fila != -1)
				{
					this.ventanaLocalidad.getTxtLocalidadAEditar().setText((String) this.ventanaLocalidad.getTablaLocalidades()
							.getValueAt(fila, 1));
					
					this.deshabilitarElementosVentanaLocalidades();
				
				}
				else
				{
					JOptionPane.showMessageDialog(ventanaLocalidad, "Debe seleccionar una fila para continuar", "Atencion!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(e.getSource() == this.ventanaLocalidad.getBtnOk())
			{
				 int fila = this.ventanaLocalidad.getTablaLocalidades().getSelectedRow();
				 int idlocalidad =(Integer) this.ventanaLocalidad.getTablaLocalidades().getValueAt(fila, 0);
				 String Nombre = this.ventanaLocalidad.getTxtLocalidadAEditar().getText();
				 
				LocalidadDTO LocalidadModificar = new LocalidadDTO(idlocalidad,Nombre);
				
				this.agenda.modificarLocalidad(LocalidadModificar);
				
				this.habilitarElementosVentanaLocalidades();
				this.llenarTablaLocalidades();;
				this.ocultarColumnaLocalidades();;
		
			}
			else if(e.getSource() == this.ventanaLocalidad.getBtnCancel())
				
			{
				this.habilitarElementosVentanaLocalidades();
				
				
			}
			
//------------------------------------------------------ventanaTipoContacto------------------------------------------------------

			else if(e.getSource() == this.ventanaTipoContacto.getBtnGuardarTipo())//Accion del boton guardar tipo de contacto
			{
				String nombreTipoContactoNuevo = this.ventanaTipoContacto.getTxtnombre().getText();
				
				if(!nombreTipoContactoNuevo.equals(""))
				{
					TipoContactoDTO nuevoTipoContacto = new TipoContactoDTO(0,nombreTipoContactoNuevo);
					this.agenda.agregarTipoContacto(nuevoTipoContacto);
					this.ventanaTipoContacto.getTxtnombre().setText("");
					this.llenarTablaTipoContacto();
					this.ocultarColumnaTipoContacto();
				}
				else
				{
					JOptionPane.showMessageDialog(ventanaTipoContacto, "Debe ingresar una tipo de contacto nuevo antes de añadir", "Atencion!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(e.getSource() == this.ventanaTipoContacto.getBtnBorrar())
			{
				if(this.ventanaTipoContacto.getTablaTipoContactos().getSelectedRow()==-1)
				{
					JOptionPane.showMessageDialog(ventanaTipoContacto, "Debe seleccionar al menos una fila para continuar", "Atencion!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int[] filas_seleccionadas = this.ventanaTipoContacto.getTablaTipoContactos().getSelectedRows();

					for (int fila:filas_seleccionadas)
					{
						this.agenda.borrarTipoContacto(this.tipos_contactos_en_tabla.get(fila));
					}

					this.llenarTablaTipoContacto();
					this.ocultarColumnaTipoContacto();
				}
			}
			else if(e.getSource() == this.ventanaTipoContacto.getBtnEditar())
			{
				int fila = this.ventanaTipoContacto.getTablaTipoContactos().getSelectedRow();

				if (fila != -1)
				{
					this.ventanaTipoContacto.getTxtNombreEditar().setText((String) this.ventanaTipoContacto.getTablaTipoContactos()
							.getValueAt(fila, 1));
					
					this.deshabilitarElementosVentanaTipoContacto();
				
				}
				else
				{
					JOptionPane.showMessageDialog(ventanaTipoContacto, "Debe seleccionar una fila para continuar", "Atencion!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(e.getSource() == this.ventanaTipoContacto.getBtnOk())
			{
				 int fila = this.ventanaTipoContacto.getTablaTipoContactos().getSelectedRow();
				 int idTipoContacto =(Integer) this.ventanaTipoContacto.getTablaTipoContactos().getValueAt(fila, 0);
				 String Nombre = this.ventanaTipoContacto.getTxtNombreEditar().getText();
				 
				TipoContactoDTO tipContactoModificar = new TipoContactoDTO(idTipoContacto,Nombre);
				
				this.agenda.modificarTipoContacto(tipContactoModificar);
				
				this.habilitarElementosVentanaTipoCOntacto();
				this.llenarTablaTipoContacto();
				this.ocultarColumnaTipoContacto();
		
			}
			else if(e.getSource() == this.ventanaTipoContacto.getBtnCancel())
				
			{
				this.habilitarElementosVentanaTipoCOntacto();
				
				
			}
				

		}


// ---------------------------------------------------------------------Metodos auxiliares-----------------------------------------------------

		private void llenarTabla()
		{
			this.vista.getModelPersonas().setRowCount(0); //Para vaciar la tabla
			this.vista.getModelPersonas().setColumnCount(0);
			this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());
			
			this.personas_en_tabla = agenda.obtenerPersonas();

		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    
			for (int i = 0; i < this.personas_en_tabla.size(); i ++)
			{
				Date date = this.personas_en_tabla.get(i).getCumpleanos();
				
				String sDate= sdf.format(date);
				
				Object[] fila = {this.personas_en_tabla.get(i).getNombre(), this.personas_en_tabla.get(i).getTelefono(), 
						this.personas_en_tabla.get(i).getEmail(), sDate,
						this.personas_en_tabla.get(i).getCalle(), this.personas_en_tabla.get(i).getAltura(), 
						this.personas_en_tabla.get(i).getPiso(), this.personas_en_tabla.get(i).getDepto(),
						this.personas_en_tabla.get(i).getLocalidad(), this.personas_en_tabla.get(i).getTipoContacto(),this.personas_en_tabla.get(i).getIdPersona()};
				this.vista.getModelPersonas().addRow(fila);
			}
			this.vista.ocultarColumna();
			this.vista.show();
		}
		
		private void ocultarColumnaLocalidades() {
			
			this.ventanaLocalidad.getTablaLocalidades().getColumnModel().getColumn(0).setMaxWidth(0);
			this.ventanaLocalidad.getTablaLocalidades().getColumnModel().getColumn(0).setMinWidth(0);
			this.ventanaLocalidad.getTablaLocalidades().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
			this.ventanaLocalidad.getTablaLocalidades().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

		}
		
		private void ocultarColumnaTipoContacto() {
			
			this.ventanaTipoContacto.getTablaTipoContactos().getColumnModel().getColumn(0).setMaxWidth(0);
			this.ventanaTipoContacto.getTablaTipoContactos().getColumnModel().getColumn(0).setMinWidth(0);
			this.ventanaTipoContacto.getTablaTipoContactos().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
			this.ventanaTipoContacto.getTablaTipoContactos().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

		}
		
		private void llenarTablaLocalidades() {

			this.ventanaLocalidad.getModelLocalidades().setRowCount(0); //Para vaciar la tabla
			this.ventanaLocalidad.getModelLocalidades().setColumnCount(0);
			this.ventanaLocalidad.getModelLocalidades().setColumnIdentifiers(this.ventanaLocalidad.getNombreColumnas());

			this.localidades_en_tabla= agenda.obtenerLocalidades();
			for (int i = 0; i < this.localidades_en_tabla.size(); i ++)
			{
				Object[] fila = {this.localidades_en_tabla.get(i).getId(), this.localidades_en_tabla.get(i).getNombre()};
				this.ventanaLocalidad.getModelLocalidades().addRow(fila);
			}
		}

		private void llenarTablaTipoContacto() {
			
			this.ventanaTipoContacto.getModelTipoContacto().setRowCount(0); //Para vaciar la tabla
			this.ventanaTipoContacto.getModelTipoContacto().setColumnCount(0);
			this.ventanaTipoContacto.getModelTipoContacto().setColumnIdentifiers(this.ventanaTipoContacto.getNombreColumnas());
			
			this.tipos_contactos_en_tabla = agenda.obtenerTiposContactos();
			for (int i = 0; i < this.tipos_contactos_en_tabla.size(); i ++)
			{
				Object[] fila = {this.tipos_contactos_en_tabla.get(i).getId(), this.tipos_contactos_en_tabla.get(i).getNombre()};
				this.ventanaTipoContacto.getModelTipoContacto().addRow(fila);
			}

		}
		
		private void limpiarDatosVentanaPersona() {
			
			this.ventanaPersona.getTxtNombre().setText("");
			this.ventanaPersona.getTxtDireccion().setText("");
			this.ventanaPersona.getTxtEmail().setText("");
			this.ventanaPersona.getTxtTelefono().setText("");
			this.ventanaPersona.getTxtDpto().setText("");
			this.ventanaPersona.getTxtPiso().setText("");
			this.ventanaPersona.getTxtCallenro().setText("");
			this.ventanaPersona.getJcmbDias().setSelectedIndex(-1);
			this.ventanaPersona.getJcmbMeses().setSelectedIndex(-1);
			this.ventanaPersona.getComboLocalidad().setSelectedIndex(-1);
			this.ventanaPersona.getComboTipoContacto().setSelectedIndex(-1);
			this.ventanaPersona.getTxtAnio().setText("");
			
		}
		private void limpiarDatosVentanaEditarPersona() {
			
			this.ventanaPersModificar.getTxtNombre().setText("");
			this.ventanaPersModificar.getTxtDireccion().setText("");
			this.ventanaPersModificar.getTxtEmail().setText("");
			this.ventanaPersModificar.getTxtTelefono().setText("");
			this.ventanaPersModificar.getTxtDpto().setText("");
			this.ventanaPersModificar.getTxtPiso().setText("");
			this.ventanaPersModificar.getTxtCallenro().setText("");
			this.ventanaPersModificar.getJcmbDias().setSelectedIndex(-1);
			this.ventanaPersModificar.getJcmbMeses().setSelectedIndex(-1);
			this.ventanaPersModificar.getcBoxLocalidad().setSelectedIndex(-1);
			this.ventanaPersModificar.getcBoxTipoContacto().setSelectedIndex(-1);
			this.ventanaPersModificar.getTxtAnio().setText("");
			
		
		}

		private void llenarComboTipocontacto()
		{
			tipos_contactos_en_combo_agregar = this.agenda.obtenerTiposContactos();
			for (int i = 0; i < tipos_contactos_en_combo_agregar.size(); i++)
			{
					this.ventanaPersona.getComboTipoContacto().addItem(tipos_contactos_en_combo_agregar.get(i).getNombre());
			}
		}
		
		private void llenarComboLocalidad()
		{
			localidades_en_combo_agregar = this.agenda.obtenerLocalidades();
			for (int i = 0; i < localidades_en_combo_agregar.size(); i++)
			{
					this.ventanaPersona.getComboLocalidad().addItem(localidades_en_combo_agregar.get(i).getNombre());
			}
		}
		
		private void editarCargarDatos(int fila)
		{
			this.ventanaPersModificar.setVisible(true);
			
			this.ventanaPersModificar.getTxtId().setText(this.vista.getTablaPersonas().getValueAt(fila, 10).toString());
            this.ventanaPersModificar.getTxtNombre().setText((String) this.vista.getTablaPersonas().getValueAt(fila, 0));
            this.ventanaPersModificar.getTxtTelefono().setText((String) this.vista.getTablaPersonas().getValueAt(fila, 1));
            this.ventanaPersModificar.getTxtEmail().setText((String) this.vista.getTablaPersonas().getValueAt(fila, 2));
            
            this.obtenerFechaCumplanos(fila);
           
            this.ventanaPersModificar.getJcmbDias().setEditable(true);
            this.ventanaPersModificar.getJcmbDias().setSelectedIndex(Integer.parseInt(this.dia)-1);
            this.ventanaPersModificar.getJcmbDias().setEditable(false);

            this.ventanaPersModificar.getJcmbMeses().setEditable(true);
            this.ventanaPersModificar.getJcmbMeses().setSelectedIndex(Integer.parseInt(this.mes)-1);
            this.ventanaPersModificar.getJcmbMeses().setEditable(false);
            
            this.ventanaPersModificar.getTxtAnio().setText(this.anio);
            
            
            this.ventanaPersModificar.getTxtDireccion().setText((String) this.vista.getTablaPersonas().getValueAt(fila, 4));
            this.ventanaPersModificar.getTxtCallenro().setText((String) this.vista.getTablaPersonas().getValueAt(fila, 5));
            this.ventanaPersModificar.getTxtPiso().setText((String) this.vista.getTablaPersonas().getValueAt(fila, 6));
            this.ventanaPersModificar.getTxtDpto().setText((String) this.vista.getTablaPersonas().getValueAt(fila, 7));
            
            
            String textLocalidad = (String) this.vista.getTablaPersonas().getValueAt(fila, 8);
			this.ventanaPersModificar.getcBoxLocalidad().setEditable(true);
			this.ventanaPersModificar.getcBoxLocalidad().setSelectedItem(textLocalidad);
			this.ventanaPersModificar.getcBoxLocalidad().setEditable(false);
			
			String textTipoContacto = (String) this.vista.getTablaPersonas().getValueAt(fila, 9);

			this.ventanaPersModificar.getcBoxTipoContacto().setEditable(true);
			this.ventanaPersModificar.getcBoxTipoContacto().setSelectedItem(textTipoContacto);
			this.ventanaPersModificar.getcBoxTipoContacto().setEditable(false);			

			
           
		}
		
		private void llenarCombosLocalidadesTipoContactoPersonaModificar(int fila)
		{
			
			localidades_en_combo_modificar = this.agenda.obtenerLocalidades();

			for (int i = 0; i < localidades_en_combo_modificar.size(); i++)
			{
				this.ventanaPersModificar.getcBoxLocalidad().addItem(localidades_en_combo_modificar.get(i).getNombre());
			}
			tipos_contactos_en_combo_modificar = this.agenda.obtenerTiposContactos();

			for (int i = 0; i < tipos_contactos_en_combo_modificar.size(); i++)		
			{
				this.ventanaPersModificar.getcBoxTipoContacto().addItem(tipos_contactos_en_combo_modificar.get(i).getNombre());
			}

		}
		
		private void llenarComboDiasPersonaModificar() {
			
			for(int i = 0; i < this.dias.length; i++ ){
				if (i != 0){
					this.ventanaPersModificar.getJcmbDias().addItem(this.dias[i]);
				}
			}
		}
		
		private void llenarComboMesesPersonaModificar() {

			for(int i = 0; i < this.meses.length; i++ ){
				this.ventanaPersModificar.getJcmbMeses().addItem(this.meses[i]);
			}
		}

		private void obtenerFechaCumplanos(int fila) {

			String cumpleanos = (String) this.vista.getTablaPersonas().getValueAt(fila, 3);
			String delimitadores= "[ /.,;?!¡¿\'\"\\[\\]]+";
			String[] palabrasSeparadas = cumpleanos.split(delimitadores);
			this.dia = palabrasSeparadas[0].toString();
			this.mes = palabrasSeparadas[1].toString();
			this.anio = palabrasSeparadas[2].toString();
		}
		
		private void llenarComboMesesPersona() {

			for(int i = 0; i < this.meses.length; i++ ){
				this.ventanaPersona.getJcmbMeses().addItem(this.meses[i]);
			}
		}

		private void llenarComboDiasPersona() {

			for(int i = 0; i < this.dias.length; i++ ){
				if (i != 0){
					this.ventanaPersona.getJcmbDias().addItem(this.dias[i]);
				}
			}
		}
		
		private void habilitarElementosVentanaTipoCOntacto(){
			
			this.ventanaTipoContacto.getBtnGuardarTipo().setEnabled(true);
			this.ventanaTipoContacto.getBtnEditar().setEnabled(true);
			this.ventanaTipoContacto.getBtnBorrar().setEnabled(true);
			this.ventanaTipoContacto.getTablaTipoContactos().setEnabled(true);
			this.ventanaTipoContacto.getTxtnombre().setEnabled(true);
			this.ventanaTipoContacto.getBtnOk().setEnabled(false);
			this.ventanaTipoContacto.getBtnCancel().setEnabled(false);
			this.ventanaTipoContacto.getTxtNombreEditar().setText("");
		}
		
		private void deshabilitarElementosVentanaTipoContacto() {

			this.ventanaTipoContacto.getBtnGuardarTipo().setEnabled(false);
			this.ventanaTipoContacto.getBtnEditar().setEnabled(false);
			this.ventanaTipoContacto.getBtnBorrar().setEnabled(false);
			this.ventanaTipoContacto.getTablaTipoContactos().setEnabled(false);
			this.ventanaTipoContacto.getTxtnombre().setEnabled(false);
			this.ventanaTipoContacto.getBtnOk().setEnabled(true);
			this.ventanaTipoContacto.getBtnCancel().setEnabled(true);

		}
		
		private void deshabilitarElementosVentanaLocalidades() {
			
			this.ventanaLocalidad.getBtnAgregarLocalidad().setEnabled(false);
			this.ventanaLocalidad.getBtnEditar().setEnabled(false);
			this.ventanaLocalidad.getBtnBorrar().setEnabled(false);
			this.ventanaLocalidad.getTablaLocalidades().setEnabled(false);
			this.ventanaLocalidad.getTxtNombre().setEnabled(false);
			this.ventanaLocalidad.getBtnOk().setEnabled(true);
			this.ventanaLocalidad.getBtnCancel().setEnabled(true);	
		}
		
		private void habilitarElementosVentanaLocalidades() {
			
			this.ventanaLocalidad.getBtnAgregarLocalidad().setEnabled(true);
			this.ventanaLocalidad.getBtnEditar().setEnabled(true);
			this.ventanaLocalidad.getBtnBorrar().setEnabled(true);
			this.ventanaLocalidad.getTablaLocalidades().setEnabled(true);
			this.ventanaLocalidad.getTxtNombre().setEnabled(true);
			this.ventanaLocalidad.getBtnOk().setEnabled(false);
			this.ventanaLocalidad.getBtnCancel().setEnabled(false);

			this.ventanaLocalidad.getTxtLocalidadAEditar().setText("");
			
		}

		private boolean validarEmail (String email){

			final String patternEemail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			
				// Compiles the given regular expression into a pattern.
				Pattern pattern = Pattern.compile(patternEemail);

				// Match the given input against this pattern
				Matcher matcher = pattern.matcher(email);
				
				return matcher.matches();
		}
		
		private boolean validarFecha(String dia, String mes ,String anio){
			
			System.out.println(dia + mes + anio);
			
			int anio2 = 0;
			anio2 = Integer.parseInt(anio);

			if((dia.equals("29") || dia.equals("30") || dia.equals("31")) && mes.equals("Febrero")){
				return false;

			}
			if(dia.equals("31") && ((mes.equals("Abril") || mes.equals("Junio") || mes.equals("Septiembre")
					|| mes.equals("Noviembre")))){
				return false;
			}
			if ((anio2 < 1900) || (anio2 > 2016)){
				
				return false;
			}
				
		return true;
		
		}
	}		