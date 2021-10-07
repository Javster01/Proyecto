package co.edu.uptc.Control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import co.edu.uptc.Modelo.*;
import co.edu.uptc.Persistencia.*;

/**
 * Clase para controlar el intercambio de informacion hacia las ventanas
 * 
 * @author Luis Pinto
 * 
 **/

public class Control {

	private ArrayList<Nota> listadoNotas;
	private ArrayList<Carpeta> listaCarpetas;
	private ArrayList<Integer> ids;
	private String[] nombresArchivos;
	private String[] nombresCarpetas;
	private boolean orden;

	/**
	 * Constructor para crear controles
	 * 
	 * @param String ruta
	 * 
	 **/

	public Control(String ruta) {

		ids = new ArrayList<>();
		listadoNotas = new DAONotas().getNotas(ruta + "/");
		listaCarpetas = new DAOCarpeta().getCarpetas(ruta);
		organizarAlfabeticamente();
		orden = true;

	}
	
	/**
	 * Metodo para obtener si el orden alfabetico esta puesto si es true
	 * 
	 * @return boolean orden
	 * 
	 **/

	public boolean getOrden() {
		return orden;
	}
	
	/**
	 * Metodo para declarar el tipo de orden segun ture o false
	 * 
	 * @param boolean orden
	 * 
	 **/

	public void setOrden(boolean orden) {
		this.orden = orden;
	}

	/**
	 * Metodo para organizar las notas por orden alfabetico
	 **/

	public void organizarAlfabeticamente() {

		Collections.sort(listadoNotas, new Comparator<Nota>() {

			@Override
			public int compare(Nota o1, Nota o2) {

				String nota1 = o1.getTitulo();
				String nota2 = o2.getTitulo();

				return nota1.compareTo(nota2);
			}
		});

	}

	/**
	 * Metodo para organizar las notas por orden prioritario
	 **/

	public void organizarPrioritariamente() {

		Collections.sort(listadoNotas, new Comparator<Nota>() {

			@Override
			public int compare(Nota o1, Nota o2) {

				Integer nota1 = o1.getPrioridad();
				Integer nota2 = o2.getPrioridad();

				return nota2.compareTo(nota1);
			}
		});

	}

	/**
	 * Metodo para traer el nombre de todas las notas
	 * 
	 * @return String nombresArchivos
	 * 
	 **/

	public String[] getNombresArchivos() {

		nombresArchivos = new String[listadoNotas.size()];

		for (int i = 0; i < nombresArchivos.length; i++) {

			nombresArchivos[i] = listadoNotas.get(i).getTitulo();

		}

		return nombresArchivos;
	}

	/**
	 * Metodo para traer el nombre de todas las carpetas
	 * 
	 * @return String nombresArchivos
	 * 
	 **/

	public String[] getNombresCarpetas() {

		nombresCarpetas = new String[listaCarpetas.size()];

		for (int i = 0; i < nombresCarpetas.length; i++) {

			nombresCarpetas[i] = listaCarpetas.get(i).getNombre();

		}

		return nombresCarpetas;
	}

	public ArrayList<Nota> getListadoNotas() {
		return listadoNotas;
	}

	/**
	 * Metodo para verificar si se repite el id de una nota
	 * 
	 * @param Nota n
	 * 
	 * @return boolean aux
	 * 
	 **/

	private boolean seRepiteId(Nota n) {

		boolean aux = false;

		for (Integer integer : ids) {

			if (integer == n.getId()) {

				aux = true;
			}
		}

		return aux;
	}

	/**
	 * Metodo para crear crear una nota
	 * 
	 * @param String titulo
	 * 
	 * @param String contenido
	 * 
	 * @param String ruta
	 * 
	 * @param int    Prioridad
	 * 
	 **/

	public void agregarNota(String titulo, String contenido, String ruta, int Prioridad, String contrasena) {

		Nota n = new Nota();

		Integer dia, mes, anio;

		Calendar ca = Calendar.getInstance();

		dia = ca.get(Calendar.DATE);
		mes = ca.get(Calendar.MONTH);
		anio = ca.get(Calendar.YEAR);

		String fecha = dia + "/" + mes + "/" + anio;

		n.setTitulo(titulo.replace(",", "_").replace(";", "-"));
		n.setContenido(contenido.replace("\n", "\\n").replace(",", "_").replace(";", "-"));

		int id = (int) (Math.random() * (1000));
		n.setId(id);

		while (seRepiteId(n) == true) {

			id = (int) (Math.random() * (1000));

		}

		n.setId(id);
		ids.add(id);

		n.setPrioridad(Prioridad);
		n.setRuta(ruta + "/" + n.getTitulo() + "'" + n.getId() + ".txt");
		n.setFecha(fecha);
		n.setContrasena(contrasena);

		DAONotas notas = new DAONotas();

		notas.guardarNota(n, n.getRuta());

		listadoNotas = notas.getNotas(ruta);

	}
	
	/**
	 * Metodo para crear crear una nota
	 * 
	 * @param String nombre
	 * 
	 * @param String ruta
	 * 
	 **/

	public void agregarCarpeta(String nombre, String ruta) {

		Carpeta c = new Carpeta();

		c.setNombre(nombre);

		DAOCarpeta carpetas = new DAOCarpeta();

		carpetas.crearDirectorio(nombre);

		listaCarpetas = carpetas.getCarpetas(ruta);

	}

	/**
	 * Metodo para traer una nota del arreglo de las notas segun el indice
	 * 
	 * @param int indice
	 * 
	 * @return Nota nota
	 * 
	 **/

	public Nota getNota(int indice) {

		Nota nota = new Nota();

		if (listadoNotas.get(indice) != null) {

			nota = listadoNotas.get(indice);

		} else {

			nota = null;
		}

		return nota;
	}

	/**
	 * Metodo para traer una carpeta del arreglo de las carpetas segun el indice
	 * 
	 * @param int indice
	 * 
	 * @return Carpeta carpeta
	 * 
	 **/

	public Carpeta getCarpeta(int indice) {

		Carpeta carpeta = new Carpeta();

		if (listaCarpetas.get(indice) != null) {

			carpeta = listaCarpetas.get(indice);

		} else {

			carpeta = null;
		}

		return carpeta;
	}

	/**
	 * Metodo para eliminar una nota
	 * 
	 * @param Nota   n
	 * 
	 * @param String ruta
	 * 
	 **/

	public void eliminarNota(Nota n, String ruta) {

		ArrayList<Nota> lista = listadoNotas;

		for (int i = 0; i < lista.size(); i++) {

			if (lista.get(i).getRuta().equals(n.getRuta())) {

				new DAONotas().eliminarArchivo(n, ruta);
				lista.remove(n);

			}
		}

		listadoNotas = lista;

	}

	/**
	 * Metodo para eliminar una carpeta
	 * 
	 * @param Carpeta c
	 * 
	 * @param String  ruta
	 * 
	 **/

	public void eliminarCarpeta(Carpeta c, String ruta) {

		ArrayList<Carpeta> lista = listaCarpetas;

		for (int i = 0; i < lista.size(); i++) {

			if (lista.get(i).getNombre().equals(c.getNombre())) {

				new DAOCarpeta().eliminarCarpeta(c, ruta);
				lista.remove(c);

			}
		}

		listaCarpetas = lista;

	}

	/**
	 * Metodo para editar una nota
	 * 
	 * @param Nota   n
	 * 
	 * @param String titulo
	 * 
	 * @param String contenido
	 * 
	 * @param String ruta
	 * 
	 * @param int    Prioridad
	 * 
	 **/

	public void editarArchivo(Nota n, String titulo, String contenido, String ruta, int Prioridad, String contrasena) {

		Nota n1 = new Nota();
		n1.setTitulo(titulo);
		n1.setContenido(contenido);
		n1.setPrioridad(Prioridad);
		n1.setContrasena(contrasena);

		eliminarNota(n, ruta);
		agregarNota(n1.getTitulo(), n1.getContenido(), ruta, n1.getPrioridad(), n1.getContrasena());

		if (getOrden() == true) {

			organizarAlfabeticamente();

		} else if (getOrden() == false) {

			organizarPrioritariamente();

		}

	}

	public void contrasena() {

	}

}
