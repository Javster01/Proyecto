package co.edu.uptc.Presentacion;

public class RunBlocDeNotas {

	public static void main(String[] args) {

//		new DAONotas().vaciarArchivo(n, "Notas");

		GUIListadoNotas guiPrincipal = new GUIListadoNotas("Notas");

		guiPrincipal.setLocationRelativeTo(null);

	}

}
