package co.edu.uptc.Control;

import java.util.ArrayList;
import java.util.Calendar;

import co.edu.uptc.Modelo.*;
import co.edu.uptc.Persistencia.*;

public class Control {
	
	private ArrayList<Nota> listadoNotas;
	
	public Control() {

		listadoNotas = new ArrayList<Nota>();
	}
	
	public void agregarNota(String titulo, String contenido) {

		Nota n = new Nota();
		
		Integer dia, mes, anio;

		Calendar ca = Calendar.getInstance();

		dia = ca.get(Calendar.DATE);
		mes = ca.get(Calendar.MONTH);
		anio = ca.get(Calendar.YEAR);
		
		String fecha = dia + "/" + mes + "/" + anio;
		
		n.setFecha(fecha);

		DAONotas notas = new DAONotas();

		notas.guardarNota(n);

		listadoNotas = notas.getNotas();

	}
	
	public Nota buscarNota(String id) {

		for (Nota nota : listadoNotas) {

			if ( id.equalsIgnoreCase(nota.getId())) {

				return nota;

			}

		}

		return null;
	}

	public void eliminarNota(Nota n) {

		ArrayList<Nota> lista = new ArrayList<Nota>();
		lista = listadoNotas;

		for (int i = 0; i < lista.size(); i++) {

			if (lista.get(i).getId().equalsIgnoreCase(n.getId())) {

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
