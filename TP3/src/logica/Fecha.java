package logica;

import java.util.LinkedList;
import java.util.List;

public class Fecha {

	private LinkedList<Partido> partidos= new LinkedList<Partido>();
	private int numero;
	
	Fecha(int numero){
		
	}
	
	
	public int getNumero() {
  		return numero;
  	}

	
	public List<Partido> getPartidos() {
		return partidos;
	}

	
	public boolean existePartido(Partido partido) {
		return partidos.contains(partido);
	}

	public List<Partido> partidosSinArbitro(){
		
		List<Partido> partidosSinArbitro = new LinkedList<Partido>();
		for(Partido partido: partidos) {
			if(partido.getArbitro() == null) {
				partidosSinArbitro.add(partido);
			}
		}
		return partidosSinArbitro;
	}

	
	
}
