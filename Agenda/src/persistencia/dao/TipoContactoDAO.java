package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;

public class TipoContactoDAO {
	
	private static final String insert = "INSERT INTO TipoContacto(idTipoContacto, TipoContacto) "
			+ "VALUES(?, ?)";
	private static final String delete = "DELETE FROM TipoContacto WHERE idTipoContacto = ?";
	private static final String readall = "SELECT * FROM TipoContacto;";
	private static final String update = "UPDATE TipoContacto SET TipoContacto = ?  WHERE idTipoContacto = ? ;";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(TipoContactoDTO tipoContacto)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, tipoContacto.getId());
			statement.setString(2, tipoContacto.getNombre());
			
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
	
	public boolean delete(TipoContactoDTO tipoContacto_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(tipoContacto_a_eliminar.getId()));
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
	
	public List<TipoContactoDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<TipoContactoDTO> tiposDeContactos = new ArrayList<TipoContactoDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				tiposDeContactos.add(new TipoContactoDTO(resultSet.getInt("idTipoContacto"), resultSet.getString("TipoContacto")));
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
		
		return tiposDeContactos;
	}
	
	public boolean update (TipoContactoDTO TipoContacto_a_modificar)

	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);

			statement.setString(1, TipoContacto_a_modificar.getNombre());
			statement.setInt(2, TipoContacto_a_modificar.getId());

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
}
