package test;

import org.junit.Test;

import logica.Partido;
import logica.Equipo;
public class PartidoTest {

	@Test (expected= IllegalArgumentException.class)
	public void unEquipoInvalido() {
		new Partido(null,new Equipo(""));
	}
	@Test (expected= IllegalArgumentException.class)
	public void mismoEquipo() {
		new Partido(new Equipo(""),new Equipo(""));
	}

}
