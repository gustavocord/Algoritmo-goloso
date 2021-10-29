package vistas;

import javax.swing.JButton;
import javax.swing.JFrame;

public class UIMain {
	
	private Fondo fondo;
	private JButton asignarArbitros;
	private JFrame frameMenuPrincipal;

	
	public UIMain() {
		initialize();
	}

	
	public void initialize() {
		mostrarCalendario();

	}

	public void mostrarCalendario() {
		this.fondo = new Fondo("/imagenes/fondo.jpg");
		frameMenuPrincipal= new JFrame();
		frameMenuPrincipal.setContentPane(fondo);
		frameMenuPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frameMenuPrincipal.getContentPane().setLayout(null);
		frameMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMenuPrincipal.setTitle("Torneo de football" );
		frameMenuPrincipal.setVisible(true);

		
	}

}
