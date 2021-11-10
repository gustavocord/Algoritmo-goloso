package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logica.Equipo;
public class EquipoTest {

	@Test (expected= IllegalArgumentException.class)
	public void equipoInvalido() {
		new Equipo(null);
	}
	public void equipoValido() {//es valido un equipo con nombre vacio
		Equipo test= new Equipo("");
		assertEquals("",test.getNombre());
	}
}
