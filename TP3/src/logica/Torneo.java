package logica;

import java.util.ArrayList;
import java.util.HashMap;





public class Torneo {

	private ArrayList<Equipo> equipos;
	private ArrayList<Fecha> fechas;
	private ArrayList<Arbitro> arbitros;
	//private int[][] arbitrajesPorEquipo;
	//private HashMap<Fecha, ArrayList<Integer>> arbitrosDisponibles;
	
	public Torneo() {
		this.equipos = new ArrayList<>();
		this.arbitros = new ArrayList<Arbitro>();
		this.fechas = new ArrayList<Fecha>();
	//	this.arbitrosDisponibles = new HashMap<Fecha, ArrayList<Integer>>();
	}

	@Override
	public String toString() {
		return "Torneo [equipos=" + equipos + ", fechas=" + fechas + ", arbitros=" + arbitros + "]";
	}

	
/* 
 * 	public void generarFixture() {
		
		if (equipos.size()==0) 
			throw new IllegalArgumentException("el fixture no se puede generar sin equipos");
		
		
		if (equipos.size()%2==1) 
			throw new IllegalArgumentException("el fixture no se puede generar con una cantidad de equipos impares");
		
		
		// se crean todas la fechas sin partidos
		//se crean un array vacio de arbitrosdisponibles por cada fecha
		generarFechas();

		//se agrega la cant de partidos correspondientes a cada fecha
		generarPartidos();
		
		// se agrega el local de cada partido de cada fecha
		agregarEquiposLocales();
		
		// se agrega el visitante de cada partido de cada fecha
		agregarEquiposVisitantes();
		
		// inicializamos la tabla donde dice la cantidad de arbitrajes en cada equipo
		// inicializamos los arbitros disponibles por cada fecha
		inicializarArbitrajes();
		
	}


	private void inicializarArbitrajes() {
		// inicializamos la tabla donde dice la cantidad de arbitrajes en cada equipo
		// inicializamos los arbitros disponibles por cada fecha
		this.arbitrajesPorEquipo = new int [cantEquipos()][cantArbitros()];
		for (int i = 0; i < cantEquipos()/2; i++) {
			arbitros.add(new Arbitro (i));
		}

		for(int i = 0; i < this.cantEquipos(); i++) {
			for (int j = 0; j < this.cantArbitros(); j++) {
				arbitrajesPorEquipo[i][j] = 0;
				
				if((i < this.cantEquipos() - 1) && !(arbitrosDisponibles.get(fechas.get(i)).contains(j)))
					
					arbitrosDisponibles.get(fechas.get(i)).add(j);
			}
		}
		
	}


	private void agregarEquiposVisitantes() {
		int equipoMasAlto = cantEquipos() - 1;
		for (int i = 0; i < fechas.size(); i++) {

			Equipo equipoVisitante = equipos.get(equipoMasAlto);
			fechas.get(i).getPartidos().get(0).setEquipo2(equipoVisitante);
		}

		int equipoImparMasAlto = equipoMasAlto - 1;
		for (int i = 0, k = equipoImparMasAlto; i < fechas.size(); i++) {

			for (int j = 1; j < fechas.get(i).cantPartidos(); j++) {
				Equipo equipoVisitante = equipos.get(k);
				fechas.get(i).getPartidos().get(j).setEquipo2(equipoVisitante);
				k--;

				if (k == -1)
					k = equipoImparMasAlto;
			}
		}
	}


	private void agregarEquiposLocales() {
		for (int i = 0, k = 0; i < fechas.size(); i++) {

			for (int j = 0; j < fechas.get(i).cantPartidos(); j++) {

				Equipo equipoLocal = equipos.get(k);
				fechas.get(i).getPartidos().get(j).setEquipo1(equipoLocal);
				k++;

				if (k == fechas.size())
					k = 0;
			}
		}
	}


	private void generarPartidos() {
		int cantPartidos = cantEquipos()/2;
		for (Fecha fecha : fechas) {
			for (int i = 0; i < cantPartidos; i++) {
				Partido partidoNuevo = new Partido("", "");
				fecha.agregarPartido(partidoNuevo);
			}
		}
	}


	private void generarFechas() {
		for (int i = 1; i < cantEquipos() ; i++) {
			Fecha fechaNueva = new Fecha(i);
			fechas.add(fechaNueva);
			arbitrosDisponibles.put(fechaNueva, new ArrayList<Integer>());
		}
	}
	

	
	// ---------------------------------------------------------------------------------------//
	
	public void agregarArbitraje(Fecha fecha, Partido partido, int numeroArbitro) {
		
		if(!estaDisponible(fecha, numeroArbitro)) {
			throw new IllegalArgumentException("El arbitro no esta disponible para esa fecha. |numero:"+ numeroArbitro +"|");
		}
		
		if(!fecha.existePartido(partido)) {
			throw new IllegalArgumentException("El partido no existe en la fecha indicada.");
		}
		
		if(partido.getArbitro()!=null)
			throw new RuntimeException("El partido indicado ya tiene arbitro designado." + partido.toString());
		
		
		for (int i = 0; i < arbitrosDisponibles.get(fecha).size(); i++) {
			if(arbitrosDisponibles.get(fecha).get(i) == numeroArbitro)
				arbitrosDisponibles.get(fecha).remove(i);
		}
		
		//agrego al contador de equipos x arbitros
		int iEq1 = equipos.indexOf(partido.getEquipo1());
		int iEq2 = equipos.indexOf(partido.getEquipo2());
		arbitrajesPorEquipo[iEq1][numeroArbitro] ++;
		arbitrajesPorEquipo[iEq2][numeroArbitro] ++;
		
		//agrego al arbitro al partido
		partido.setArbitro(numeroArbitro);
		
		if(!estaArbitro(numeroArbitro))
			arbitros.add(partido.getArbitro());
	}
	
	
	public int arbitroConPromedioMinimo(Equipo eq1, Equipo eq2) {

		int iEq1 = obtenerIndiceEquipo(eq1);
		int iEq2 = obtenerIndiceEquipo(eq2);
		double promedioMinimo = (arbitrajesPorEquipo[iEq1][0] + arbitrajesPorEquipo[iEq2][0]) / 2;
		int indiceArbitro = 0;
		
		for (int j = 0; j < cantArbitros(); j++) {
			double actual = (arbitrajesPorEquipo[iEq1][j] + arbitrajesPorEquipo[iEq2][j]) / 2;

			if (actual < promedioMinimo) {
				promedioMinimo = actual;
				indiceArbitro = j;
			}
		}
		return indiceArbitro;
	}
	
	public int arbitroConPromedioMinimoDisponible(Fecha fecha, Equipo eq1, Equipo eq2) {

		if (!arbitrosDisponibles.get(fecha).isEmpty()) {
			
			int iEq1 = obtenerIndiceEquipo(eq1);
			int iEq2 = obtenerIndiceEquipo(eq2);
			
			int indiceArbitro = arbitrosDisponibles.get(fecha).get(0);
			double promedioMinimo = (arbitrajesPorEquipo[iEq1][indiceArbitro]
					                + arbitrajesPorEquipo[iEq2][indiceArbitro]) / 2;

			for (int j = 0; j < arbitrosDisponibles.get(fecha).size(); j++) {
				indiceArbitro = arbitrosDisponibles.get(fecha).get(j);
				double actual = (arbitrajesPorEquipo[iEq1][j] + arbitrajesPorEquipo[iEq2][j]) / 2;

				if (actual < promedioMinimo) {
					promedioMinimo = actual;
					indiceArbitro = j;
				}
			}
			return indiceArbitro;
		} else {
			throw new RuntimeException("No hay arbitros disponibles.");
		}
	}
	//------------------------------AUXILIARES-----------------------------------------//
	
	// chequea si un arbitro ya esta en la lista de arbitros
	private boolean estaArbitro(int numeroArbitro) {
		for (Arbitro arb : arbitros) {
			if (arb.getNro() == numeroArbitro)
				return true;
		}
		return false;
	}

	// chequea si un arbitro en una fecha determinada esta disponible
	private boolean estaDisponible(Fecha fecha, int numeroArbitro) {
		for (int nroarbitro : arbitrosDisponibles.get(fecha)) {
			if (nroarbitro == numeroArbitro) {
				return true;
			}
		}
		return false;
	}

	private int obtenerIndiceEquipo(Equipo equipo) {
		return equipos.indexOf(equipo);
	}

	public void agregarEquipo(String nombre) {
		equipos.add(new Equipo(nombre));
	}

	public int cantEquipos() {
		return equipos.size();
	}

	public int cantArbitros() {
		return equipos.size() / 2;
	}
	
	public ArrayList<Fecha> fechasSinArbitro() {
		ArrayList<Fecha> fechasSinArbitro = new ArrayList<Fecha>();
		
		for(Fecha fecha: fechas) {
			if(fecha.partidosSinArbitro().size() == fecha.cantPartidos())
				fechasSinArbitro.add(fecha);
		}
		return fechasSinArbitro;
	}

	//----------------------------------GETTERS Y SETTERS---------------------------------//
	@SuppressWarnings("unchecked")
	public ArrayList<Fecha> getFechas() {
		return (ArrayList<Fecha>) fechas.clone();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Equipo> getEquipos() {
		return (ArrayList<Equipo>) equipos.clone();
	}

	public int[][] getArbitrajesPorEquipo() {
		return arbitrajesPorEquipo.clone();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Arbitro> getArbitros() {
		return (ArrayList<Arbitro>) arbitros.clone();
	}

	@SuppressWarnings("unchecked")
	public HashMap<Fecha, ArrayList<Integer>> getArbitrosDisponibles() {
		return (HashMap<Fecha, ArrayList<Integer>>) arbitrosDisponibles.clone();
	}

	public void setArbitrosDisponibles(HashMap<Fecha, ArrayList<Integer>> arbitrosDisponibles) {
		this.arbitrosDisponibles = arbitrosDisponibles;
	}
	
	public void setEquipos(ArrayList<Equipo> equipos) {
		this.equipos = equipos;
	}

	public void setFechas(ArrayList<Fecha> fechas) {
		this.fechas = fechas;
	}

	public void setArbitros(ArrayList<Arbitro> arbitros) {
		this.arbitros = arbitros;
	}

	public void setArbitrajesPorEquipo(int[][] arbitrajesPorEquipo) {
		this.arbitrajesPorEquipo = arbitrajesPorEquipo;
	}
	*/
	
	
}
