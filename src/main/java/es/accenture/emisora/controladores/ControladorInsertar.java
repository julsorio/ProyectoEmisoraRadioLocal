package es.accenture.emisora.controladores;

import javax.sql.DataSource;

import es.accenture.emisora.constantes.ConstantesVistas;
import es.accenture.emisora.entidades.Grupo;
import es.accenture.emisora.modelos.ModGrupo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControladorInsertar implements IControlador {
	private ModGrupo modeloGrupo;

	public ControladorInsertar(DataSource dataSource) {
		modeloGrupo = new ModGrupo(dataSource);
	}
	
	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		String nombre = request.getParameter("nombre");
		int creacion = Integer.parseInt(request.getParameter("creacion"));
		String origen = request.getParameter("origen");
		String genero = request.getParameter("genero");
		
		Grupo grupo = new Grupo();
		grupo.setCreacion(creacion);
		grupo.setGenero(genero);
		grupo.setNombre(nombre);
		grupo.setOrigen(origen);
		
		try {
			modeloGrupo.insertarGrupo(grupo);
			
			return ConstantesVistas.VISTA_GRUPOS_MUSICALES;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			
			return ConstantesVistas.VISTA_GRUPOS_MUSICALES;
		}
		
		
	}

}
