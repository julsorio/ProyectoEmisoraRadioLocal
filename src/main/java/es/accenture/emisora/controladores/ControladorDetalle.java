package es.accenture.emisora.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import es.accenture.emisora.constantes.ConstantesVistas;
import es.accenture.emisora.entidades.Componente;
import es.accenture.emisora.entidades.Grupo;
import es.accenture.emisora.modelos.ModComponente;
import es.accenture.emisora.modelos.ModGrupo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControladorDetalle implements IControlador {
	
	private ModGrupo modeloGrupo = null;
	private ModComponente modeloComponente = null;
	
	public ControladorDetalle(DataSource dataSource) {
		modeloGrupo = new ModGrupo(dataSource);
		modeloComponente = new ModComponente(dataSource);
	}

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		int grupoId = Integer.parseInt(request.getParameter("grupoId"));
		Grupo grupo = null;
		List<Componente> listaComponentes = new ArrayList<>();
		
		
		try {
			grupo = modeloGrupo.getGrupo(grupoId);
			request.setAttribute("grupo", grupo);
			
			listaComponentes = modeloComponente.getComponentes(grupoId);
			request.setAttribute("listaComponentes", listaComponentes);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ConstantesVistas.VISTA_DETALLE_GRUPO;
	}

}
