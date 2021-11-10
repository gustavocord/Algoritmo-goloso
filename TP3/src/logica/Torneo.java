package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Torneo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Fecha> _fechas; //asi las fechas estarian enumeradas 
	private ArrayList<Equipo> _equipos; 
	private int _cantEquipos;
	private Map<Integer, Map<Equipo, Integer > > _arbitraje;
	private Map<Integer, Set<Partido>> _historialDeArbitro;
	
	public Torneo(ArrayList<Fecha> fechas, ArrayList<Equipo> equipos) {
		this._fechas= new ArrayList<Fecha>(fechas.size());
		this._equipos= new ArrayList<Equipo>(equipos.size());
		validarTodasLasFechas(fechas);
		this._fechas.addAll(fechas);
		this._cantEquipos= equipos.size();
		agregarTodosLosEquiposAlTorneo(equipos);
	}
	
	private void agregarArbitro() {
		int idDelArbitro= this._arbitraje.size()+1;
		HashMap<Equipo,Integer> diccDelArbitro= new HashMap<Equipo,Integer>();
		this._arbitraje.put(idDelArbitro, diccDelArbitro); //lo agrego al map de arbitraje 
		this._historialDeArbitro.put(idDelArbitro, new HashSet<Partido>());
	}
	
	public void asignarArbitraje() {//asigna el arbitraje a todo el torneo
		//cuando se llama a esta funcion se inicializa el arbitraje, esto es para que cada vez que llame se reinicie el arbitraje
		this._arbitraje= new HashMap<Integer, Map<Equipo, Integer> >(this._cantEquipos/2);
		this._historialDeArbitro= new HashMap<Integer, Set<Partido>>(this._cantEquipos);
		for(int i= 0; i< (this._cantEquipos/2); i++ ) {
			agregarArbitro(); //agrego un arbitro al arbitraje
		}
		for(int i= 0; i<this._fechas.size();i++) {
			asignarArbitrajeEnLaFecha(i);
		}
	}
	
	public void cambiarNombreAArbitro(int idArbitro, String nombre) {
		//dado un idDeArbitro, le cambia el nombre
		validarIDArbitro(idArbitro);
		for(Partido partido: this._historialDeArbitro.get(idArbitro)) {
			partido.setArbitro(new Arbitro(nombre));
		}
	}
	
	public void mezclarArbitraje() {
		//randomiza el arbitraje
		int cantDeMezclas= 5;
		for(int i=0 ; i<cantDeMezclas ; i++) {
			int idArbitroA=numeroRandomEntre(1, (this._cantEquipos/2)+1); //+1 para que lo incluya
			int idArbitroB=numeroRandomEntre(1, (this._cantEquipos/2)+1);
			intercambiarArbitro(idArbitroA, idArbitroB);
		}
	}
	
	public void intercambiarArbitro(int idArbitroA, int idArbitroB) {
		validarIDArbitro(idArbitroA);
		validarIDArbitro(idArbitroB);
		Map<Equipo,Integer> diccArbitroA = this._arbitraje.get(idArbitroA);
		Map<Equipo,Integer> diccArbitroB = this._arbitraje.get(idArbitroB);
		this._arbitraje.replace(idArbitroA, diccArbitroB); //le paso el historial del arbitro B
		this._arbitraje.replace(idArbitroB, diccArbitroA); //le paso el historial del arbitro A
		
		Set<Partido> historialArbitroA= this._historialDeArbitro.get(idArbitroA);
		Set<Partido> historialArbitroB= this._historialDeArbitro.get(idArbitroB);
		
		this._historialDeArbitro.replace(idArbitroA,historialArbitroB); //le paso el historial del arbitro B
		this._historialDeArbitro.replace(idArbitroB,historialArbitroA); //le paso el historial del arbitro A
		
		//ahora le tengo que cambiar el nombre a sus partidos
		cambiarNombreAArbitro(idArbitroA, "Arbitro "+idArbitroA);
		cambiarNombreAArbitro(idArbitroB, "Arbitro "+idArbitroB);
	}
	
	private void asignarArbitrajeEnLaFecha(int fecha) {
		//dado una fecha, asigna el arbitraje de acuerdo al promedio
		Set<Integer> arbitrosDisponibles = new HashSet<Integer>();
		arbitrosDisponibles.addAll(this._arbitraje.keySet());
		for(Partido partido : this._fechas.get(fecha).getPartidos()) {
			Map<Integer,Double> promediosArbitros= new HashMap<Integer,Double>(); //arbitroID, promedio
			for(Integer arbitroID : arbitrosDisponibles) {
				promediosArbitros.put(arbitroID, promedioDePartido (arbitroID,partido));
			}
			//ordena por valores
			List<Entry<Integer, Double>> list = new ArrayList<>(promediosArbitros.entrySet());
			list.sort(Entry.comparingByValue());
			StringBuilder arbitroNombre= new StringBuilder ("Arbitro ");
			arbitroNombre.append(list.get(0).getKey());
			partido.setArbitro(new Arbitro (arbitroNombre.toString()));
			registrarPartidoAEquipo(list.get(0).getKey(), partido.getEquipoLocal());
			registrarPartidoAEquipo(list.get(0).getKey(), partido.getEquipoVisitante());
			registrarPartidoAArbitro(list.get(0).getKey(), partido);
			arbitrosDisponibles.remove(list.get(0).getKey());//deja de estar disponible
			
		}
	
	}
	
	private double promedioDePartido(int arbitroID, Partido partido) {
		Equipo equipoLocal = partido.getEquipoLocal();
		Equipo equipoVisitante = partido.getEquipoVisitante();
		registrarEquipoEnArbitro(arbitroID, equipoLocal);
		registrarEquipoEnArbitro(arbitroID, equipoVisitante);
		int cantVecesDirigidoEquipoLocal = this._arbitraje.get(arbitroID).get(equipoLocal);
		int cantVecesDirigidoEquipoVisitante = this._arbitraje.get(arbitroID).get(equipoVisitante);
		int suma = cantVecesDirigidoEquipoLocal + cantVecesDirigidoEquipoVisitante;
		return suma!=0? suma/2.0 : 0;
	}
	
	private void registrarEquipoEnArbitro(int arbitroID, Equipo equipo) {
		if(!this._arbitraje.get(arbitroID).containsKey(equipo)) { //si el equipo no esta registrado en el map del arbitro
			this._arbitraje.get(arbitroID).put(equipo, 0); //tengo que agregar al equipo al map
		}
	}
	
	private void registrarPartidoAArbitro(int idArbitro, Partido partido) {
		validarIDArbitro(idArbitro);
		this._historialDeArbitro.get(idArbitro).add(partido);
	}
	
	private void registrarPartidoAEquipo(int arbitroID, Equipo equipo) {
		Map<Equipo,Integer> historialArbitro = this._arbitraje.get(arbitroID);
		int cantDePartidosActuales= historialArbitro.get(equipo);
		historialArbitro.replace(equipo, cantDePartidosActuales+1);
	}
	
	private void agregarTodosLosEquiposAlTorneo(ArrayList<Equipo> equipos) {
		for(Equipo elem: equipos) {
			agregarEquipoAlTorneo(elem);
		}
	}
	
	private void validarTodasLasFechas(ArrayList<Fecha> fechas) {
		for(int i=0; i<fechas.size();i++) {
			if(fechas.get(i) == null) {
				throw new IllegalArgumentException("La fecha[" + i + "] es invalida, no puede ser null");
			}
		}
	}
	
	private void agregarEquipoAlTorneo(Equipo equipo) {
		if(equipo==null) {
			throw new IllegalArgumentException("El equipo que ingresa al torneo no puede ser null");
		}
		if(this._equipos.contains(equipo)) {
			throw new IllegalArgumentException("El equipo " + equipo.getNombre() + " ya participa en el torneo");
		}
		this._equipos.add(equipo);
	}
	
	public void setFechas(ArrayList<Fecha> fechas) {
		this._fechas = fechas;
	}
	
	public void setEquipos(ArrayList<Equipo> equipos) {
		this._equipos = equipos;
	}
	
	public ArrayList<Fecha> getFechas() {
		return _fechas;
	}
	
	public ArrayList<Equipo> getEquipos() {
		return _equipos;
	}
	
	public void validarIDArbitro(int idArbitro) {
		if(idArbitro<=0 || idArbitro>this._arbitraje.size()) {
			throw new IllegalArgumentException("idArbitro no es valido, el id va de [,"+this._arbitraje.size()+"]");
		}
	}
	
	public int getCantEquipos() {
		return _cantEquipos;
	}
	
	public Map<Integer, Map<Equipo, Integer>> getArbitraje() {
		return _arbitraje;
	}
	
	public Map<Integer, Set<Partido>> getHistorialDeArbitro() {
		return _historialDeArbitro;
	}
	
	private int numeroRandomEntre(int min, int max) {
		Random aleatorio= new Random();
		return min + aleatorio.nextInt(max-min);
	}
}
