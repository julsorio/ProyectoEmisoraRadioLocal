package es.accenture.emisora.excepciones;

public class ExcepcionPropia extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcepcionPropia(String mensajeError) {
		super(mensajeError);
	}

}
