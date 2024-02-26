package es.accenture.emisora.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import es.accenture.emisora.constantes.ConstantesError;
import es.accenture.emisora.entidades.Grupo;
import es.accenture.emisora.excepciones.ExcepcionPropia;

public class ModGrupo {

	private DataSource poolConexiones;
	
	public ModGrupo(DataSource dataSource) {
		this.poolConexiones = dataSource;
	}
	
	public List<Grupo> getGrupos() throws Exception {
		List<Grupo> listaGrupos = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet resultado = null;
		
		String query = "SELECT grupoId,nombre,origen,creacion,genero FROM GRUPOS";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.createStatement();
			resultado = miStatement.executeQuery(query);
			
			if (resultado != null) {

				while (resultado.next()) {
					Grupo grupo = new Grupo();
					grupo.setGrupoId(resultado.getInt("grupoId"));
					grupo.setNombre(resultado.getString("nombre"));
					grupo.setOrigen(resultado.getString("origen"));
					grupo.setCreacion(resultado.getInt("creacion"));
					grupo.setGenero(resultado.getString("genero"));

					listaGrupos.add(grupo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcepcionPropia(ConstantesError.ERROR_GRUPOS);
		} finally {
			resultado.close();
			miStatement.close();
		}
		
		return listaGrupos;
	}
	
	public Grupo getGrupo(int idGrupo) throws Exception {
		Grupo grupoEncontrado = null;
		Connection miConexion = null;
		PreparedStatement  miStatement = null;
		ResultSet resultado = null;
		
		String query = "SELECT grupoId,nombre,origen,creacion,genero FROM grupos WHERE grupoId = ?";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.prepareStatement(query);
			miStatement.setInt(1, idGrupo);
			resultado = miStatement.executeQuery();
			
			if (resultado != null) {

				while (resultado.next()) {
					grupoEncontrado = new Grupo();
					grupoEncontrado.setGrupoId(resultado.getInt("grupoId"));
					grupoEncontrado.setNombre(resultado.getString("nombre"));
					grupoEncontrado.setOrigen(resultado.getString("origen"));
					grupoEncontrado.setCreacion(resultado.getInt("creacion"));
					grupoEncontrado.setGenero(resultado.getString("genero"));
				}
			} else {
				System.out.println("No hay resultados para el grupo con id " + idGrupo);
			}
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al realizar la consulta " + e.getMessage());
			e.printStackTrace();
		} finally {
			resultado.close();
			miStatement.close();
		}
		
		return grupoEncontrado;
	}
	
	public void insertarGrupo(Grupo grupo) throws Exception {
		Connection miConexion = null;
		PreparedStatement  miStatement = null;
		
		String query = "INSERT INTO grupos (nombre, creacion, origen, genero) VALUES (?,?,?,?)";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.prepareStatement(query);
			miStatement.setString(1, grupo.getNombre());
			miStatement.setInt(2, grupo.getCreacion());
			miStatement.setString(3, grupo.getOrigen());
			miStatement.setString(4, grupo.getGenero());
			miStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al realizar insert en la tabla grupos " + e.getMessage());
			e.printStackTrace();
		} finally {
			miStatement.close();
		}
	}
	
	public void actualizarGrupo(Grupo grupo) throws Exception {
		Connection miConexion = null;
		PreparedStatement  miStatement = null;
		
		String query = "UPDATE grupos SET nombre = ?, creacion = ?, origen = ?, genero = ? WHERE grupoId = ?";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.prepareStatement(query);
			miStatement.setString(1, grupo.getNombre());
			miStatement.setInt(2, grupo.getCreacion());
			miStatement.setString(3, grupo.getOrigen());
			miStatement.setString(4, grupo.getGenero());
			miStatement.setInt(5, grupo.getGrupoId());
			miStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error al realizar update en la tabla grupos " + e.getMessage());
			e.printStackTrace();
		} finally {
			miStatement.close();
		}
	}
	
	public void eliminarGrupo(int grupoId) throws Exception {
		Connection miConexion = null;
		PreparedStatement  miStatement = null;
		
		String query = "DELETE FROM grupos WHERE grupoId = ?";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.prepareStatement(query);
			miStatement.setInt(1, grupoId);
			miStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al realizar delete en la tabla grupos " + e.getMessage());
			e.printStackTrace();
		} finally {
			miStatement.close();
		}
	}
}
