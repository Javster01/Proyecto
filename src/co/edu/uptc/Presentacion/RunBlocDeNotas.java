package co.edu.uptc.Presentacion;

import co.edu.uptc.Modelo.Nota;

public class RunBlocDeNotas {

	public static void main(String[] args) {

//		new Menu().menuBloc();
		Nota n = new Nota();
		n.setId("1");
		n.setTitulo("Nota de prueba");
		n.setRuta("Notas");
		n.setFecha("hola");
		n.setContenido("fghjklkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
//		new DAONotas().guardarNota(n);

//		GUINota frame = new GUINota(n);
//		frame.setVisible(true);
//		frame.setResizable(true);
//		frame.setLocationRelativeTo(null);
		
		GUIPrincipal guiPrincipal = new GUIPrincipal();

		guiPrincipal.setLocationRelativeTo(null);
	}

}
