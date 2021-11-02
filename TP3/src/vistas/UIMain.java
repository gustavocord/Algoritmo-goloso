package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import logica.MainController;

public class UIMain {
	
	private Fondo fondo;
	private JButton BtnAsignarArbitros;
	private JFrame frameMenuPrincipal;

	private static JLayeredPane layeredPane;
	private static JPanelMostrarDatos panelDatos;
	
	private JButton BtnCambiarAFixture;

	
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
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 521, 325);
		layeredPane.setBounds(frameMenuPrincipal.getBounds());
		frameMenuPrincipal.getContentPane().add(layeredPane);
		
		
		panelDatos = new JPanelMostrarDatos();
		panelDatos.setVisible(false);
		layeredPane.add(panelDatos);
		//inicializo el boton 
		
		/*------------Boton para ver calendario--------------------*/
		BtnAsignarArbitros= new JButton("asignar arbitros");
		BtnAsignarArbitros.setBackground(new Color(0, 0, 0));
		BtnAsignarArbitros.setFont(new Font("Tahoma", Font.PLAIN, 25));
		BtnAsignarArbitros.setBounds(0,0, 300, 80);
		BtnAsignarArbitros.setLocation((frameMenuPrincipal.getWidth()/2-BtnAsignarArbitros.getWidth()/2),
									  (frameMenuPrincipal.getHeight()/2 + BtnAsignarArbitros.getHeight()/2) + 100 );
		
		//BtnAsignarArbitros.addActionListener(new ActionListener() {
		
			//public void actionPerformed(ActionEvent e) {
			
	// aca se va a generar la asignacion desde la vista 
			//	}
			//});
	
		frameMenuPrincipal.getContentPane().add(BtnAsignarArbitros);
	
		
		BtnCambiarAFixture= new JButton("Cambiar a Fixture");
		BtnCambiarAFixture.setBackground(new Color(0, 0, 0));
		BtnCambiarAFixture.setFont(new Font("Tahoma", Font.PLAIN, 25));
		BtnCambiarAFixture.setBounds(0,0, 300, 80);
		BtnCambiarAFixture.setLocation((frameMenuPrincipal.getWidth()/2-BtnAsignarArbitros.getWidth()/2),
									  (frameMenuPrincipal.getHeight()/2 + BtnAsignarArbitros.getHeight()/2) + 10);
		
		BtnCambiarAFixture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				cambiarAFixture();
			}
		});
		
		frameMenuPrincipal.getContentPane().add(BtnCambiarAFixture);
		
	}
	
	private void cambiarAFixture() {
		panelDatos.setVisible(true);
	}
	
	public static void main(String[] args) {
		UIMain ui = new UIMain();
	}
	

}
