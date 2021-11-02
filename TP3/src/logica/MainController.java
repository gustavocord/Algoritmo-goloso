package logica;

import java.util.ArrayList;

import archivos.Archivo;

//mañana construir 

public class MainController {

	
	private Torneo torneo;

	public MainController() {
		this.torneo  = Archivo.leerJSON();
	}

	public int cantFechas() {
		return this.torneo.getFechas().size();
		
	}
	
	public int cantPartidos() {
		return torneo.cantEquipos()/2;
	}
	
	public int cantEquipos() {
		return torneo.cantEquipos();
	}
	
	public int cantArbitros() {
		return torneo.cantArbitros();
	}
	
	public ArrayList<String> getEquipos(){
		ArrayList<String> ret = new ArrayList<>();
		
		for(Equipo equipo : torneo.getEquipos()) {
			String nombre = equipo.getNombre();
			ret.add(nombre);
		}
		return ret;
			
	}
	
	public String asignarPartido(int fecha, int partido) {
		String equipo1 = fechas().get(fecha).getPartidos().get(partido).getEquipo1().getNombre();
		String equipo2 = fechas().get(fecha).getPartidos().get(partido).getEquipo2().getNombre();
		String ret = equipo1 + " vs " + equipo2;
		return ret;
	} 
	
	private ArrayList<Fecha> fechas(){
		ArrayList<Fecha> fechasTorneo = torneo.getFechas();
		return fechasTorneo;
	}

	public ArrayList<Integer> getArbitros() {
		ArrayList<Integer> ret = new ArrayList<>();
		
		for(Arbitro arbitro : torneo.getArbitros()) {
			int numero = arbitro.getNro();
			ret.add(numero);
		}
		return ret;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainController m= new MainController();
		System.out.print(m.getArbitros());  
		System.out.println(m.getEquipos());

		
		
		

	}


}
