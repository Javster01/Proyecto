package co.edu.uptc.Persistencia;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import co.edu.uptc.Modelo.*;
import co.edu.uptc.Utilidades.Archivo;

/**
 * Clase para manipular la persistencia de las notas
 * 
 * @author Luis Pinto
 * 
 **/

public class DAONotas {

	/**
	 * Metodo para crear nuevos archivos
	 * 
	 * @param Nota   n
	 * 
	 * @param String ruta
	 * 
	 **/

	private ArrayList<File> archivos;

	public void guardarNota(Nota n, String ruta) {

		new Archivo().AgregarContenido(ruta, n.getTitulo() + "," + n.getId() + "," + n.getFecha() + "," + n.getRuta()
				+ "," + n.getPrioridad() + "," + n.getContenido() + "," + n.getContrasena());

	}

	/**
	 * Metodo para recorrer los archivos dentro de una carpeta
	 * 
	 * @param File folder ruta
	 * 
	 **/

	public void findAllFilesInFolder(File folder) {
		
		archivos = new ArrayList<>();

		for (File file : folder.listFiles()) {

			if (file.isFile()) {

				archivos.add(file);

			} else {

				findAllFilesInFolder(file);
			}
		}
	}

	/**
	 * Metodo para manipular las notas existentes y extraer el contenido de las
	 * notas
	 * 
	 * @param String ruta
	 * 
	 **/

	public ArrayList<Nota> getNotas(String ruta) {

		ArrayList<String> datos = new ArrayList<>();
		ArrayList<Nota> notas = new ArrayList<Nota>();
		archivos = new ArrayList<>();

		File dir = new File(ruta);

		findAllFilesInFolder(dir);

		for (int i = 0; i < archivos.size(); i++) {

			datos = new Archivo().ContenidoArchivo((archivos.get(i).getPath()));

			for (int j = 0; j < datos.size(); j++) {

				Nota n = new Nota();

				String linea[] = datos.get(j).split(",");

				n.setTitulo(linea[0].replace("_", ",").replace("-", ";"));
				n.setId(Integer.parseInt(linea[1]));
				n.setFecha(linea[2]);
				n.setRuta(linea[3]);
				n.setPrioridad(Integer.parseInt(linea[4]));
				n.setContenido(linea[5].replace(";", "").replace("\\n", "\n").replace("_", ",").replace("-", ";"));
				n.setContrasena(linea[6].replace(";", ""));

				notas.add(n);

			}

		}

		return notas;
	}

	/**
	 * Metodo para eliminar archivos
	 * 
	 * @param Nota   n
	 * 
	 * @param String ruta
	 * 
	 **/

	public void eliminarArchivo(Nota n, String ruta) {

		ArrayList<String> datos = new ArrayList<>();

		File dir = new File(ruta);

		findAllFilesInFolder(dir);

		for (int i = 0; i < archivos.size(); i++) {

			datos = new Archivo().ContenidoArchivo((archivos.get(i).getPath()));
			
			for (int j = 0; j < datos.size(); j++) {

				if (n.getRuta().equalsIgnoreCase(archivos.get(j).getPath().replace("\\", "/"))) {

					File a = archivos.get(i);

					a.delete();

				}
			
		}

		

		}
	}

}
