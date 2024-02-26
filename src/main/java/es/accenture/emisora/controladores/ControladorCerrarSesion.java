package es.accenture.emisora.controladores;

import es.accenture.emisora.constantes.ConstantesVistas;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ControladorCerrarSesion implements IControlador {

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		
		return ConstantesVistas.VISTA_LOGIN;
	}

}
