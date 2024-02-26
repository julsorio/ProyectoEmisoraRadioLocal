package es.accenture.emisora.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import es.accenture.emisora.entidades.Componente;

public class ModComponente {
	private DataSource poolConexiones;
	
	public ModComponente(DataSource dataSource) {
		this.poolConexiones = dataSource;
	}
	
	public List<Componente> getComponentes(int grupoId) throws Exception {
		Connection miConexion = null;
		PreparedStatement  miStatement = null;
		ResultSet resultado = null;
		List<Componente> listaComponentes = new ArrayList<>();
		
		String query2 = "SELECT componenteId,nombre,instrumento FROM componentes WHERE grupoId = ?";

		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.prepareStatement(query2);
			miStatement.setInt(1, grupoId);
			resultado = miStatement.executeQuery();
			
			if(resultado != null) {
				while(resultado.next()) {
					Componente componente = new Componente();
					componente.setComponenteId(resultado.getInt("componenteId"));
					componente.setGrupoId(grupoId);
					componente.setInstrumento(resultado.getString("instrumento"));
					componente.setNombre(resultado.getString("nombre"));
					
					listaComponentes.add(componente);
				}
			} else {
				System.out.println("No hay componentes para el grupo con id " + grupoId);
			}
			
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al realizar la consulta " + e.getMessage());
			e.printStackTrace();
		} finally {
			resultado.close();
			miStatement.close();
		}
		
		return listaComponentes;
	}
}
