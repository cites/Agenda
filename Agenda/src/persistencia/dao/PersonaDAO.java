package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PersonaDTO;
import dto.PersonaDTOmes;
import persistencia.conexion.Conexion;

public class PersonaDAO 
{
	private static final String insert = "INSERT INTO personas ( `Nombre`, `Telefono`, `Email`, `Cumpleanos`, `Calle`, "
			+ "`Altura`, `Piso`, `Depto`, `Localidad`, `TipoContacto`)"
			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	
	private static final String readall = "SELECT * FROM personas";
	
	private static final String readallGroup = "SELECT * FROM personas order by Cumpleanos";
	
	private static final String update = "UPDATE personas SET Nombre = ?, "
			+ " Telefono = ?, Email = ?, Cumpleanos = ?, "
			+ " Calle = ?,  Piso = ?, Depto = ?,Altura = ?, Localidad = ? , TipoContacto = "
			+ " ? WHERE idPersona= ? ";
	
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getEmail());
			statement.setDate(4, new java.sql.Date(persona.getCumpleanos().getTime()));
			statement.setString(5, persona.getCalle());
			statement.setString(6, persona.getAltura());
			statement.setString(7, persona.getPiso());
			statement.setString(8, persona.getDepto());
			statement.setString(9, persona.getLocalidad());
			statement.setString(10, persona.getTipoContacto());
			
			if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			System.out.println("ocurrio un error e el insert");
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecut� devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				//convierto el sqlDate a utils.Date de java para que se muestre bien
			    java.util.Date cumpleanos = new java.util.Date(resultSet.getDate("Cumpleanos").getTime());
				
				personas.add(new PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"), resultSet.getString("Telefono")
						, resultSet.getString("Email"), cumpleanos , resultSet.getString("Calle"), resultSet.getString("Altura"), 
						resultSet.getString("Piso"), resultSet.getString("Depto"),resultSet.getString("Localidad"), resultSet.getString("TipoContacto")
						));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return personas;
	}
	
	public boolean update (PersonaDTO persona_a_modificar)
	{	
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			
			statement.setString(1, persona_a_modificar.getNombre());
			statement.setString(2, persona_a_modificar.getTelefono());
			statement.setString(3, persona_a_modificar.getEmail());
			statement.setDate(4, new java.sql.Date(persona_a_modificar.getCumpleanos().getTime()));
			statement.setString(5, persona_a_modificar.getCalle());
			statement.setString(6, persona_a_modificar.getAltura());
			statement.setString(7, persona_a_modificar.getPiso());
			statement.setString(8, persona_a_modificar.getDepto());
			statement.setString(9, persona_a_modificar.getLocalidad());
			statement.setString(10, persona_a_modificar.getTipoContacto());
			statement.setInt(11, persona_a_modificar.getIdPersona());
			
			if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
		
	}

	public List<PersonaDTOmes> todos() {

		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTOmes> personas = new ArrayList<PersonaDTOmes>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readallGroup);
			resultSet = statement.executeQuery();

			while(resultSet.next())
			{
				//convierto el sqlDate a utils.Date de java para que se muestre bien
				java.util.Date cumpleanos = new java.util.Date(resultSet.getDate("Cumpleanos").getTime());
				

				personas.add(new PersonaDTOmes(resultSet.getInt("idPersona"), resultSet.getString("Nombre"), resultSet.getString("Telefono")
						, resultSet.getString("Email"), cumpleanos , resultSet.getString("Calle"), resultSet.getString("Altura"), 
						resultSet.getString("Piso"), resultSet.getString("Depto"),resultSet.getString("Localidad"), resultSet.getString("TipoContacto")
						));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return personas;
	}
}
