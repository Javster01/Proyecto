package co.edu.uptc.Utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Archivo {

	public void AgregarContenido(String ruta, String texto) {

		File archivo = new File(ruta);

		try {

			FileWriter fstream = new FileWriter(archivo, true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(texto + ";\n");
			out.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public ArrayList<String> ContenidoArchivo(String ruta) {

		File archivo = new File(ruta);
		String cadena = null;

		ArrayList<String> finalarray = new ArrayList<String>();

		try {

			FileReader f = new FileReader(archivo);
			BufferedReader b = new BufferedReader(f);

			while ((cadena = b.readLine()) != null) {
				finalarray.add(cadena);
			}

			b.close();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return finalarray;

	}

	public void crearDirectorio(String Ruta) {

		File file = new File(Ruta);

		try {

			file.mkdir();

		} catch (Exception e) {

			e.printStackTrace();

			System.err.println("No se pudo crear el archivo");
		}
	}

}
