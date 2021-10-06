package co.edu.uptc.Presentacion;

import co.edu.uptc.Control.Control;

/**
 * Programa con estilo app de celular para gestion de notas
 * 
 * @author Luis Pinto
 * 
 * @author Javier Ortiz
 * 
 * @apiNote Bloc De Notas
 * 
 **/

public class RunBlocDeNotas {

	public static void main(String[] args) {

		Control c = new Control("Notas");

		GUIListadoNotas guiPrincipal = new GUIListadoNotas("Notas", c);

		guiPrincipal.setLocationRelativeTo(null);

	}

}
