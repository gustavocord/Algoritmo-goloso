package vistas;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class HubPanel extends JPanel {
	private JButton btnFechaAnterior;
	private JButton btnAsignarArbitraje;
	private JButton btnFechaSiguiente;
	private JButton btnCargarArbitro;
	
	public HubPanel() {
		this.setBackground(new Color(223,149,233));
		this.setBounds(0, 470, 783, 90);
		setLayout(null);
		
		btnFechaAnterior = new JButton("Anterior");
		btnFechaAnterior.setBounds(110, 11, 102, 23);
		add(btnFechaAnterior);
		
		btnAsignarArbitraje = new JButton("Asignar arbitraje");
		btnAsignarArbitraje.setBounds(619, 11, 138, 23);
		add(btnAsignarArbitraje);
		
		btnFechaSiguiente = new JButton("Siguiente ");
		btnFechaSiguiente.setBounds(110, 56, 102, 23);
		add(btnFechaSiguiente);
		
		btnCargarArbitro = new JButton("Cargar Arbitro");
		btnCargarArbitro.setBounds(619, 56, 138, 23);
		add(btnCargarArbitro);
		
		JLabel lblFecha = new JLabel("Ver Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFecha.setBounds(10, 11, 90, 68);
		add(lblFecha);
	}
	
	public JButton getBtnFechaAnterior() {
		return btnFechaAnterior;
	}
	
	public JButton getBtnAsignarArbitraje() {
		return btnAsignarArbitraje;
	}
	
	public JButton getBtnFechaSiguiente() {
		return btnFechaSiguiente;
	}
	
	public JButton getBtnCargarArbitro() {
		return btnCargarArbitro;
	}
	
}
