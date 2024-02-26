package es.accenture.emisora.controladores;

import javax.sql.DataSource;

import es.accenture.emisora.constantes.ConstantesVistas;
import es.accenture.emisora.entidades.Grupo;
import es.accenture.emisora.modelos.ModGrupo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControladorBaja implements IControlador {
	private ModGrupo modeloGrupo = null;
	
	public ControladorBaja(DataSource dataSource) {
		modeloGrupo = new ModGrupo(dataSource);
	}

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		int grupoId = Integer.parseInt(request.getParameter("grupoId"));
		Grupo grupo = null;
		
		try {
			grupo = modeloGrupo.getGrupo(grupoId);
			modeloGrupo.eliminarGrupo(grupo.getGrupoId());
			
			return ConstantesVistas.VISTA_GRUPOS_MUSICALES;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			
			return ConstantesVistas.VISTA_GRUPOS_MUSICALES;
		}
		
	}

}
