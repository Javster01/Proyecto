package co.edu.uptc.Utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Archivo {

	public boolean SobreescribirArchivo(String ruta, String texto) {

		File archivo = new File(ruta);
		boolean existe = false;
		BufferedWriter bw;

		try {
			if (archivo.exists()) {
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write(texto + ";\n");
				existe = true;
			} else {
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write(texto);
				existe = false;

			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return existe;

	}

	public void AgregarContenido(String ruta, String texto) {

		// String ruta = "Resources/" + nombre + ".txt";
		File archivo = new File(ruta);
		

		try {

			FileWriter fstream = new FileWriter(archivo, true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(texto + ";\n");
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String ContenidoArchivoTotal(String ruta) {

		File archivo = new File(ruta);
		String cadena = null;
		String cadenaFinal = "++";

		try {

			FileReader f = new FileReader(archivo);
			BufferedReader b = new BufferedReader(f);

			while ((cadena = b.readLine()) != null) {
				cadenaFinal = cadena + "++" + cadenaFinal;
			}

			b.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return cadenaFinal;

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
			// TODO Auto-generated catch block
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

	public void eliminarArchivo(String ruta) {

		File a = new File(ruta);

		a.delete();
	}

	public void resetArchivo(String ruta) {

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
