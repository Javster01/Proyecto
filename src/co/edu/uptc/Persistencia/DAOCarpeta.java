package co.edu.uptc.Persistencia;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import co.edu.uptc.Modelo.Nota;
import co.edu.uptc.Utilidades.Archivo;

public class DAOCarpeta {
	public void crearDirectorio(String Nombre) {

		File file = new File(Nombre);
		try {
			file.mkdir();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("No se pudo crear el archivo");
		}
	}

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

				n.setTitulo(linea[0].replace("_", ",").replace("-", ";"));
				n.setId(Integer.parseInt(linea[1]));
				n.setFecha(linea[2]);
				n.setRuta(linea[3]);
				n.setPrioridad(Integer.parseInt(linea[4]));
				n.setContenido(linea[5].replace(";", "").replace("\\n", "\n").replace("_", ",").replace("-", ";"));

				notas.add(n);

			}

		}

		return notas;
	}

}
