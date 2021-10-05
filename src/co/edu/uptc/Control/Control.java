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
	private ArrayList<Integer> ids;
	private String[] nombresArchivos;
	private boolean orden;

	/**
	 * Constructor para crear y configurar el frame principal
	 * 
	 * @param String ruta
	 * 
	 **/

	public Control(String ruta) {

		ids = new ArrayList<>();
		listadoNotas = new DAONotas().getNotas(ruta + "/");
		organizarAlfabeticamente();
		orden = true;

	}

	public boolean getOrden() {
		return orden;
	}

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

	public void agregarNota(String titulo, String contenido, String ruta, int Prioridad) {

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
		n.setRuta(ruta + "/" + n.getTitulo() + "���" + n.getId() + ".txt");
		n.setFecha(fecha);

		DAONotas notas = new DAONotas();

		notas.guardarNota(n, n.getRuta());

		listadoNotas = notas.getNotas(ruta);

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

			if (lista.get(i).getId() == n.getId()) {

				lista.remove(n);
				new DAONotas().eliminarArchivo(n, ruta);

			}
		}

		listadoNotas = lista;

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

	public void editarArchivo(Nota n, String titulo, String contenido, String ruta, int Prioridad) {

		Nota n1 = new Nota();
		n1.setTitulo(titulo);
		n1.setContenido(contenido);
		n1.setPrioridad(Prioridad);

		eliminarNota(n, ruta);
		agregarNota(n1.getTitulo(), n1.getContenido(), ruta, n1.getPrioridad());

		if (getOrden() == true) {

			organizarAlfabeticamente();

		} else if (getOrden() == false) {

			organizarPrioritariamente();

		}

	}

}
