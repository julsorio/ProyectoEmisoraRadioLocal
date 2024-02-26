package es.accenture.emisora.entidades;

/**
 * @author Andrea Ravagli Castillo
 */
public class Componente {
	private int componenteId;
	private String nombre;
	private int grupoId;
	private String instrumento;

	public int getComponenteId() {
		return componenteId;
	}

	public void setComponenteId(int componenteId) {
		this.componenteId = componenteId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(int grupoId) {
		this.grupoId = grupoId;
	}

	public String getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

}
