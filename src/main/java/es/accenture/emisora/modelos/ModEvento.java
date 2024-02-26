package es.accenture.emisora.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import es.accenture.emisora.constantes.ConstantesError;
import es.accenture.emisora.entidades.Evento;
import es.accenture.emisora.excepciones.ExcepcionPropia;

public class ModEvento {
	
	private DataSource poolConexiones;
	
	public ModEvento(DataSource dataSource) {
		this.poolConexiones = dataSource;
	}
	
	public List<Evento> getEventos() throws ExcepcionPropia {
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet resultado = null;
		
		List<Evento> listaEventos = new ArrayList<>();
		
		String query = "SELECT eventoId,nombre,descripcion,lugar,duracion,tipoEvento,asientosDisp FROM eventos";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.createStatement();
			resultado = miStatement.executeQuery(query);
			
			if(resultado != null) {
				while(resultado.next()) {
					Evento evento = new Evento();
					evento.setAsientosDisp(resultado.getInt("asientosDisp"));
					evento.setDescripcion(resultado.getString("descripcion"));
					evento.setDuracion(resultado.getString("duracion"));
					evento.setEventoId(resultado.getInt("eventoId"));
					evento.setLugar(resultado.getString("lugar"));
					evento.setNombre(resultado.getString("nombre"));
					evento.setTipoEvento(resultado.getString("tipoEvento"));
					
					listaEventos.add(evento);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcepcionPropia(ConstantesError.ERROR_EVENTOS);
		}
		
		return listaEventos;
	}
	
	public List<Evento> getEventos(String criterio) throws ExcepcionPropia {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		ResultSet resultado = null;
		
		List<Evento> listaEventos = new ArrayList<>();
		
		String query = "SELECT eventoId,nombre,descripcion,lugar,duracion,tipoEvento,asientosDisp FROM eventos WHERE nombre like ? OR descripcion like ?";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.prepareStatement(query);
			miStatement.setString(1, "%" + criterio + "%");
			miStatement.setString(2, "%" + criterio + "%");
			resultado = miStatement.executeQuery();
			
			if(resultado != null) {
				while(resultado.next()) {
					Evento evento = new Evento();
					evento.setAsientosDisp(resultado.getInt("asientosDisp"));
					evento.setDescripcion(resultado.getString("descripcion"));
					evento.setDuracion(resultado.getString("duracion"));
					evento.setEventoId(resultado.getInt("eventoId"));
					evento.setLugar(resultado.getString("lugar"));
					evento.setNombre(resultado.getString("nombre"));
					evento.setTipoEvento(resultado.getString("tipoEvento"));
					
					listaEventos.add(evento);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcepcionPropia(ConstantesError.ERROR_EVENTOS);
		}
		
		return listaEventos;
	}
}
