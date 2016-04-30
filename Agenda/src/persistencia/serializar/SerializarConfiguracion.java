package persistencia.serializar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dto.ConfiguracionDTO;

public class SerializarConfiguracion {
	
	public SerializarConfiguracion (){
		
	}

	public void Serializar(ConfiguracionDTO configuracion){

		try 
		
		{
			FileOutputStream fos = new FileOutputStream("config.bin");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(configuracion);
			out.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}
	
	public ConfiguracionDTO DeSerializar(){
		
		ConfiguracionDTO nueva = null;
		try
		{
			FileInputStream fis = new FileInputStream("config.bin");
			ObjectInputStream in = new ObjectInputStream(fis);
			nueva = (ConfiguracionDTO) in.readObject();
			in.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return nueva;
		
	}

}
