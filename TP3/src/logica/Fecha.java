package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Fecha implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Partido> _partidos;
	public Fecha(ArrayList<Partido> partidos) {
		if(partidos==null) {
			throw new IllegalArgumentException("La lista de partidos no puede ser null");
		}
		this._partidos=new ArrayList<Partido>(partidos.size());
		for(Partido elem: partidos) {
			agregarPartidoAFecha(elem);
		}
	}
	public void asignarArbitroAPartido(int partido, Arbitro arbitro) {
		validarIndicePartido(partido);
		if(arbitro==null) {
			throw new IllegalArgumentException("El arbitro a asignar no debe ser null");
		}
		this._partidos.get(partido).setArbitro(arbitro);
	}
	private void agregarPartidoAFecha(Partido partido) {
		validarPartido(partido, "agregar partido");
		this._partidos.add(partido);
	}
	private void validarIndicePartido(int partido) {
		if(partido<0 || partido>this._partidos.size()-1) {
			throw new IllegalArgumentException("El partido tiene que pertenecer [0, " + partido + "]");
		}
	}
	private void validarPartido(Partido partido, String consulta) {
		if(partido==null) {
			throw new IllegalArgumentException("El partido no puede ser null");
		}
		if(consulta.equals("agregar partido") && this._partidos.contains(partido) ) {
			throw new IllegalArgumentException("El partido ya existe en la lista no se puede: " + consulta);
		}
	}
	public Partido getPartido(int partido) {
		validarIndicePartido(partido);
		return this._partidos.get(partido);
	}
	public ArrayList<Partido> getPartidos() {
		return _partidos;
	}
	@Override
	public String toString() {
		StringBuilder string= new StringBuilder();
		for(int i= 0; i<this._partidos.size(); i++) {
			string.append('\n');
			string.append("Partido " + i + " ");
			string.append(this._partidos.get(i));
		}
		return string.toString();
	}
	
}
