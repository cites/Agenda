package dto;

import java.util.Date;

public class PersonaDTOmes 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private String email;
	private Date Cumpleanos;
	private String calle;
	private String altura;
	private String piso;
	private String depto;
	private String localidad;
	private String tipoContacto;
	private String mes;


	@SuppressWarnings("deprecation")
	public PersonaDTOmes(int idPersona, String nombre, String telefono, String email, Date Cumpleanos, String calle, String altura,
			String piso, String depto, String localidad, String tipoContacto)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.Cumpleanos = Cumpleanos;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.tipoContacto = tipoContacto;
		
		switch (Cumpleanos.getMonth()+1) {
		case 1:
			this.mes = "ENERO";
			break;
		
		case 2:
			this.mes = "FEBRERO";
			break;
			
		case 3:
			this.mes = "MARZO";
			break;
		
		case 4:
			this.mes = "ABRIL";
			break;
			
		case 5:
			this.mes = "MAYO";
			break;
			
		case 6:
			this.mes = "JUNIO";
			break;
			
		case 7:
			this.mes = "JULIO";
			break;
			
		case 8:
			this.mes = "AGOSTO";
			break;
		
		case 9:
			this.mes = "SEPTIEMBRE";
			break;
			
		case 10:
			this.mes = "OCTUBRE";
			break;
		
		case 11:
			this.mes = "NOVIEMBRE";
			break;
			
		case 12:
			this.mes = "DICIEMBRE";
			break;
		
		default:
			break;
		}
		
	}
	
	public String getMes() {
		return mes;
	}

	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	public String getEmail() {
		
		return this.email;
	}

	public void setEmail(String email) {
		
		this.email = email;
	}

	public Date getCumpleanos() {
		
		return this.Cumpleanos;
	}

	public void setFechaCumpleanos(Date fechaCumpleanos) {
		
		this.Cumpleanos = fechaCumpleanos;
	}

	public String getCalle() {
		
		return this.calle;
	}

	public void setCalle(String calle) {
		
		this.calle = calle;
	}

	public String getAltura() {
		
		return this.altura;
	}

	public void setAltura(String altura) {
		
		this.altura = altura;
	}

	public String getPiso() {
		
		return this.piso;
	}

	public void setPiso(String piso) {
		
		this.piso = piso;
	}

	public String getDepto() {
		
		return this.depto;
	}

	public void setDepto(String depto) {
		
		this.depto = depto;
	}

	public String getLocalidad() {
		
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		
		this.localidad = localidad;
	}

	public String getTipoContacto() {
		
		return this.tipoContacto;
	}

	public void setTipoContacto(String tipoContacto) {
		
		this.tipoContacto = tipoContacto;
	}

	
}
