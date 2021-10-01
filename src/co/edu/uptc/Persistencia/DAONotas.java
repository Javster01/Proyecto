package co.edu.uptc.Persistencia;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import co.edu.uptc.Modelo.*;
import co.edu.uptc.Utilidades.Archivo;

public class DAONotas {

	// titulo, id, fecha, ruta, urgencia, contenido

	// Metodo para agregar notas

	public void guardarNota(Nota n, String ruta) {

		new Archivo().AgregarContenido(ruta, n.getTitulo() + "," + n.getId() + "," + n.getFecha() + "," + n.getRuta()
				+ "," + n.getUrgencia() + "," + n.getContenido());

	}

	// Metodo para extraer los nombres de las notas del txt

	public String[] getNombresArchivos(String ruta) {

		ArrayList<String> nombresArchivos = new ArrayList<>();

		File dir = new File(ruta);

		File[] files = dir.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".txt");
			}
		});

		for (int i = 0; i < files.length; i++) {

			nombresArchivos.add(files[i].getName().replace(".txt", ""));
		}

		if (files.length == 0) {
			System.out.println("El directorio no contiene extensiones de tipo '.txt'");

		}
		String[] nombres = new String[nombresArchivos.size()];

		for (int i = 0; i < nombres.length; i++) {
			nombres[i] = nombresArchivos.get(i);
		}

		return nombres;
	}

	// Metodo para extraer las notas del txt

	public ArrayList<Nota> getNotas(String ruta) {

		ArrayList<String> datos = new ArrayList<>();
		ArrayList<Nota> notas = new ArrayList<Nota>();

		File dir = new File(ruta);

		File[] files = dir.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".txt");
			}
		});

		for (int i = 0; i < files.length; i++) {

			datos = new Archivo().ContenidoArchivo((files[i].getPath()));

			for (int j = 0; j < datos.size(); j++) {

				Nota n = new Nota();

				String linea[] = datos.get(j).split(",");

				n.setTitulo(linea[0]);
				n.setId(Integer.parseInt(linea[1]));
				n.setFecha(linea[2]);
				n.setRuta(linea[3]);
				n.setUrgencia(Integer.parseInt(linea[4]));
				n.setContenido(linea[5].replace(";", "").replace("\\n", "\n"));

				notas.add(n);

			}

		}

		return notas;
	}

	// Metodo para resetear el archivo para modifcar algo en la nota

	public void eliminarArchivo(Nota n, String ruta) {

		File dir = new File(ruta);

		File[] files = dir.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".txt");
			}
		});

		for (int i = 0; i < files.length; i++) {

			if (n.getRuta().equalsIgnoreCase(files[i].getPath().toString().replace("\\", "/"))) {

				File a = files[i];

				a.delete();

			}

		}
	}

}
