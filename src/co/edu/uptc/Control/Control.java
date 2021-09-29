package co.edu.uptc.Control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import co.edu.uptc.Modelo.*;
import co.edu.uptc.Persistencia.*;

public class Control {

	private ArrayList<Nota> listadoNotas;
	private String[] nombresArchivos;

	public Control() {

		DAONotas d = new DAONotas();

		nombresArchivos = d.getNombresArchivos();

	

//		listadoNotas = new DAONotas().getNotas();
//		System.out.println(listadoNotas.size());

	}

	

	public String[] getNombresArchivos() {
		return nombresArchivos;
	}



	public void setNombresArchivos(String[] nombresArchivos) {
		this.nombresArchivos = nombresArchivos;
	}



	public ArrayList<Nota> getListadoNotas() {
		return listadoNotas;
	}

	public void setListadoNotas(ArrayList<Nota> listadoNotas) {
		this.listadoNotas = listadoNotas;
	}

	public void agregarNota(String titulo, String contenido) {

		Nota n = new Nota();

		Integer dia, mes, anio;

		Calendar ca = Calendar.getInstance();

		dia = ca.get(Calendar.DATE);
		mes = ca.get(Calendar.MONTH);
		anio = ca.get(Calendar.YEAR);

		String fecha = dia + "/" + mes + "/" + anio;

		n.setTitulo(titulo);
		n.setContenido(contenido);
		n.setId(listadoNotas.size() + 1);
		n.setFecha(fecha);

		DAONotas notas = new DAONotas();
//
//		notas.guardarNota(n);
//
//		listadoNotas = notas.getNotas();

	}

	public Nota buscarNota(int id) {

		for (Nota nota : listadoNotas) {

			if (id == nota.getId()) {

				return nota;

			}

		}

		return null;
	}

	public void eliminarNota(Nota n) {

		ArrayList<Nota> lista = new ArrayList<Nota>();
		lista = listadoNotas;

		for (int i = 0; i < lista.size(); i++) {

			if (lista.get(i).getId() == n.getId()) {

				lista.remove(n);

			}
		}

		sobreEscribirNotas(lista);

	}

	private void sobreEscribirNotas(ArrayList<Nota> lista) {

		new DAONotas().resetArchivo();

		for (int i = 0; i < lista.size(); i++) {

			agregarNota(lista.get(i).getTitulo(), lista.get(i).getContenido());

		}

	}

}
