package test;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import logica.Equipo;
import logica.Fecha;
import logica.Partido;
import logica.Torneo;


public class TorneoTest {
	private ArrayList<Fecha> fechasPrueba;
	private ArrayList<Equipo> equiposPrueba;

	@Test (expected = IllegalArgumentException.class)
	public void unaFechaNull() {
		inicializarListasPrueba();
		this.fechasPrueba.add(null);
		new Torneo(this.fechasPrueba,this.equiposPrueba);
	}
	@Test (expected = IllegalArgumentException.class)
	public void unEquipoNull() {
		inicializarListasPrueba();
		this.equiposPrueba.add(null);
		new Torneo(this.fechasPrueba,this.equiposPrueba);
	}
	@Test (expected = IllegalArgumentException.class)
	public void unEquipoRepetidoEnElArray() {
		inicializarListasPrueba();
		this.equiposPrueba.add(new Equipo("EquipoD"));
		new Torneo(this.fechasPrueba,this.equiposPrueba);
	}
	@Test (expected = IllegalArgumentException.class)
	public void cambiarNombreArbitroIDArbitroMenorIgualACero() {
		inicializarListasPrueba();
		agregarUnaFechaMasAPrueba();
		Torneo prueba= new Torneo(this.fechasPrueba,this.equiposPrueba);
		prueba.asignarArbitraje();
		prueba.cambiarNombreAArbitro(0, "Rodolfo Sanchez");
	}
	@Test (expected = IllegalArgumentException.class)
	public void cambiarNombreArbitroMayorALaCantArbitro() { //cant de arbitros = 3
		inicializarListasPrueba();
		agregarUnaFechaMasAPrueba();
		Torneo prueba= new Torneo(this.fechasPrueba,this.equiposPrueba);
		prueba.asignarArbitraje();
		prueba.cambiarNombreAArbitro(4, "Rodolfo Sanchez");
	}
	@Test (expected = NullPointerException.class)
	public void cambiarNombreArbitroSinHacerAsignacionPrevia() {
		/*esta excepcion se produce ya que al no hacer asignacion de arbitros,
		no se puede cambiar el nombre a los partidos porque los arbitros no tienen partidos */
		inicializarListasPrueba();
		agregarUnaFechaMasAPrueba();
		Torneo prueba= new Torneo(this.fechasPrueba,this.equiposPrueba);
		prueba.cambiarNombreAArbitro(0, "Rodolfo Sanchez");
	}
	@Test
	public void asignarArbitraje() {
		inicializarListasPrueba();
		agregarUnaFechaMasAPrueba();
		Torneo prueba= new Torneo(this.fechasPrueba,this.equiposPrueba);
		/*
		  	Fecha 1:
		  		Equipo A vs Equipo B = Arbitro 1
		  		Equipo C vs Equipo D = Arbitro 2
		  		Equipo E vs Equipo F =	Arbitro 3
		  	Fecha 2:
				Equipo C vs Equipo B =	Arbitro 3
				Equipo D vs Equipo F =	Arbitro 1
				Equipo A vs Equipo E =	Arbitro 2
		*/
		prueba.asignarArbitraje();
		String [] arbitrosEsperadosFecha1= new String[] {"Arbitro 1", "Arbitro 2", "Arbitro 3"};
		String [] arbitrosFecha1= listaDeNombreDeArbitros(this.fechasPrueba.get(0));//lista de arbitros fecha 1
		boolean fecha1= Arrays.equals(arbitrosEsperadosFecha1, arbitrosFecha1); //compara si dos arrays tienen los mismos elem
		String [] arbitrosEsperadosFecha2= new String[] {"Arbitro 3", "Arbitro 1", "Arbitro 2"};
		String [] arbitrosFecha2= listaDeNombreDeArbitros(this.fechasPrueba.get(1));//lista de arbitros fecha 1
		boolean fecha2= Arrays.equals(arbitrosEsperadosFecha2, arbitrosFecha2);
		assertTrue(fecha1 && fecha2);
		
	}
	@Test
	public void asignarArbitrajeDosVeces() {
		inicializarListasPrueba();
		agregarUnaFechaMasAPrueba();
		Torneo prueba= new Torneo(this.fechasPrueba,this.equiposPrueba);
		prueba.asignarArbitraje();
		prueba.asignarArbitraje();
		String [] arbitrosEsperadosFecha1= new String[] {"Arbitro 1", "Arbitro 2", "Arbitro 3"};
		String [] arbitrosFecha1= listaDeNombreDeArbitros(this.fechasPrueba.get(0));//lista de arbitros fecha 1
		boolean fecha1= Arrays.equals(arbitrosEsperadosFecha1, arbitrosFecha1); //compara si dos arrays tienen los mismos elem
		String [] arbitrosEsperadosFecha2= new String[] {"Arbitro 3", "Arbitro 1", "Arbitro 2"};
		String [] arbitrosFecha2= listaDeNombreDeArbitros(this.fechasPrueba.get(1));//lista de arbitros fecha 1
		boolean fecha2= Arrays.equals(arbitrosEsperadosFecha2, arbitrosFecha2);
		assertTrue(fecha1 && fecha2);
	}
	
	@Test
	public void cambiarNombreArbitro() {
		inicializarListasPrueba();
		agregarUnaFechaMasAPrueba();
		Torneo prueba= new Torneo(this.fechasPrueba,this.equiposPrueba);
		prueba.asignarArbitraje();
		prueba.cambiarNombreAArbitro(1, "Rodolfo Sanchez");
		String [] arbitrosEsperadosFecha1= new String[] {"Rodolfo Sanchez", "Arbitro 2", "Arbitro 3"};
		String [] arbitrosFecha1= listaDeNombreDeArbitros(this.fechasPrueba.get(0));//lista de arbitros fecha 1
		System.out.println(Arrays.toString(arbitrosFecha1));
		boolean fecha1= Arrays.equals(arbitrosEsperadosFecha1, arbitrosFecha1); //compara si dos arrays tienen los mismos elem
		String [] arbitrosEsperadosFecha2= new String[] {"Arbitro 3", "Rodolfo Sanchez", "Arbitro 2"};
		String [] arbitrosFecha2= listaDeNombreDeArbitros(this.fechasPrueba.get(1));//lista de arbitros fecha 1
		boolean fecha2= Arrays.equals(arbitrosEsperadosFecha2, arbitrosFecha2);
		assertTrue(fecha1 && fecha2);
	}
	@Test
	public void intercambiarArbitro() {
		inicializarListasPrueba();
		agregarUnaFechaMasAPrueba();
		Torneo prueba= new Torneo(this.fechasPrueba,this.equiposPrueba);
		prueba.asignarArbitraje(); //[1,2,3]
		Map<Equipo, Integer> conteoArbitroA= prueba.getArbitraje().get(1);
		Map<Equipo, Integer> conteoArbitroB= prueba.getArbitraje().get(2);
		Set<Partido> historialArbitroA= prueba.getHistorialDeArbitro().get(1);
		Set<Partido> historialArbitroB= prueba.getHistorialDeArbitro().get(2);
		
		prueba.intercambiarArbitro(1, 2);
		Map<Equipo, Integer> cambioConteoArbitroA= prueba.getArbitraje().get(1);
		Map<Equipo, Integer> cambioConteoArbitroB= prueba.getArbitraje().get(2);
		Set<Partido> cambioHistorialArbitroA= prueba.getHistorialDeArbitro().get(1);
		Set<Partido> cambioHistorialArbitroB= prueba.getHistorialDeArbitro().get(2);
		
		boolean intercambioACorrecto= cambioHistorialArbitroA.equals(historialArbitroB) && cambioConteoArbitroA.equals(conteoArbitroB);
		boolean intercambioBCorrecto= cambioHistorialArbitroB.equals(historialArbitroA) && cambioConteoArbitroB.equals(conteoArbitroA);
		
		String [] arbitrosEsperadosFecha1= new String[] {"Arbitro 2", "Arbitro 1", "Arbitro 3"};
		String [] arbitrosFecha1= listaDeNombreDeArbitros(this.fechasPrueba.get(0));//lista de arbitros fecha 1
		boolean intercambioDeNombre= Arrays.equals(arbitrosEsperadosFecha1, arbitrosFecha1);
		boolean resultado= intercambioACorrecto&&intercambioBCorrecto&&intercambioDeNombre;
	
		assertTrue(resultado);
	}
	
	private void inicializarListasPrueba() {
		this.fechasPrueba= new ArrayList<Fecha>();
		this.equiposPrueba= new ArrayList<Equipo>();
		ArrayList <Partido> partidosFecha1= new ArrayList <Partido>();
		Equipo equipoA= new Equipo("EquipoA");
		Equipo equipoB= new Equipo("EquipoB");
		Equipo equipoC= new Equipo("EquipoC");
		Equipo equipoD= new Equipo("EquipoD");
		Equipo equipoE= new Equipo("EquipoE");
		Equipo equipoF= new Equipo("EquipoF");
		partidosFecha1.add(new Partido(equipoA,equipoB));
		partidosFecha1.add(new Partido(equipoC,equipoD));
		partidosFecha1.add(new Partido(equipoE,equipoF));
		this.fechasPrueba.add(new Fecha(partidosFecha1));
		this.equiposPrueba.add(equipoA);
		this.equiposPrueba.add(equipoB);
		this.equiposPrueba.add(equipoC);
		this.equiposPrueba.add(equipoD);
		this.equiposPrueba.add(equipoE);
		this.equiposPrueba.add(equipoF);
	}
	private void agregarUnaFechaMasAPrueba() {
		ArrayList <Partido> partidosFecha2= new ArrayList <Partido>();
		partidosFecha2.add(new Partido(this.equiposPrueba.get(2),this.equiposPrueba.get(1)));
		partidosFecha2.add(new Partido(this.equiposPrueba.get(3),this.equiposPrueba.get(5)));
		partidosFecha2.add(new Partido(this.equiposPrueba.get(0),this.equiposPrueba.get(4)));
		this.fechasPrueba.add(new Fecha(partidosFecha2));
	}
	private String[] listaDeNombreDeArbitros(Fecha fecha) {
		String [] ret = new String[fecha.getPartidos().size()];
		for(int i= 0; i< fecha.getPartidos().size(); i++) {
			ret[i] = fecha.getPartido(i).getArbitro().getNombre();
		}
		return ret;
	}
}
