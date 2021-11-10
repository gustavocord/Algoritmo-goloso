package logica;

import java.io.Serializable;
import java.util.Objects;

public class Partido implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Equipo _equipoLocal;
	private Equipo _equipoVisitante;
	private Arbitro _arbitro;

	public Partido(Equipo equipoLocal, Equipo equipoVisitante) {
		validarEquipos(equipoLocal, equipoVisitante);
		this._equipoLocal= equipoLocal;
		this._equipoVisitante= equipoVisitante;
		this._arbitro = null; 
	}
	
	public Equipo getEquipoLocal() {
		return _equipoLocal;
	}
	
	public void setEquipoLocal(Equipo equipoLocal) {
		this._equipoLocal = equipoLocal;
	}
	
	public Equipo getEquipoVisitante() {
		return _equipoVisitante;
	}
	
	public void setEquipoVisitante(Equipo equipoVisitante) {
		this._equipoVisitante = equipoVisitante;
	}
	
	public Arbitro getArbitro() {
		return _arbitro;
	}
	
	public void setArbitro(Arbitro arbitro) {
		this._arbitro = arbitro;
	}
	
	private void validarEquipos(Equipo equipoLocal, Equipo equipoVisitante) {
		if(equipoLocal==null) {
			throw new IllegalArgumentException("El equipo local es null");
		}
		if(equipoVisitante==null) {
			throw new IllegalArgumentException("El equipo local es null");
		}
		if(equipoLocal.equals(equipoVisitante)) {
			throw new IllegalArgumentException("Ambos equipos son iguales");
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(_equipoLocal, _equipoVisitante);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		return Objects.equals(_equipoLocal, other._equipoLocal) && Objects.equals(_equipoVisitante, other._equipoVisitante);
	}
	
	@Override
	public String toString() {
		return "[" + _equipoLocal + " vs " + _equipoVisitante +  " || Arbitro= " + _arbitro + "]";
	}
	
}
