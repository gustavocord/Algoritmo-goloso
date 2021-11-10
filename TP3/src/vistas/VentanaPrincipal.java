package vistas;


import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpinnerNumberModel;

import logica.Fecha;
import logica.Partido;


public class VentanaPrincipal {

	public JFrame frame;
	private HubPanel hub;
	public ArrayList <FechaPanel> calendarioPaneles;
	private int indiceDePagina;
	private VentanaCargarArbitro ventanaCargarArbitro;
	public VentanaPrincipal() {
		initialize();
	}
	private void initialize() {	
		this.hub= new HubPanel();
		this.frame = new JFrame();
		agregarPropiedadesDeLaVentana();
		this.frame.getContentPane().add(this.hub);
		this.getBtnCargarArbitro().setEnabled(false); //inicializa bloqueado, habilita cuando se asigna arbitraje

		this.ventanaCargarArbitro= new VentanaCargarArbitro();//la ventana de cargar arbitro
		this.frame.getContentPane().add(this.ventanaCargarArbitro);
	
	}
	private void agregarPropiedadesDeLaVentana() {
		this.frame.setTitle("Torneo de futbol");
		this.frame.setBounds(0, 0, 800, 600);
		this.frame.getContentPane().setLayout(null);
		this.frame.setLocationRelativeTo(null); //centra la ventana
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void mostrar() {
		frame.setVisible(true);
	}
	public void cargarTorneo(ArrayList<Fecha> calendario) {
		if(this.calendarioPaneles!=null) {
			this.calendarioPaneles.get(indiceDePagina).mostrar(false);;
		}
		
		this.calendarioPaneles = new ArrayList <FechaPanel>(calendario.size());
		inicializarPaginasDeFechas(calendario.size());
		for(int i= 0; i< this.calendarioPaneles.size(); i++) {
			this.cargarFechaEnTabla(i, calendario.get(i).getPartidos()); //le cargo los partidos a ese panel
		}
		this.calendarioPaneles.get(0).mostrar(true);//al cargar el torneo, voy a mostrar la primera fecha
		this.indiceDePagina= 0;//el indice va a ser 0 porque es la primera pagina
		getBtnFechaAnterior().setEnabled(false);//al cargar en la primera pagina, este boton esta inhabilitado 
		getBtnFechaSiguiente().setEnabled(true);//al cargar en la primera pagina, este boton queda habilitado
	}
	private void cargarFechaEnTabla(int indiceFecha,ArrayList<Partido> partidos) { //este esta para probar, lo ideal es que reciba un calendario y genere todos los panel correspondientes
		this.calendarioPaneles.get(indiceFecha).cargarFechaEnTabla(partidos);
	}
	private void inicializarPaginasDeFechas(int cantidad) {
		for(int i= 0; i<cantidad;i++) {
			FechaPanel nuevoPanel= new FechaPanel(Integer.toString(i+1));
			this.calendarioPaneles.add(nuevoPanel); //creo un panel para la fecha
			nuevoPanel.mostrar(false);
			this.frame.getContentPane().add(nuevoPanel); //lo agrego
		}
	}
	public void paginaAnterior() {
		
		if(existePaginaAnterior()) {
			cambiarDePagina(this.indiceDePagina-1);
		}
		getBtnFechaSiguiente().setEnabled(true);//al ir a la pagina anterior, el boton siguiente debe estar habilitado
		if(!existePaginaAnterior()) {
			getBtnFechaAnterior().setEnabled(false);
		}
	}
	public void paginaSiguiente() {
		
		if(existePaginaSiguiente()) { 
			cambiarDePagina(this.indiceDePagina+1);
		}
		getBtnFechaAnterior().setEnabled(true);
		if(!existePaginaSiguiente()) {
			getBtnFechaSiguiente().setEnabled(false);
		}
	}
	public boolean existePaginaAnterior() {
		return this.indiceDePagina>0;
	}
	public boolean existePaginaSiguiente() {//si es menor a la cant - 1 significa que hay una siguiente
		return this.indiceDePagina<this.calendarioPaneles.size()-1;
	}
	private void cambiarDePagina(int indiceDePagina) {
		this.calendarioPaneles.get(this.indiceDePagina).mostrar(false);//hago invisible el actual
		this.calendarioPaneles.get(indiceDePagina).mostrar(true);//muestro la que quiero
		this.indiceDePagina= indiceDePagina;//actualizo el indice de pagina
		
	}
	public JButton getBtnAsignarArbitraje() {
		return this.hub.getBtnAsignarArbitraje();
	}
	public JButton getBtnCargarArbitro() {
		return this.hub.getBtnCargarArbitro();
	}
	public JButton getBtnFechaAnterior() {
		return this.hub.getBtnFechaAnterior();
	}
	public JButton getBtnFechaSiguiente() {
		return this.hub.getBtnFechaSiguiente();
	}
	public JButton getBtnVentanaDeCargaArbitro__CargarArbitro(){
		return this.ventanaCargarArbitro.getBtnCargar();
	}
	public JButton getBtnVentanaDeCargaArbitro__Cancelar(){
		return this.ventanaCargarArbitro.getBtnCancelar();
	}
	public void mostrarVentanaDeCargarArbitro(boolean b) {
		this.ventanaCargarArbitro.mostrar(b);
	}
	public String nombreDeArbitro() {//devuelve el texto de la ventana de CargarArbitro y lo reinicia
		String ret= this.ventanaCargarArbitro.getNombreArbitro().getText();
		this.ventanaCargarArbitro.getNombreArbitro().setText("");
		return ret;
	}
	public int idDeArbitro() {//devuelve el valor de la ventana de CargarArbitro y lo reinicia
		int ret = (int) this.ventanaCargarArbitro.getIdArbitro().getValue();
		this.ventanaCargarArbitro.getIdArbitro().setValue(1);
		return ret;
	}
	public void setLimiteDeArbitrosEnVentanaCargarArbitro(int cantArbitros){
		this.ventanaCargarArbitro.getIdArbitro().setModel(new SpinnerNumberModel(1, 1, cantArbitros, 1));
	}
}
