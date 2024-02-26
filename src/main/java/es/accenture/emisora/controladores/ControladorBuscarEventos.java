package es.accenture.emisora.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import es.accenture.emisora.constantes.ConstantesError;
import es.accenture.emisora.constantes.ConstantesVistas;
import es.accenture.emisora.entidades.Evento;
import es.accenture.emisora.modelos.ModEvento;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControladorBuscarEventos implements IControlador {
	
	private ModEvento modeloEvento;
	
	public ControladorBuscarEventos(DataSource dataSource) {
		modeloEvento = new ModEvento(dataSource);
	}

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		String filtro = request.getParameter("filtro");
		List<Evento> listaEventos = new ArrayList<>();
		
		try {
			if(filtro == null || (filtro != null && filtro.equals(""))) {
				listaEventos = modeloEvento.getEventos();
			} else {
				listaEventos = modeloEvento.getEventos(filtro);
			}
			
			if(listaEventos.isEmpty()) {
				request.setAttribute("error", ConstantesError.CONSULTA_EVENTOS_SIN_RESULTADO);
			}
			
			request.setAttribute("listaEventos", listaEventos);
			
			return ConstantesVistas.VISTA_MOSTRAR_EVENTOS;
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			
			return ConstantesVistas.VISTA_BUSQUEDA_EVENTOS;
		}
		
		
	}

}
