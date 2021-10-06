package co.edu.uptc.Modelo;

/**
 * Esta clase define los atributos de los objetos de tipo nota
 * 
 * @author Luis Pinto
 * 
 **/

public class Nota {

	private String titulo, contenido, fecha, ruta;
	private int id, Prioridad;

	public int getPrioridad() {
		return Prioridad;
	}

	public void setPrioridad(int Prioridad) {
		this.Prioridad = Prioridad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

}
