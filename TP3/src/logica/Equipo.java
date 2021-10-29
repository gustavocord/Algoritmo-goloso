package logica;

public class Equipo {

	private String nombre ="";
	
	Equipo(String nombre){
		this.nombre=nombre;
	}
	
	public String getNombre() {
		return nombre;
	}


	
	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo otroEquipo = (Equipo) obj;
		return this.nombre.equals(otroEquipo.getNombre());
	}
	

}
