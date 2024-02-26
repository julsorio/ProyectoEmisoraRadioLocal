package es.accenture.emisora.controladores;

import java.util.List;

import javax.sql.DataSource;

import es.accenture.emisora.constantes.ConstantesVistas;
import es.accenture.emisora.entidades.Grupo;
import es.accenture.emisora.modelos.ModGrupo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControladorObtenerGrupos implements IControlador {
	
	private ModGrupo modeloGrupo;
	
	public ControladorObtenerGrupos(DataSource dataSource) {
		modeloGrupo = new ModGrupo(dataSource);
	}
	

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		List<Grupo> listaGrupos = null;
		
		try {
			listaGrupos = modeloGrupo.getGrupos();
			request.setAttribute("listaGrupos", listaGrupos);
			
			return ConstantesVistas.VISTA_GRUPOS_MUSICALES;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			
			return ConstantesVistas.VISTA_BUSQUEDA_EVENTOS;
		}
		
	}

}
