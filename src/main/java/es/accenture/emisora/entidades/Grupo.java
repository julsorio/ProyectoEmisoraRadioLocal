package es.accenture.emisora.entidades;

import java.util.List;

/**
 * @author Andrea Ravagli Castillo
 */
public class Grupo {
	private int grupoId;
	private String nombre;
	private int creacion;
	private String origen;
	private String genero;
	private int discograficaIdActual;
	
	private List<Componente> componentes;

	public int getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(int grupoId) {
		this.grupoId = grupoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCreacion() {
		return creacion;
	}

	public void setCreacion(int creacion) {
		this.creacion = creacion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDiscograficaIdActual() {
		return discograficaIdActual;
	}

	public void setDiscograficaIdActual(int discograficaIdActual) {
		this.discograficaIdActual = discograficaIdActual;
	}

	public List<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}

}
