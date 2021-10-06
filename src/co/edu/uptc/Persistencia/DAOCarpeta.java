package co.edu.uptc.Persistencia;

import java.io.File;
import java.util.ArrayList;
import co.edu.uptc.Modelo.Carpeta;

public class DAOCarpeta {

	/**
	 * Metodo para crear carpetas
	 * 
	 * @param String Nombre
	 * 
	 **/

	public void crearDirectorio(String Nombre) {

		File file = new File("Carpetas/" + Nombre);

		try {

			file.mkdir();

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println("No se pudo crear el archivo");
		}
	}

	/**
	 * Metodo para ver las carpetas existentes
	 * 
	 * @param Carpeta c
	 * 
	 * @param String  ruta
	 * 
	 **/

	public ArrayList<Carpeta> getCarpetas(String ruta) {

		ArrayList<Carpeta> carpetas = new ArrayList<>();

		File dir = new File(ruta);

		File[] files = dir.listFiles();

		for (int i = 0; i < files.length; i++) {

			Carpeta c = new Carpeta();

			String linea[] = files[i].getPath().split("\\\\");

			c.setNombre(linea[1]);
			carpetas.add(c);
		}

		return carpetas;
	}

	/**
	 * Metodo para eliminar carpetas
	 * 
	 * @param Carpeta c
	 * 
	 * @param String  ruta
	 * 
	 **/

	public void eliminarCarpeta(Carpeta c, String ruta) {

		File dir = new File(ruta);

		File[] files = dir.listFiles();

		for (int i = 0; i < files.length; i++) {
			

			if (c.getNombre().equalsIgnoreCase(files[i].getName())) {

				File a = files[i];
				
				deleteFile(a);

			}

		}
	}
	
	/**
	 * Metodo para elimina carpetas
	 * 
	 * @param File element
	 * 
	 **/
	private void deleteFile(File element) {
		
	    if (element.isDirectory()) {
	    	
	        for (File sub : element.listFiles()) {
	        	
	            deleteFile(sub);
	        }
	    }
	    
	    element.delete();
	}

}
