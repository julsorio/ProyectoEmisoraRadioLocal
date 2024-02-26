package es.accenture.emisora.controladores;

import javax.sql.DataSource;

import es.accenture.emisora.constantes.ConstantesError;
import es.accenture.emisora.constantes.ConstantesVistas;
import es.accenture.emisora.entidades.Usuario;
import es.accenture.emisora.modelos.ModUsuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ControladorInicioSesion implements IControlador {
	
	private ModUsuario modeloUsuario;
	
	public ControladorInicioSesion(DataSource dataSource) {
		modeloUsuario = new ModUsuario(dataSource);
	}

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		//se obtienen los datos ingresados en el formulario
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		Usuario user = null;
		
		if((usuario == null || usuario.equals("")) || (password == null || password.equals(""))) {
			//los campos del formulario vienen vacios
			//se muestra un mensaje de error en la pagina de login
			request.setAttribute("error", ConstantesError.DATOS_LOGIN_VACIOS);
			
			return ConstantesVistas.VISTA_LOGIN;
		}
		
		try {
			user = modeloUsuario.getUsuario(usuario, password);
			
			if(user == null || !password.equals(user.getPassword())) {
				request.setAttribute("error", ConstantesError.DATOS_LOGIN_ERRADOS);
				
				return ConstantesVistas.VISTA_LOGIN; 
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			return ConstantesVistas.VISTA_BUSQUEDA_EVENTOS;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			
			return ConstantesVistas.VISTA_LOGIN;
		}
		
	}

}
