package test;
import logica.Fecha;
import java.util.ArrayList;
import org.junit.Test;
import logica.Arbitro;
import logica.Equipo;
import logica.Partido;

public class FechaTest {
	ArrayList<Partido> arrayPrueba;
	
	@Test (expected= IllegalArgumentException.class)
	public void listaDePartidosNull() {
		new Fecha(null);
	}
	@Test (expected= IllegalArgumentException.class)
	public void unPartidoDeLaListaNull() {
		instanciarArrayPrueba();
		this.arrayPrueba.add(null);
		new Fecha(arrayPrueba);
	}
	@Test (expected= IllegalArgumentException.class)
	public void dosPartidosIgualesEnElArray() {
		instanciarArrayPrueba();
		Equipo equipoA= new Equipo("A");
		Equipo equipoB= new Equipo("B");
		Partido partidoA= new Partido(equipoA,equipoB);
		Partido partidoB= new Partido(equipoA,equipoB);
		this.arrayPrueba.add(partidoA);
		this.arrayPrueba.add(partidoB);
		new Fecha(arrayPrueba);
	}
	@Test (expected= IllegalArgumentException.class)
	public void asignarUnArbitroAPartidoNegativo() {
		instanciarArrayPrueba();
		Fecha prueba = new Fecha(arrayPrueba);
		prueba.asignarArbitroAPartido(-1, new Arbitro(""));
	}
	@Test (expected= IllegalArgumentException.class)
	public void asignarUnArbitroAPartidoExcedido() {
		instanciarArrayPrueba();
		Fecha prueba = new Fecha(arrayPrueba);
		prueba.asignarArbitroAPartido(1, new Arbitro(""));
	}
	@Test (expected= IllegalArgumentException.class)
	public void asignarUnArbitroNull() {
		instanciarArrayPrueba();
		Fecha prueba = new Fecha(arrayPrueba);
		prueba.asignarArbitroAPartido(0, null);
	}
	public void asignarUnArbitro() {
		instanciarArrayPrueba();
		Fecha prueba = new Fecha(arrayPrueba);
		prueba.asignarArbitroAPartido(0, new Arbitro(""));
	}
	private void instanciarArrayPrueba() {
		this.arrayPrueba= new ArrayList<Partido>();
		this.arrayPrueba.add(new Partido(new Equipo(""), new Equipo("I")));
	}
}
