package co.edu.uptc.Persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.Modelo.*;
import co.edu.uptc.Utilidades.Archivo;

public class DAONotas {

	private String ruta;

	private String crearRuta(Nota n, String nomCarpeta) {

		ruta = nomCarpeta + "/" + n.getTitulo() + "_" + n.getId() + ".txt";

		return ruta;
	}

	// Metodo para agregar notas
	public void guardarNota(Nota n) {

		new Archivo().AgregarContenido(crearRuta(n, n.getRuta()), n.getTitulo() + "," + n.getId() + "," + n.getFecha()
				+ "," + crearRuta(n, n.getRuta()) + "," + n.getContenido());

	}

	//Metodo para extraer el contendio del txt
	public ArrayList<Nota> getNotas() {

		ArrayList<String> datos = new Archivo().ContenidoArchivo(ruta);
		ArrayList<Nota> notas = new ArrayList<Nota>();

		for (int i = 0; i < datos.size(); i++) {

			Nota n = new Nota();

			String linea[] = datos.get(i).split("_");

			n.setTitulo(linea[0]);
			n.setContenido(linea[1].replace(";", ""));

			notas.add(n);

		}

		return notas;
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
