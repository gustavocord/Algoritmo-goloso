package logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Torneo {

	private ArrayList<Equipo> equipos;
	private ArrayList<Fecha> fechas;
	private ArrayList<Arbitro> arbitros;
	private HashMap<Fecha, ArrayList<Integer>> arbitrosDisponibles;

	public Torneo() {
		this.equipos = new ArrayList<>();
		this.arbitros = new ArrayList<Arbitro>();
		this.fechas = new ArrayList<Fecha>();
		this.arbitrosDisponibles = new HashMap<Fecha, ArrayList<Integer>>();
	}

	@Override
	public String toString() {
		return "Torneo [equipos=" + equipos + ", fechas=" + fechas + ", arbitros=" + arbitros + ", arbitrajesPorEquipo="
				+ ", arbitrosDisponibles=" + arbitrosDisponibles + "]";
	}

	public void generarFixture() {

		if (equipos.size() == 0)
			throw new IllegalArgumentException("el calendario no se puede generar sin equipos");

		generarFechas();

		generarPartidos();

	}

	private void generarPartidos() {
		int cantPartidos = cantEquipos() / 2;
		for (Fecha fecha : fechas) {
			for (int i = 0; i < cantPartidos; i++) {
				Partido partidoNuevo = new Partido("", "");
				fecha.agregarPartido(partidoNuevo);
			}
		}
	}

	private void generarFechas() {
		for (int i = 1; i < cantEquipos(); i++) {
			Fecha fechaNueva = new Fecha(i);
			fechas.add(fechaNueva);
			arbitrosDisponibles.put(fechaNueva, new ArrayList<Integer>());
		}
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

	// ----------------------------------GETTERS Y
	// SETTERS---------------------------------//
	@SuppressWarnings("unchecked")
	public ArrayList<Fecha> getFechas() {
		return (ArrayList<Fecha>) fechas.clone();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Equipo> getEquipos() {
		return (ArrayList<Equipo>) equipos.clone();
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

}
