package Main;
import archivos.CalendarioJSON;
import archivos.EquiposJSON;
import vistas.UIMain;
import vistas.VentanaPrincipal;



public class Main {

	public static void main(String[] args){
		CalendarioJSON calendario= CalendarioJSON.leerJSON("calendario.json"); //leo el archivo json
		EquiposJSON listaDeEquipos= EquiposJSON.leerJSON("listaDeEquipos.json");
		VentanaPrincipal ventana= new VentanaPrincipal();
		UIMain main= new UIMain(ventana);
		main.cargarCalendarioAlTorneo(calendario.getCalendario(), listaDeEquipos.getListaDeEquipos());
		main.mostrarVentanaPrincipal();
	}

}
