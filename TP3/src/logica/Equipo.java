package logica;

import java.io.Serializable;
import java.util.Objects;

public class Equipo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _nombre; 
	public Equipo(String nombre) {
		if(nombre==null) {
			throw new IllegalArgumentException("El nombre del equipo no puede ser null");
		}
		this._nombre = nombre;
	}
	public String getNombre() {
		return _nombre;
	}
	public void setNombre(String nombre) {
		this._nombre = nombre;
	}
	@Override
	public int hashCode() {
		return Objects.hash(_nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(_nombre, other._nombre);
	}
	@Override
	public String toString() {
		return _nombre;
	}
	
}
