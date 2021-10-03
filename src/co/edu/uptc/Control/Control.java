package co.edu.uptc.Control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import co.edu.uptc.Modelo.*;
import co.edu.uptc.Persistencia.*;

public class Control {

	private ArrayList<Nota> listadoNotas;
	private ArrayList<Integer> ids;
	private String[] nombresArchivos;

	public Control(String ruta) {

		ids = new ArrayList<>();
		listadoNotas = new DAONotas().getNotas(ruta + "/");
		nombresArchivos = new String[listadoNotas.size()];

	}

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

	public void organizarPrioritariamente() {

		Collections.sort(listadoNotas, new Comparator<Nota>() {

			@Override
			public int compare(Nota o1, Nota o2) {

				Integer nota1 = o1.getUrgencia();
				Integer nota2 = o2.getUrgencia();

				return nota2.compareTo(nota1);
			}
		});

	}

	public String[] getNombresArchivos() {

		for (int i = 0; i < nombresArchivos.length; i++) {

			nombresArchivos[i] = listadoNotas.get(i).getTitulo();

		}

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

		n.setTitulo(titulo.replace(",", "_").replace(";", "-"));
		n.setContenido(contenido.replace("\n", "\\n").replace(",", "_").replace(";", "-"));

		int id = (int) (Math.random() * (1000));
		n.setId(id);

		while (seRepiteId(n) == true) {

			id = (int) (Math.random() * (1000));

		}

		n.setId(id);
		ids.add(id);

		n.setUrgencia(urgencia);
		n.setRuta(ruta + "/" + n.getTitulo() + "°¬°" + n.getId() + ".txt");
		n.setFecha(fecha);

		DAONotas notas = new DAONotas();

		notas.guardarNota(n, n.getRuta());

		listadoNotas = notas.getNotas(ruta);

	}

	public Nota getNota(int indice) {

		Nota nota = new Nota();

		if (listadoNotas.get(indice) != null) {

			nota = listadoNotas.get(indice);

			return nota;
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

	public void editarArchivo(Nota n, String titulo, String contenido, String ruta, int urgencia) {

		Nota n1 = new Nota();
		n1.setTitulo(titulo);
		n1.setContenido(contenido);
		n1.setUrgencia(urgencia);

		eliminarNota(n, ruta);
		agregarNota(n1.getTitulo(), n1.getContenido(), ruta, n1.getUrgencia());

	}

}
