package es.accenture.emisora.controladores;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IControlador {
	public String procesarPeticion(HttpServletRequest request,HttpServletResponse response);
}
