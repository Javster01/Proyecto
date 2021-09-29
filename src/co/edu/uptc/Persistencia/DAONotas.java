package co.edu.uptc.Persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import co.edu.uptc.Modelo.*;
import co.edu.uptc.Utilidades.Archivo;

public class DAONotas {

	// nombre, id, fecha, ruta, urgencia, contenido

	private String ruta;
//
//	private String crearRuta() {
//
//		ruta = nomCarpeta + "/" + n.getTitulo() + "_" + n.getId() + ".txt";
//
//		return ruta;
//	}

	// Metodo para agregar notas
//	public void guardarNota(Nota n) {
//
//		new Archivo().AgregarContenido(crearRuta(n, n.getRuta()), n.getTitulo() + "," + n.getId() + "," + n.getFecha()
//				+ "," + crearRuta(n, n.getRuta()) + "," + n.getUrgencia() + "," + n.getContenido());
//
//		new DAORegistroNotas().guardarRegistro(n.getRuta());
//
//	}

	// Metodo para extraer el contendio del txt
	public String[] getNombresArchivos() {
		
		ArrayList<String> nombresArchivos = new ArrayList<>();
		

		File dir = new File("Notas");

		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".txt");
			}
		});

		for (int i = 0; i < files.length; i++) {
			
			nombresArchivos.add(files[i].getName().replace(".txt",""));
		}
		
		if (files.length == 0) {
			System.out.println("El directorio no contiene extensiones de tipo '.txt'");

//			ArrayList<String> datos = new Archivo().ContenidoArchivo(new DAORegistroNotas().notasRegistradas());
//		ArrayList<Nota> notas = new ArrayList<Nota>();
//
//		for (int i = 0; i < datos.size(); i++) {
//
//			Nota n = new Nota();
//
//			String linea[] = datos.get(i).split("_");
//
//			n.setTitulo(linea[0]);
//			n.setId(Integer.parseInt(linea[1]));
//			n.setFecha(linea[2]);
//			n.setRuta(linea[3]);
//			n.setUrgencia(Integer.parseInt(linea[4]));
//			n.setContenido(linea[5].replace(";", ""));
//
//			notas.add(n);

		}
		String[] nombres = new String [nombresArchivos.size()] ;
		
		for (int i = 0; i < nombres.length; i++) {
			nombres [i] = nombresArchivos.get(i);
//			System.out.println(nombres [i]);
		}

		return nombres;
	}

	// Metodo para resetear el archivo para modifcar algo en la nota
	public void resetArchivo() {

		File a = new File(ruta);
		BufferedWriter bw;

		if (a.exists()) {

			try {

				bw = new BufferedWriter(new FileWriter(a));
				bw.write("");

			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

}
