package es.accenture.emisora.controladores;

import es.accenture.emisora.constantes.ConstantesVistas;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControladorAlta implements IControlador {

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		return ConstantesVistas.VISTA_ALTA_GRUPO;
	}

}
