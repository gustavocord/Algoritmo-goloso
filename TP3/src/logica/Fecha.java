package logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Fecha {

	private ArrayList<Partido> partidos= new ArrayList<Partido>();
	private int numero;
	
	Fecha(int numero){
		this.numero=numero;
	}
	
	public int getNumero() {
  		return numero;
  	}

	
	public ArrayList<Partido> getPartidos() {
		return partidos;
	}

	
	public boolean existePartido(Partido partido) {
		return partidos.contains(partido);
	}

	public ArrayList<Partido> partidosSinArbitro(){
		
		ArrayList<Partido> partidosSinArbitro = new ArrayList<Partido>();
		for(Partido partido: partidos) {
			if(partido.getArbitro() == null) {
				partidosSinArbitro.add(partido);
			}
		}
		return partidosSinArbitro;
	}
	
	public int cantPartidos() {
		return partidos.size();
	}

	
	public void agregarPartido(Partido partido) {
		partidos.add(partido);
	}

	@Override
	public String toString() {
		return "Fecha [numero= " + numero + " Partidos "+ partidos+ "]";
	}



	
	
}
