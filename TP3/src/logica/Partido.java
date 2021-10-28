package logica;

import java.util.Objects;

public class Partido {
	private Arbitro arbitro;
	private Equipo equipo1;
	private Equipo equipo2;


	public Partido(String eq1, String eq2) {
		this.equipo1 = new Equipo (eq1);
		this.equipo2 = new Equipo (eq2);
	}
	
	public boolean existeArbitro() {
		return(arbitro!=null);
	}
	
	
	public void setArbitro(int numero) {
		
		this.arbitro = new Arbitro(numero);
	}

	public Arbitro getArbitro() {
		return arbitro;
	}

	public void setArbitro(Arbitro arbitro) {
		this.arbitro = arbitro;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}
	
	
	@Override
	public String toString() {
		return "[|" + arbitro + "|"+ equipo1 + " vs " + equipo2 + "|]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(arbitro, equipo1, equipo2);
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
		return Objects.equals(arbitro, other.arbitro) && Objects.equals(equipo1, other.equipo1)
				&& Objects.equals(equipo2, other.equipo2);
	}


	

}
