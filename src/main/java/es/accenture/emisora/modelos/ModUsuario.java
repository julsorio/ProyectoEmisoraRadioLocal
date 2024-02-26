package es.accenture.emisora.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import es.accenture.emisora.constantes.ConstantesError;
import es.accenture.emisora.entidades.Usuario;

public class ModUsuario {
	private DataSource poolConexiones;
	
	public ModUsuario(DataSource dataSource) {
		this.poolConexiones = dataSource;
	}
	
	public Usuario getUsuario(String usuario, String password) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		ResultSet resultado = null;
		
		Usuario user = null;
		
		String query = "SELECT usuarioId, nombre, apellido, dni,email,telefono,direccion,usuario,password FROM usuarios WHERE usuario = ? ";
		
		try {
			miConexion = poolConexiones.getConnection();
			miStatement = miConexion.prepareStatement(query);
			miStatement.setString(1, usuario);
			resultado = miStatement.executeQuery();
			
			if(resultado != null) {
				while(resultado.next()) {
					user = new Usuario();
					user.setApellido(resultado.getString("apellido"));
					user.setDireccion(resultado.getString("direccion"));
					user.setDni(resultado.getString("dni"));
					user.setEmail(resultado.getString("email"));
					user.setNombre(resultado.getString("nombre"));
					user.setPassword(resultado.getString("password"));
					user.setTelefono(resultado.getString("telefono"));
					user.setUsuario(resultado.getString("usuario"));
					user.setUsuarioId(resultado.getInt("usuarioId"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(ConstantesError.ERROR_USUARIOS);
		} finally {
			resultado.close();
			miStatement.close();
		}
		
		return user;
	}
}
