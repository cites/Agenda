package modelo;


import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.PersonaDTOmes;
import dto.TipoContactoDTO;
import persistencia.dao.LocalidadDAO;
import persistencia.dao.PersonaDAO;
import persistencia.dao.TipoContactoDAO;


public class Agenda 
{
	private PersonaDAO persona;	
	private LocalidadDAO localidad;
	private TipoContactoDAO tipo_contacto;
	private static PersonaDAO personaStatic;
	
	public Agenda() {
		persona = new PersonaDAO();
		localidad = new LocalidadDAO();
		tipo_contacto = new TipoContactoDAO();
	}
	//-------------------- Persona---------------------
	
	public void agregarPersona(PersonaDTO nuevaPersona) {
		persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) {
		persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas() {
		return persona.readAll();		
	}
	
	public void  modificarPersona(PersonaDTO persona_a_modificar) {
		persona.update(persona_a_modificar);
		
	}

	 public static List<PersonaDTOmes> todosMes(){
		 personaStatic =new PersonaDAO();
		 return personaStatic.todos();
	 }
	//----------------Localidades-------------------------------
	
	public void agregarLocalidad(LocalidadDTO nuevalocalidad) {

		localidad.insert(nuevalocalidad);
	}

	public List<LocalidadDTO> obtenerLocalidades() {

		return localidad.readAll();
	}

	public void borrarLocalidad(LocalidadDTO localidad_a_eliminar) {
		
		localidad.delete(localidad_a_eliminar);
	}
	
	public void modificarLocalidad (LocalidadDTO localidad_a_modificar){
		
		localidad.update(localidad_a_modificar);
		
	}
	
	//----------------tipo de COntacto--------------------------------
	
	public void agregarTipoContacto(TipoContactoDTO nuevo_tipo_contacto) {

		tipo_contacto.insert(nuevo_tipo_contacto);
	}
	
	public List<TipoContactoDTO> obtenerTiposContactos() {
		
		return tipo_contacto.readAll();
	}
	
	public void borrarTipoContacto(TipoContactoDTO tipoContacto_a_eliminar) {

		tipo_contacto.delete(tipoContacto_a_eliminar);
	}

	public void  modificarTipoContacto(TipoContactoDTO tipoContacto_a_modificar) {
		tipo_contacto.update(tipoContacto_a_modificar);

	}

}
