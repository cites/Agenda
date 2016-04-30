package main;

import dto.ConfiguracionDTO;
import modelo.Agenda;
import persistencia.conexion.Conexion;

import persistencia.serializar.SerializarConfiguracion;
import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorConfiguracion;
import presentacion.vista.VentanaConfiguracion;
import presentacion.vista.Vista;

public class Main 
{
	public static void main(String[] args) 
	{
		SerializarConfiguracion serializarConf = new SerializarConfiguracion();

		ConfiguracionDTO  configuracionInicial = serializarConf.DeSerializar();

		if (configuracionInicial == null)
		{
			VentanaConfiguracion configuracion = new VentanaConfiguracion();
			ControladorConfiguracion controladorConfig= new ControladorConfiguracion(configuracion);

			
			controladorConfig.iniciar();
		}
		else
		{
			ConfiguracionDTO otra = serializarConf.DeSerializar();
			Conexion.setConection(otra);
			
			if(Conexion.isFallo())//si falla la conexion le vuelvo a pedir los datos de conexion a la base
			{
				VentanaConfiguracion configuracion = new VentanaConfiguracion();
				ControladorConfiguracion controladorConfig= new ControladorConfiguracion(configuracion);
				
				controladorConfig.iniciar();
			}
			else
			{
				Vista vista = new Vista();
				Agenda modelo = new Agenda();

				Controlador controlador = new Controlador(vista,  modelo);
				controlador.inicializar();
			}
		}
	}
}