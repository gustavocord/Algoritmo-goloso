package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class UIMain {
	
	private Fondo fondo;
	private JButton BtnAsignarArbitros;
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
		
	}
	
	
	

}
