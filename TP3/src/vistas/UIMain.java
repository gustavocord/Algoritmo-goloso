package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import logica.Torneo;
import logica.Equipo;
import logica.Fecha;

public class UIMain {

	
	private VentanaPrincipal _ventana;
	private JButton _btnFechaAnterior;
	private JButton _btnAsignarArbitraje;
	private JButton _btnFechaSiguiente;
	private JButton _btnCargarArbitro;
	private JButton _btnCargarEnVCA; //boton que se encuentra en la ventana cargar arbitro
	private JButton _btnCancelarEnVCA; //boton que se encuentra en la ventana cargar arbitro
	private Torneo _torneo;
	private boolean _primerClicEnBtnArbitro; //esto realizar la asignacion una unica vez
	
	
	public UIMain (VentanaPrincipal ventana) {
		this._ventana = ventana;
		this._torneo=null;
		inicializarBotones();
		this._primerClicEnBtnArbitro= false;
		agregarEventos();
	}
	
	
	public void mostrarVentanaPrincipal() {
		this._ventana.mostrar();
	}
	
	public void cargarCalendarioAlTorneo(ArrayList<Fecha> calendario, ArrayList<Equipo> equipos) {
		this._torneo= new Torneo(calendario,equipos);
		this._ventana.cargarTorneo(calendario);
		this._ventana.setLimiteDeArbitrosEnVentanaCargarArbitro(equipos.size()/2);//pongo limite en valor para la ventana emergente
	}
	
	private void inicializarBotones() {
		this._btnFechaAnterior=this._ventana.getBtnFechaAnterior();
		this._btnAsignarArbitraje=this._ventana.getBtnAsignarArbitraje();
		this._btnFechaSiguiente=this._ventana.getBtnFechaSiguiente();
		this._btnCargarArbitro=this._ventana.getBtnCargarArbitro();
		this._btnCargarEnVCA= this._ventana.getBtnVentanaDeCargaArbitro__CargarArbitro();
		this._btnCancelarEnVCA=this._ventana.getBtnVentanaDeCargaArbitro__Cancelar();
	}
	
	private void agregarEventos() {
		_btnFechaAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_ventana.paginaAnterior();
			}
		});
		_btnAsignarArbitraje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!_primerClicEnBtnArbitro) {
					_torneo.asignarArbitraje();//asigna el arbitraje al torneo
					_primerClicEnBtnArbitro=true;
					_btnCargarArbitro.setEnabled(true); //se habilita
				}
				else {
					_torneo.mezclarArbitraje(); //mezcla el arbitraje, ya que solucion hay una sola 
				}
					_ventana.cargarTorneo(_torneo.getFechas()); //carga otra vez el torneo
				
			}
		});
		
		_btnFechaSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_ventana.paginaSiguiente();
			}
		});
		
		_btnCargarArbitro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_ventana.mostrarVentanaDeCargarArbitro(true);
			}
		});
		
		this._btnCancelarEnVCA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_ventana.mostrarVentanaDeCargarArbitro(false);
			}
		});
		
		this._btnCargarEnVCA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_torneo.cambiarNombreAArbitro(_ventana.idDeArbitro(), _ventana.nombreDeArbitro());
				_ventana.mostrarVentanaDeCargarArbitro(false);
				_ventana.cargarTorneo(_torneo.getFechas()); //carga otra vez el torneo

			}
		});
	}
}
