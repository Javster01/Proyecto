package co.edu.uptc.Control;

import java.util.ArrayList;
import java.util.Calendar;
import co.edu.uptc.Modelo.*;
import co.edu.uptc.Persistencia.*;

public class Control {

	private ArrayList<Nota> listadoNotas;
	private ArrayList<Integer> ids;
	private String[] nombresArchivos;

	public Control(String ruta) {

		DAONotas d = new DAONotas();
		nombresArchivos = d.getNombresArchivos(ruta);
		ids = new ArrayList<>();
		listadoNotas = new DAONotas().getNotas("Notas/");

	}

	public String[] getNombresArchivos() {
		return nombresArchivos;
	}

	public ArrayList<Nota> getListadoNotas() {
		return listadoNotas;
	}

	private boolean seRepiteId(Nota n) {

		for (Integer integer : ids) {

			if (integer == n.getId()) {

				return true;
			}
		}

		return false;
	}

	public void agregarNota(String titulo, String contenido, String ruta, int urgencia) {

		Nota n = new Nota();

		Integer dia, mes, anio;

		Calendar ca = Calendar.getInstance();

		dia = ca.get(Calendar.DATE);
		mes = ca.get(Calendar.MONTH);
		anio = ca.get(Calendar.YEAR);

		String fecha = dia + "/" + mes + "/" + anio;

		n.setTitulo(titulo);
		n.setContenido(contenido);

		int id = (int) (Math.random() * (1000));
		n.setId(id);

		while (seRepiteId(n) == true) {

			id = (int) (Math.random() * (1000));

		}

		n.setId(id);
		ids.add(id);

		n.setUrgencia(urgencia);
		n.setRuta(ruta + "/" + n.getTitulo() + ".txt");
		n.setFecha(fecha);

		DAONotas notas = new DAONotas();

		notas.guardarNota(n, n.getRuta());

		listadoNotas = notas.getNotas(ruta);

	}

	public Nota buscarNota(String nombre) {

		for (Nota nota : listadoNotas) {

			if (nombre.equalsIgnoreCase(nota.getTitulo())) {

				return nota;

			}

		}

		return null;
	}

	public void eliminarNota(Nota n, String ruta) {

		ArrayList<Nota> lista = listadoNotas;

		for (int i = 0; i < lista.size(); i++) {

			if (lista.get(i).getId() == n.getId()) {

				new DAONotas().eliminarArchivo(n, ruta);
				lista.remove(n);

			}
		}

		listadoNotas = lista;

	}

	public void editarArchivo(Nota n, String ruta) {

		eliminarNota(n, ruta);
		agregarNota(n.getTitulo(), n.getContenido(), n.getRuta(), n.getUrgencia());

	}

}
