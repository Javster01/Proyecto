package co.edu.uptc.Presentacion;

import co.edu.uptc.Modelo.Nota;
import co.edu.uptc.Persistencia.DAONotas;

public class RunBlocDeNotas {

	public static void main(String[] args) {
		
//		new DAONotas().vaciarArchivo(n, "Notas");

		GUIListadoNotas guiPrincipal = new GUIListadoNotas("Notas");

		guiPrincipal.setLocationRelativeTo(null);
		
	}

}
