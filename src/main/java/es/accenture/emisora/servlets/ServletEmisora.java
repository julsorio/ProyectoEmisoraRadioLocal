package es.accenture.emisora.servlets;

import java.io.IOException;

import javax.sql.DataSource;

import es.accenture.emisora.constantes.ConstantesAccion;
import es.accenture.emisora.controladores.ControladorActualizar;
import es.accenture.emisora.controladores.ControladorAlta;
import es.accenture.emisora.controladores.ControladorBaja;
import es.accenture.emisora.controladores.ControladorBuscarEventos;
import es.accenture.emisora.controladores.ControladorCerrarSesion;
import es.accenture.emisora.controladores.ControladorDetalle;
import es.accenture.emisora.controladores.ControladorInicioSesion;
import es.accenture.emisora.controladores.ControladorInsertar;
import es.accenture.emisora.controladores.ControladorModificar;
import es.accenture.emisora.controladores.ControladorObtenerGrupos;
import es.accenture.emisora.controladores.ControladorVolver;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEmisora
 */
public class ServletEmisora extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/emisora")
	private DataSource poolConexiones;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEmisora() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		String vista = null;
		
		ControladorInicioSesion inicioSesion = null;
		ControladorCerrarSesion cerrarSesion = null;
		ControladorObtenerGrupos obtenerGrupos = null;
		ControladorVolver volver = null;
		ControladorDetalle detalle = null;
		ControladorModificar modificar = null;
		ControladorBaja baja = null;
		ControladorActualizar actualizar = null;
		ControladorAlta alta = null;
		ControladorInsertar insertar = null;
		ControladorBuscarEventos buscarEventos = null;
		
		switch(accion) {
		case ConstantesAccion.ACCION_ACTUALIZAR:
			actualizar = new ControladorActualizar(poolConexiones);
			vista = actualizar.procesarPeticion(request, response);
			
			break;
		case ConstantesAccion.ACCION_ALTA:
			alta = new ControladorAlta();
			vista = alta.procesarPeticion(request, response);
			
			break;
		case ConstantesAccion.ACCION_BAJA:
			baja = new ControladorBaja(poolConexiones);
			vista = baja.procesarPeticion(request, response);
			
			break;
		case ConstantesAccion.ACCION_BUSCAR:
			buscarEventos = new ControladorBuscarEventos(poolConexiones);
			vista = buscarEventos.procesarPeticion(request, response);
			
			break;
		case ConstantesAccion.ACCION_CARGAR:
			obtenerGrupos = new ControladorObtenerGrupos(poolConexiones);
			vista = obtenerGrupos.procesarPeticion(request, response);
			
			break;
		case ConstantesAccion.ACCION_DETALLE:
			detalle = new ControladorDetalle(poolConexiones);
			vista = detalle.procesarPeticion(request, response);
			
			break;
		case ConstantesAccion.ACCION_INSERTAR:
			insertar = new ControladorInsertar(poolConexiones);
			vista = insertar.procesarPeticion(request, response);
			
			break;
		case ConstantesAccion.ACCION_LOGIN:
			inicioSesion = new ControladorInicioSesion(poolConexiones);
			vista = inicioSesion.procesarPeticion(request, response);
			
			break;
		case ConstantesAccion.ACCION_LOGOUT:
			cerrarSesion = new ControladorCerrarSesion();
			vista = cerrarSesion.procesarPeticion(request, response);
			
			break;
		case ConstantesAccion.ACCION_MODIFICAR:
			modificar = new ControladorModificar(poolConexiones);
			vista = modificar.procesarPeticion(request, response);
			
			break;
		case ConstantesAccion.ACCION_VOLVER:
			volver = new ControladorVolver();
			vista = volver.procesarPeticion(request, response);
			
			break;
		}
		
		despachador(request, response, vista, accion);
	}
	
	private void despachador(HttpServletRequest request, HttpServletResponse response, String vista, String accion) throws ServletException, IOException {
		/*RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
		dispatcher.forward(request, response);*/
		
		if(accion.equals("insertar") || accion.equals("actualizar") || accion.equals("baja")) {
    		response.sendRedirect("ServletEmisora?accion=cargar");
    	} else {
    		RequestDispatcher dispatcher =   getServletContext().getRequestDispatcher(vista);
    		dispatcher.forward(request , response);
    	}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
